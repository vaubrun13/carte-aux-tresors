package com.vaubrun.board;

import com.vaubrun.board.landscape.Land;
import com.vaubrun.board.landscape.LandType;
import com.vaubrun.exception.InputFileFormatException;
import com.vaubrun.exception.MapCreationException;
import com.vaubrun.utils.ExpectedResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class GameBoardTest {
    private Path inputFile = Paths.get("src", "test", "resources", "simple_map.txt");
    private Land[][] expectedLand = ExpectedResults.getSimpleMap();


    @DisplayName("Should parse input and create the game map with right dimensions")
    @Test
    public void shouldGenerateTheMap() throws MapCreationException {
        //Given
        String[] mapInfo = new String[]{"C ", "3", "  4"};
        //When
        Land[][] map = GameBoard.createMap(mapInfo);
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
            GameBoard.generateBoardFromFile(emptyInfo);
        });
    }

    @DisplayName("Should throw exception because of bad map dimension")
    @Test
    public void shouldThrowExceptionOnBadMapDimensionInfo() {
        //Given
        String[] badInfo = new String[]{"C ", "text", " text"};
        //Then
        Assertions.assertThrows(MapCreationException.class, () -> {
            GameBoard.createMap(badInfo);
        });
    }

    @DisplayName("Should parse input and put mountains on map")
    @Test
    public void shouldSetMountains() throws MapCreationException, IOException, InputFileFormatException {
        //Given
        List<String> allLines = Files.readAllLines(inputFile, Charset.defaultCharset());
        //When
        GameBoard board = GameBoard.generateBoardFromFile(allLines);
        //Then
        Assertions.assertEquals(LandType.MOUNTAIN, board.getMap()[1][1].getType());
        Assertions.assertEquals(LandType.MOUNTAIN, board.getMap()[2][2].getType());
    }

    @DisplayName("Should parse input and put treasures on map")
    @Test
    public void shouldSetTreasures() throws MapCreationException, IOException, InputFileFormatException {
        //Given
        List<String> allLines = Files.readAllLines(inputFile);
        //When
        GameBoard board = GameBoard.generateBoardFromFile(allLines);
        //Then
        Assertions.assertEquals(2, board.getMap()[3][0].getTreasures());
        Assertions.assertEquals(1, board.getMap()[3][1].getTreasures());
    }
}