package com.vaubrun.model;

import com.vaubrun.exception.BadMoveException;
import com.vaubrun.exception.CannotClimbMountainException;
import com.vaubrun.exception.LandAlreadyOccupiedException;
import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.LandType;
import com.vaubrun.parser.ObjectSeparator;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the whole world: map and its landscape, adventurers ...
 */
@Log4j2
@Data
public class GameBoard {
    private Land[][] map;
    private List<Adventurer> adventurers;

    public GameBoard() {
        this.adventurers = new ArrayList<>();
    }

    /**
     * Move the adventurers on the map according to the move that has been assigned to them
     */
    public void makeAdventurerMove() {
        if (this.getAdventurers().isEmpty()) {
            log.info("Noting to do as no adventurers defined");
        } else {
            for (int i = 0; i < this.getAdventurers().get(0).getMoves().size(); i++) {
                for (Adventurer adventurer : this.getAdventurers()) {
                    try {
                        adventurer.move(adventurer.getMoves().get(i), this.getMap());
                    } catch (BadMoveException e) {
                        log.error(MessageFormat.format("Adventurer{0} could not move as it will bring him out of bounds", adventurer.getName()), e);
                    } catch (CannotClimbMountainException e) {
                        log.error(MessageFormat.format("Adventurer{0} could not move as it cannot clim mountains", adventurer.getName()), e);
                    } catch (LandAlreadyOccupiedException e) {
                        log.error(MessageFormat.format("Adventurer{0} could not move as the land is already occupied", adventurer.getName()), e);
                    }
                }
            }
        }
    }

    /**
     * Generate the strings that should be saved in output file
     *
     * @return List of all the lines
     */
    public List<String> generateOutput() {
        List<String> output = new ArrayList<>();
        output.add(MessageFormat.format("{0} - {1} - {2}", ObjectSeparator.MAP.getValue(), this.getMap()[0].length, this.getMap().length));
        //Crawling the map and looking for mountains
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                if (map[y][x].getType().equals(LandType.MOUNTAIN)) {
                    output.add(MessageFormat.format("{0} - {1} - {2}", ObjectSeparator.MOUNTAIN.getValue(), x, y));
                }
            }
        }
        //Crawling the map and looking for treasures
        output.add("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                if (map[y][x].getTreasures() > 0) {
                    output.add(MessageFormat.format("{0} - {1} - {2} - {3}", ObjectSeparator.TREASURE.getValue(), x, y, map[y][x].getTreasures()));
                }
            }
        }

        output.add("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}");
        for (Adventurer adventurer : this.getAdventurers()) {
            output.add(adventurer.generateOutput());
        }

        return output;
    }
}


