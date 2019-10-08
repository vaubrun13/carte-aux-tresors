package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.exception.CannotClimbMountainException;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.LandType;
import com.vaubrun.parser.ObjectSeparator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class Adventurer {
    private String name;
    private int x;
    private int y;
    private Orientation orientation;
    @Setter(value = AccessLevel.PRIVATE)
    private int collectedTreasures;
    private List<Movement> moves;

    public Adventurer(String name, int x, int y, Orientation orientation) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.collectedTreasures = 0;
        this.moves = new ArrayList<>();
    }

    public void turnLeft() {
        switch (this.getOrientation()) {
            case NORTH:
                this.setOrientation(Orientation.WEST);
                break;
            case EAST:
                this.setOrientation(Orientation.NORTH);
                break;
            case SOUTH:
                this.setOrientation(Orientation.EAST);
                break;
            case WEST:
                this.setOrientation(Orientation.SOUTH);
                break;
        }
    }

    public void turnRight() {
        switch (this.getOrientation()) {
            case NORTH:
                this.setOrientation(Orientation.EAST);
                break;
            case EAST:
                this.setOrientation(Orientation.SOUTH);
                break;
            case SOUTH:
                this.setOrientation(Orientation.WEST);
                break;
            case WEST:
                this.setOrientation(Orientation.NORTH);
                break;
        }
    }

    public void collectTreasure() {
        this.collectedTreasures++;
    }

    /**
     * Make the adventurer move forward on the map
     *
     * @param map
     * @throws BadMoveException             the new position is out of the map bounds
     * @throws CannotClimbMountainException the new position is onto a mountain
     */
    public void moveForward(Land[][] map) throws BadMoveException, CannotClimbMountainException {
        int nextX = this.x;
        int nextY = this.y;
        //Moving the adventurer
        switch (this.getOrientation()) {
            case NORTH:
                if (this.y == 0) throw new BadMoveException("Forbidden movement");
                nextY--;
                break;
            case EAST:
                if (this.x == map[0].length - 1) throw new BadMoveException("Forbidden movement");
                nextX++;
                break;
            case SOUTH:
                if (this.y == map.length - 1) throw new BadMoveException("Forbidden movement");
                nextY++;
                break;
            case WEST:
                if (this.x == 0) throw new BadMoveException("Forbidden movement");
                nextX--;
                break;
        }

        //Checking next position
        if (map[nextY][nextX].getType().equals(LandType.MOUNTAIN)) {
            throw new CannotClimbMountainException(MessageFormat.format("Adventurer {0} could not move forward onto a mountain", this.getName()));
        } else {
            this.setX(nextX);
            this.setY(nextY);
        }

    }

    /**
     * Move adventurer based on chosen move
     *
     * @param movement
     * @param map
     * @throws BadMoveException             when moving forward and the new position is out of the map bounds
     * @throws CannotClimbMountainException when moving forward and the new position is onto a mountain
     */
    public void move(Movement movement, Land[][] map) throws BadMoveException, CannotClimbMountainException {
        switch (movement) {
            case TURN_RIGHT:
                this.turnRight();
                break;
            case TURN_LEFT:
                this.turnLeft();
                break;
            case MOVE_FORWARD:
                this.moveForward(map);
                break;
        }
    }

    public String generateOutput() {
        return MessageFormat.format("{0} - {1} - {2} - {3} - {4} - {5}", ObjectSeparator.ADVENTURER.getValue(), this.getName(),
                this.getX(), this.getY(), this.getOrientation().getValue(), this.getCollectedTreasures());
    }
}
