package com.vaubrun.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdventurerTest {
    @DisplayName("Should turn left in right direction")
    @Test
    void testTurnLeft() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.EAST);
        //When
        adventurer.turnLeft();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @DisplayName("Should turn right in right direction")
    @Test
    void testTurnRight() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.turnRight();
        //Then
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @DisplayName("Should increase treasure count when collecting treasures")
    @Test
    void collectTreasure() {
        //Given
        Adventurer adventurer = new Adventurer("jack sparrow", 1, 1, Orientation.WEST);
        //When
        adventurer.collectTreasure();
        adventurer.collectTreasure();
        //Then
        Assertions.assertEquals(2, adventurer.getCollectedTreasures());
    }
}