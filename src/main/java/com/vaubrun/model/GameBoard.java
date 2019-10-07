package com.vaubrun.model;

import com.vaubrun.model.landscape.Land;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the whole game: map and its landscape, adventurers ...
 */
@Log4j2
@Data
public class GameBoard {
    private Land[][] map;
    private List<Adventurer> adventurers;

    public GameBoard() {
        this.adventurers = new ArrayList<>();
    }
}


