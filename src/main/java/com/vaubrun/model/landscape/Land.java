package com.vaubrun.model.landscape;

import com.vaubrun.model.Adventurer;
import lombok.Data;

@Data
public abstract class Land {
    private LandType type;
    private int treasures;
    private Adventurer adventurer;

    public Land(LandType type) {
        this.type = type;
        this.treasures = 0;
    }

    public Land(LandType type, int treasures) {
        this.type = type;
        this.treasures = treasures;
    }
}
