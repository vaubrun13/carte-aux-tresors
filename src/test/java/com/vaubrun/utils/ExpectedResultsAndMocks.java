package com.vaubrun.utils;

import com.vaubrun.model.landscape.Land;
import com.vaubrun.model.landscape.Meadow;
import com.vaubrun.model.landscape.Mountain;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpectedResultsAndMocks {

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

    public static List<String> getExpectedOutput() {
        List<String> output = new ArrayList<>();
        output.add("C - 3 - 4");
        output.add("M - 1 - 1");
        output.add("M - 2 - 2");
        output.add("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
        output.add("T - 0 - 3 - 2");
        output.add("T - 1 - 3 - 1");
        output.add("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés} ");
        output.add("A - jack sparrow - 1 - 2 - E - 2");
        output.add("A - lara croft - 0 - 2 - O - 1");
        return output;
    }
}
