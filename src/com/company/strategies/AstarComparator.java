package com.company.strategies;

import com.company.structure.Grid;

import java.util.Comparator;

public class AstarComparator implements Comparator<Grid> {

    @Override
    public int compare(Grid o1, Grid o2) {
        if (o1.getAStarCost() > o2.getAStarCost())
            return 1;
        else if (o1.getAStarCost()< o2.getAStarCost())
            return -1;
        return 0;
    }

}
