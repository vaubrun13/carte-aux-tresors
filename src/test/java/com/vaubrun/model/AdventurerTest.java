package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.exception.CannotClimbMountainException;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.Mountain;
import com.vaubrun.utils.ExpectedResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdventurerTest {
    @DisplayName("Should turn left")
    @Test
    void shouldTurnLeft() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.EAST);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.NORTH);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.WEST, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.SOUTH, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.SOUTH);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.EAST, adventurer.getOrientation());
    }

    @DisplayName("Should turn right")
    @Test
    void shouldTurnRight() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.NORTH);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.EAST, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.EAST);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.SOUTH, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.SOUTH);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.WEST, adventurer.getOrientation());
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
    void shouldMoveForward() throws BadMoveException, CannotClimbMountainException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        Land[][] map = ExpectedResults.getSimpleMap();
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
        //Then
        Assertions.assertThrows(BadMoveException.class, () -> {
            adventurer.moveForward(map);
        });
        Assertions.assertEquals(0, adventurer.getX());
    }

    @DisplayName("Should not move onto a mountain")
    @Test
    void shouldNotMoveOnMountains() throws BadMoveException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 2, Orientation.EAST);
        Land[][] map = new Land[3][4];
        map[2][2] = new Mountain();
        //Then
        Assertions.assertThrows(CannotClimbMountainException.class, () -> {
            adventurer.moveForward(map);
        });
        Assertions.assertEquals(1, adventurer.getX());
        Assertions.assertEquals(2, adventurer.getY());
    }

    @DisplayName("Should move according to desired movement")
    @Test
    void shouldMove() throws BadMoveException, CannotClimbMountainException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 2, Orientation.EAST);
        Land[][] map = ExpectedResults.mockForMove();
        //When
        adventurer.move(Movement.TURN_RIGHT, map);
        //Then
        Assertions.assertEquals(Orientation.SOUTH, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 2, Orientation.NORTH);
        //When
        adventurer.move(Movement.TURN_LEFT, map);
        //Then
        Assertions.assertEquals(Orientation.WEST, adventurer.getOrientation());

        //Given
        adventurer = new Adventurer("jack sparrow", 1, 2, Orientation.NORTH);
        //When
        adventurer.move(Movement.MOVE_FORWARD, map);
        //Then
        Assertions.assertEquals(1, adventurer.getX());
        Assertions.assertEquals(1, adventurer.getY());
    }

}