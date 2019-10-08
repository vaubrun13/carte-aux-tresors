package com.vaubrun.utils;

import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.Meadow;
import com.vaubrun.model.landscape.Mountain;

public abstract class ExpectedResults {

    public static Land[][] getSimpleMap() {
        Land[][] land = new Land[4][3];
        land[0] = new Land[]{new Meadow(0), new Meadow(0), new Meadow(0)};
        land[1] = new Land[]{new Meadow(0), new Mountain(), new Meadow(0)};
        land[2] = new Land[]{new Meadow(0), new Meadow(0), new Mountain()};
        land[3] = new Land[]{new Meadow(2), new Meadow(1), new Meadow(0)};

        return land;
    }

    public static Land[][] mockForMove() {
        Land[][] land = new Land[3][4];
        land[0] = new Land[]{new Mountain(), new Meadow(0), new Meadow(0)};
        land[1] = new Land[]{new Meadow(0), new Meadow(0), new Meadow(0), new Meadow(0)};
        land[2] = new Land[]{new Meadow(0), new Meadow(0), new Mountain(), new Meadow(0)};

        return land;
    }
}
