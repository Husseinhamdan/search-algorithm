package com.company.strategies;

import com.company.game.GameInfo;
import com.company.structure.Action;
import com.company.structure.Grid;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class DFS {
    Grid initializeGrid;
    Action action;
    GameInfo gameInfo;


    public DFS(Action action, GameInfo gameInfo) {
        this.initializeGrid = action.getGrid();
        this.action = action;
        this.gameInfo = gameInfo;
    }

    public void search() {
        Grid grid;

    }


}




