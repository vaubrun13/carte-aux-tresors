package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.exception.CannotClimbMountainException;
import com.vaubrun.exception.LandAlreadyOccupiedException;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.Mountain;
import com.vaubrun.utils.ExpectedResultsAndMocks;
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
    void shouldMoveForward() throws BadMoveException, CannotClimbMountainException, LandAlreadyOccupiedException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();
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
    void shouldMove() throws BadMoveException, CannotClimbMountainException, LandAlreadyOccupiedException {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 2, Orientation.EAST);
        Land[][] map = ExpectedResultsAndMocks.mockForMove();
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

    @DisplayName("Should not move if other adventurer on land")
    @Test
    void shouldNotMoveOnOccupiedLand() throws BadMoveException, CannotClimbMountainException {
        //Given
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();
        Adventurer jack = new Adventurer("jack sparrow", 1, 0, Orientation.WEST);
        Adventurer lara = new Adventurer("lara croft", 0, 0, Orientation.SOUTH);
        map[jack.getY()][jack.getX()].setAdventurer(jack);
        map[lara.getY()][lara.getX()].setAdventurer(lara);
        //Then
        Assertions.assertThrows(LandAlreadyOccupiedException.class, () -> {
            jack.move(Movement.MOVE_FORWARD, map);
        });


    }

    @DisplayName("Should leave land when moving")
    @Test
    void shouldLeaveLand() throws BadMoveException, CannotClimbMountainException, LandAlreadyOccupiedException {
        //Given
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();
        Adventurer jack = new Adventurer("jack sparrow", 1, 0, Orientation.EAST);
        Adventurer lara = new Adventurer("lara croft", 0, 0, Orientation.SOUTH);
        map[jack.getY()][jack.getX()].setAdventurer(jack);
        map[lara.getY()][lara.getX()].setAdventurer(lara);
        //When
        jack.move(Movement.MOVE_FORWARD, map);
        //Then
        Assertions.assertNull(map[0][1].getAdventurer());


    }

    @DisplayName("Should occupy land when moving")
    @Test
    void shouldOccupyLand() throws BadMoveException, CannotClimbMountainException, LandAlreadyOccupiedException {
        //Given
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();
        Adventurer jack = new Adventurer("jack sparrow", 1, 0, Orientation.EAST);
        Adventurer lara = new Adventurer("lara croft", 0, 0, Orientation.SOUTH);
        map[jack.getY()][jack.getX()].setAdventurer(jack);
        map[lara.getY()][lara.getX()].setAdventurer(lara);
        //When
        jack.move(Movement.MOVE_FORWARD, map);
        //Then
        Assertions.assertEquals(jack, map[jack.getY()][jack.getX()].getAdventurer());


    }

    @DisplayName("Should collect treasures")
    @Test
    void shouldcollectTreasures() throws BadMoveException, CannotClimbMountainException, LandAlreadyOccupiedException {
        //Given
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();
        Adventurer jack = new Adventurer("jack sparrow", 0, 2, Orientation.SOUTH);
        Adventurer lara = new Adventurer("lara croft", 0, 0, Orientation.SOUTH);
        map[jack.getY()][jack.getX()].setAdventurer(jack);
        map[lara.getY()][lara.getX()].setAdventurer(lara);
        //When
        jack.move(Movement.MOVE_FORWARD, map);
        //Then
        Assertions.assertEquals(1, jack.getCollectedTreasures());
        Assertions.assertEquals(1, map[jack.getY()][jack.getX()].getTreasures());


    }

}