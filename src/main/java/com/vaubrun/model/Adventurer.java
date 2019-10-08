package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.exception.CannotClimbMountainException;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.LandType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.text.MessageFormat;

@Data
public class Adventurer {
    private String name;
    private int x;
    private int y;
    private Orientation orientation;
    @Setter(value = AccessLevel.PRIVATE)
    private int collectedTreasures;
    private String moves;

    public Adventurer(String name, int x, int y, Orientation orientation) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.collectedTreasures = 0;
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
}
