package com.vaubrun.parser;

import com.vaubrun.exception.*;
import com.vaubrun.model.Adventurer;
import com.vaubrun.model.GameBoard;
import com.vaubrun.model.Movement;
import com.vaubrun.model.Orientation;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.Meadow;
import com.vaubrun.model.landscape.Mountain;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
@Data
public abstract class GameBoardFactory {
    private GameBoardFactory() {
    }

    /**
     * Generates a game board according to the information given in gameDescriptor
     *
     * @param gameDescriptor String list describing the map and the objects on it
     * @return the game board described
     * @throws MapCreationException          Failed to initiate the map because of bad parameters in descriptor
     * @throws InputFileFormatException      the map cannot be initiated as required information is missing from descriptor
     * @throws BadPositionParameterException one of the object to be put onto the map has a position that is not a valid number
     * @throws InvalidPositionException      one of the object to be put onto the map has a position that is not, ex: -2
     * @throws BadTreasureCountParameter     a land has a treasure count that is not a valid number
     */
    public static GameBoard generateBoardFromFile(List<String> gameDescriptor)
            throws MapCreationException, InputFileFormatException, BadPositionParameterException, BadTreasureCountParameter, InvalidPositionException {
        GameBoard gameBoard = new GameBoard();

        //Look for the line that describes the map
        Optional<String> mapDescription = gameDescriptor.stream()
                .filter(line -> line.startsWith(ObjectSeparator.MAP.getValue()))
                .findFirst();

        String[] info = mapDescription.orElseThrow(() -> new InputFileFormatException("Missing map description"))
                .split(ObjectSeparator.INFO_SEPARATOR.getValue());
        gameBoard.setMap(createMap(info));

        //All other things
        for (String descriptor : gameDescriptor) {
            //Skipping the line if descriptor is a comment
            if (descriptor.startsWith(ObjectSeparator.COMMENT.getValue())) continue;

            String[] objectInformation = descriptor.split(ObjectSeparator.INFO_SEPARATOR.getValue());
            ObjectSeparator describedObject = ObjectSeparator.fromValue(objectInformation[0].trim());

            String xToParse;
            String yToParse;
            if (describedObject.equals(ObjectSeparator.ADVENTURER)) {
                xToParse = objectInformation[2];
                yToParse = objectInformation[3];
            } else {
                xToParse = objectInformation[1];
                yToParse = objectInformation[2];
            }
            //Validating parameter read from file
            int x = parsePosition(describedObject, xToParse);
            int y = parsePosition(describedObject, yToParse);

            if (x > gameBoard.getMap()[0].length || y > gameBoard.getMap().length) {
                throw new InvalidPositionException(MessageFormat.format("{0} position is outside of map bounds - x: {1} y: {2}", describedObject, x, y));
            }

            switch (describedObject) {
                case MOUNTAIN:
                    gameBoard.getMap()[y][x] = new Mountain();
                    break;
                case TREASURE:
                    try {
                        gameBoard.getMap()[y][x].setTreasures(Integer.parseInt(objectInformation[3].trim()));
                    } catch (NumberFormatException e) {
                        throw new BadTreasureCountParameter(MessageFormat.format("Invalid treasure count: {0}", objectInformation[3]), e);
                    }
                    break;
                case ADVENTURER:
                    Orientation orientation = Orientation.fromValue(objectInformation[4].trim());
                    Adventurer adventurer = new Adventurer(objectInformation[1].trim(), x, y, orientation);

                    char[] movements = objectInformation[5].trim().toCharArray();
                    List<Movement> moves = new ArrayList<>();
                    for (char move : movements) {
                        moves.add(Movement.fromValue(String.valueOf(move)));
                    }

                    adventurer.setMoves(moves);
                    gameBoard.getAdventurers().add(adventurer);
                    break;
                default:
                    break;
            }
        }


        return gameBoard;
    }

    /**
     * Validate a string as a valid position
     *
     * @param describedObject
     * @param positionString
     * @return
     * @throws BadPositionParameterException could not parse positionString to a valid number
     * @throws InvalidPositionException      parsed position is less than 0
     */
    public static int parsePosition(ObjectSeparator describedObject, String positionString) throws BadPositionParameterException, InvalidPositionException {
        int position;
        try {
            position = Integer.parseInt(positionString.trim());
        } catch (NumberFormatException e) {
            throw new BadPositionParameterException(MessageFormat.format("position for {0} is invalid: {1}", describedObject.getValue(), positionString), e);
        }
        if (position < 0) {
            throw new InvalidPositionException("Position cannot be less than 0");
        }
        return position;
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
            throw new MapCreationException(MessageFormat.format("x size of the map is invalid: {0}", info[1]), e);
        }

        try {
            y = Integer.parseInt(info[2].trim());
        } catch (NumberFormatException e) {
            throw new MapCreationException(MessageFormat.format("y size of the map is invalid: {0}", info[2]), e);
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


