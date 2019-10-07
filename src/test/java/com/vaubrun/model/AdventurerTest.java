package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.model.landscape.Land;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdventurerTest {
    @DisplayName("Should turn left in right direction")
    @Test
    void shouldTurnLeft() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.EAST);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @DisplayName("Should turn right in right direction")
    @Test
    void shouldTurnRight() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @DisplayName("Should increase treasure count when collecting treasures")
    @Test
    void shouldCollectTreasure() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.collectTreasure();
        adventurer.collectTreasure();
        //Then
        Assertions.assertEquals(2, adventurer.getCollectedTreasures());
    }

    @DisplayName("Should move forward")
    @Test
    void shouldMoveForward() throws BadMoveException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        Land[][] map = new Land[3][4];
        //When
        adventurer.moveForward(map);
        //Then
        Assertions.assertEquals(0, adventurer.getX());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.SOUTH);
        //When
        adventurer.moveForward(map);
        //Then
        Assertions.assertEquals(2, adventurer.getY());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.EAST);
        //When
        adventurer.moveForward(map);
        //Then
        Assertions.assertEquals(2, adventurer.getX());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.NORTH);
        //When
        adventurer.moveForward(map);
        //Then
        Assertions.assertEquals(0, adventurer.getY());
    }

    @DisplayName("Should not move forward out of bounds")
    @Test
    void shouldNotMoveForward() throws BadMoveException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 0, 1, Orientation.WEST);
        Land[][] map = new Land[3][4];
        //When

        //Then
        Assertions.assertThrows(BadMoveException.class, () -> {
            adventurer.moveForward(map);
        });
        Assertions.assertEquals(0, adventurer.getX());
    }

}