package com.vaubrun.model;

import com.vaubrun.exception.*;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.parser.GameBoardFactory;
import com.vaubrun.utils.ExpectedResultsAndMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class GameBoardTest {

    @DisplayName("Should generate output")
    @Test
    void generateOutput() {
        //Given
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

    @DisplayName("Should play the move for one adventurer")
    @Test
    void shouldMoveOneAdventurer() throws IOException, MapCreationException, BadTreasureCountParameter, InvalidPositionException, BadPositionParameterException, InputFileFormatException {
        //Given
        Path inputFile = Paths.get("src", "test", "resources", "example.txt");
        List<String> allLines = Files.readAllLines(inputFile, Charset.defaultCharset());
        GameBoard gameBoard = GameBoardFactory.generateBoardFromFile(allLines);
        //When
        gameBoard.makeAdventurerMove();
        List<String> out = gameBoard.generateOutput();
        //Then
        Path resultFile = Paths.get("src", "test", "resources", "example_result.txt");
        List<String> expectedResult = Files.readAllLines(resultFile, Charset.defaultCharset());
        Assertions.assertEquals(expectedResult, out);
    }

    @DisplayName("Should play scenario of adventurer getting stuck")
    @Test
    void shouldPlayScenarioStuckAdventurer() throws IOException, MapCreationException, BadTreasureCountParameter, InvalidPositionException, BadPositionParameterException, InputFileFormatException {
        //Given
        Path inputFile = Paths.get("src", "test", "resources", "adventurer_stuck_example.txt");
        List<String> allLines = Files.readAllLines(inputFile, Charset.defaultCharset());
        GameBoard gameBoard = GameBoardFactory.generateBoardFromFile(allLines);
        //When
        gameBoard.makeAdventurerMove();
        List<String> out = gameBoard.generateOutput();
        //Then
        Path resultFile = Paths.get("src", "test", "resources", "adventurer_stuck_example_result.txt");
        List<String> expectedResult = Files.readAllLines(resultFile, Charset.defaultCharset());
        Assertions.assertEquals(expectedResult, out);
    }

    @DisplayName("Should play scenario of 2 adventurers")
    @Test
    void shouldPlayScenario2Adventurers() throws IOException, MapCreationException, BadTreasureCountParameter, InvalidPositionException, BadPositionParameterException, InputFileFormatException {
        //Given
        Path inputFile = Paths.get("src", "test", "resources", "2_adventurers_example.txt");
        List<String> allLines = Files.readAllLines(inputFile, Charset.defaultCharset());
        GameBoard gameBoard = GameBoardFactory.generateBoardFromFile(allLines);
        //When
        gameBoard.makeAdventurerMove();
        List<String> out = gameBoard.generateOutput();
        //Then
        Path resultFile = Paths.get("src", "test", "resources", "2_adventurers_example_result.txt");
        List<String> expectedResult = Files.readAllLines(resultFile, Charset.defaultCharset());
        Assertions.assertEquals(expectedResult, out);
    }
}