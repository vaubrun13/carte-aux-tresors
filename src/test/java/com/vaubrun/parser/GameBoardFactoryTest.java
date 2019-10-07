package com.vaubrun.parser;

import com.vaubrun.exception.*;
import com.vaubrun.model.Adventurer;
import com.vaubrun.model.GameBoard;
import com.vaubrun.model.Orientation;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.LandType;
import com.vaubrun.utils.ExpectedResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GameBoardFactoryTest {
    private Path inputFile = Paths.get("src", "test", "resources", "simple_map.txt");
    private Land[][] expectedLand = ExpectedResults.getSimpleMap();


    @DisplayName("Should parse input and create the game map with right dimensions")
    @Test
    public void shouldGenerateTheMap() throws MapCreationException {
        //Given
        String[] mapInfo = new String[]{"C ", "3", "  4"};
        //When
        Land[][] map = GameBoardFactory.createMap(mapInfo);
        //Then
        Assertions.assertEquals(expectedLand.length, map.length);
        Assertions.assertEquals(expectedLand[0].length, map[0].length);
    }

    @DisplayName("Should throw exception because of missing map info")
    @Test
    public void shouldThrowExceptionOnMissingInfo() {
        //Given
        List<String> emptyInfo = Collections.emptyList();
        //Then
        Assertions.assertThrows(InputFileFormatException.class, () -> {
            GameBoardFactory.generateBoardFromFile(emptyInfo);
        });
    }

    @DisplayName("Should throw exception because of bad map dimension")
    @Test
    public void shouldThrowExceptionOnBadMapDimensionInfo() {
        //Given
        String[] badInfo = new String[]{"C ", "text", " text"};
        //Then
        Assertions.assertThrows(MapCreationException.class, () -> {
            GameBoardFactory.createMap(badInfo);
        });
    }

    @DisplayName("Should parse input and put mountains on map")
    @Test
    public void shouldSetMountains() throws MapCreationException, IOException, InputFileFormatException, BadTreasureCountParameter, BadPositionParameterException, InvalidPositionException {
        //Given
        List<String> allLines = Files.readAllLines(inputFile, Charset.defaultCharset());
        //When
        GameBoard board = GameBoardFactory.generateBoardFromFile(allLines);
        //Then
        Assertions.assertEquals(LandType.MOUNTAIN, board.getMap()[1][1].getType());
        Assertions.assertEquals(LandType.MOUNTAIN, board.getMap()[2][2].getType());
    }

    @DisplayName("Should parse input and put treasures on map")
    @Test
    public void shouldSetTreasures() throws MapCreationException, IOException, InputFileFormatException, BadTreasureCountParameter, BadPositionParameterException, InvalidPositionException {
        //Given
        List<String> allLines = Files.readAllLines(inputFile);
        //When
        GameBoard board = GameBoardFactory.generateBoardFromFile(allLines);
        //Then
        Assertions.assertEquals(2, board.getMap()[3][0].getTreasures());
        Assertions.assertEquals(1, board.getMap()[3][1].getTreasures());
    }

    @DisplayName("Should parse input and put adventurers on map")
    @Test
    public void shouldCreateAdventurers() throws MapCreationException, IOException, InputFileFormatException, BadTreasureCountParameter, BadPositionParameterException, InvalidPositionException {
        //Given
        List<String> allLines = Files.readAllLines(inputFile);
        //When
        GameBoard board = GameBoardFactory.generateBoardFromFile(allLines);
        //Then
        Assertions.assertEquals(2, board.getMap()[3][0].getTreasures());
        Assertions.assertEquals(1, board.getMap()[3][1].getTreasures());
    }

    @DisplayName("Should reject parameter as not a valid number")
    @Test
    void shouldRejectNotStringPosition() {
        //Given
        String falsyValue = "eeee";
        //Then
        Assertions.assertThrows(BadPositionParameterException.class, () -> {
            GameBoardFactory.parsePosition(ObjectSeparator.TREASURE, falsyValue);
        });
    }

    @DisplayName("Should reject parameter as not a valid position")
    @Test
    void shouldRejectOutOfBounds() {
        //Given
        String falsyValue = "-42";
        //Then
        Assertions.assertThrows(InvalidPositionException.class, () -> {
            GameBoardFactory.parsePosition(ObjectSeparator.TREASURE, falsyValue);
        });
    }

    @DisplayName("Should reject position as out of map bounds")
    @Test
    void shouldRejectOutOfMapBounds() {
        //Given
        List<String> description = new ArrayList<>();
        description.add("C - 2 - 2");
        description.add("T - 4 - 2 - 1");
        //Then
        Assertions.assertThrows(InvalidPositionException.class, () -> {
            GameBoardFactory.generateBoardFromFile(description);
        });
    }

    @DisplayName("Should add an adventurer")
    @Test
    void shouldAddAdventurer() throws MapCreationException, InvalidPositionException, BadTreasureCountParameter,
            BadPositionParameterException, InputFileFormatException {
        Adventurer expectedAdventurer = new Adventurer("wilson", 1, 0, Orientation.SOUTH);
        expectedAdventurer.setMoves("A");
        //Given
        List<String> description = new ArrayList<>();
        description.add("C - 2 - 2");
        description.add("A - wilson - 1 - 0 - S - A");
        //When
        GameBoard gameBoard = GameBoardFactory.generateBoardFromFile(description);
        //Then
        Assertions.assertEquals(1, gameBoard.getAdventurers().size());
        Assertions.assertEquals(expectedAdventurer, gameBoard.getAdventurers().get(0));
    }
}