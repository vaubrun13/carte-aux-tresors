package com.vaubrun.model.landscape;

import lombok.Data;

@Data
public abstract class Land {
    private LandType type;
    private int treasures;

    public Land(LandType type) {
        this.type = type;
        this.treasures = 0;
    }

    public Land(LandType type, int treasures) {
        this.type = type;
        this.treasures = treasures;
    }
}
