package com.vaubrun.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
}
