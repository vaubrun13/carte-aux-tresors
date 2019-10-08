package com.vaubrun.model;

import com.vaubrun.model.landscape.Land;
import com.vaubrun.utils.ExpectedResultsAndMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameBoardTest {

    @DisplayName("Should generate proper output")
    @Test
    void generateOutput() {
        //Give,
        Land[][] map = ExpectedResultsAndMocks.getSimpleMap();

        Adventurer jack = new Adventurer("jack sparrow", 1, 2, Orientation.EAST);
        jack.collectTreasure();
        jack.collectTreasure();

        Adventurer lara = new Adventurer("lara croft", 0, 2, Orientation.WEST);
        lara.collectTreasure();

        GameBoard board = new GameBoard();
        board.setMap(map);
        board.getAdventurers().add(jack);
        board.getAdventurers().add(lara);
        //When
        List<String> out = board.generateOutput();
        //Then
        Assertions.assertEquals(ExpectedResultsAndMocks.getExpectedOutput(), out);
    }
}