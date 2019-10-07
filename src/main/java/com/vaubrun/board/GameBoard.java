package com.vaubrun.board;

import com.vaubrun.board.landscape.Land;
import com.vaubrun.board.landscape.Meadow;
import com.vaubrun.board.landscape.Mountain;
import com.vaubrun.exception.InputFileFormatException;
import com.vaubrun.exception.MapCreationException;
import com.vaubrun.parse.FileSeparator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Represents the whole game: map and its landscape, adventurers ...
 */
@Log4j2
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameBoard {
    private Land[][] map;

    private GameBoard(int mapXsize, int mapYsize) {
        this.map = new Land[mapYsize][mapXsize];
    }

    /**
     * Generates a game board according to the information given in gameDescriptor
     *
     * @param gameDescriptor
     * @return the game board described
     * @throws MapCreationException     Failed to initiate the map because of bad parameters in descriptor
     * @throws InputFileFormatException the map cannot be initiated as required information is missing from descriptor
     */
    public static GameBoard generateBoardFromFile(List<String> gameDescriptor)
            throws MapCreationException, InputFileFormatException {
        GameBoard gameBoard = new GameBoard();

        //Look for the line that describes the map
        Optional<String> mapDescription = gameDescriptor.stream()
                .filter(line -> line.startsWith(FileSeparator.MAP.getValue()))
                .findFirst();

        String[] info = mapDescription.orElseThrow(() -> new InputFileFormatException("Missing map description"))
                .split(FileSeparator.INFO_SEPARATOR.getValue());
        gameBoard.map = createMap(info);

        //All other things
        gameDescriptor.parallelStream()
                .map(line -> line.split(FileSeparator.INFO_SEPARATOR.getValue()))
                .forEach(information -> {
                    FileSeparator describedLand = FileSeparator.fromValue(information[0].trim());
                    //Validating parameter read from file
                    int x;
                    int y;
                    try {
                        x = Integer.parseInt(information[1].trim());
                    } catch (NumberFormatException e) {
                        log.error(MessageFormat.format("x position for {0} is invalid: {1}", describedLand, information[1]), e);
                        throw new RuntimeException(MessageFormat.format("x position for {0} is invalid: {1}", describedLand.getValue(), information[1]), e);
                    }

                    try {
                        y = Integer.parseInt(information[2].trim());
                    } catch (NumberFormatException e) {
                        log.error(MessageFormat.format("y position for {0} is invalid: {1}", describedLand, information[2]), e);
                        throw new RuntimeException(MessageFormat.format("y position for {0} is invalid: {1}", describedLand.getValue(), information[2]), e);
                    }

                    switch (describedLand) {
                        case MOUNTAIN:
                            gameBoard.getMap()[y][x] = new Mountain();
                            break;
                        case TREASURE:
                            try {
                                gameBoard.getMap()[y][x].setTreasures(Integer.parseInt(information[3].trim()));
                            } catch (NumberFormatException e) {
                                log.error(MessageFormat.format("Invalid treasure count: {0}", information[3]), e);
                                throw new RuntimeException(MessageFormat.format("Invalid treasure count: {0}", information[3]), e);
                            }
                            break;
                        default:
                            break;
                    }
                });


        return gameBoard;
    }

    /**
     * Creates the map according to the descriptor info
     *
     * @param info Slitted info from descriptor{C comme Carte} - {Nb. de case en largeur} - {Nb. de case en hauteur}
     * @return
     * @throws MapCreationException
     */
    public static Land[][] createMap(String[] info) throws MapCreationException {
        //Validating parameter read from file
        int x;
        int y;
        try {
            x = Integer.parseInt(info[1].trim());
        } catch (NumberFormatException e) {
            log.error(MessageFormat.format("x size of the map is invalid: {0}", info[1]));
            throw new MapCreationException(MessageFormat.format("x size of the map is invalid: {0}", info[1]));
        }

        try {
            y = Integer.parseInt(info[2].trim());
        } catch (NumberFormatException e) {
            log.error(MessageFormat.format("y size of the map is invalid: {0}", info[2]));
            throw new MapCreationException(MessageFormat.format("y size of the map is invalid: {0}", info[2]));
        }
        //Creating the map
        Land[][] land = new Land[y][x];
        //Defaulting all as meadow
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                land[i][j] = new Meadow(0);
            }
        }
        return land;
    }
}


