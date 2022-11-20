package com.company.game;

import com.company.strategies.BFS;
import com.company.strategies.DFS;
import com.company.strategies.UserPlayer;
import com.company.structure.Action;
import com.company.structure.Grid;

import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Game {

    Action action;

    public Game() {

        this.action = new Action();
//        UserPlayer userPlayer = new UserPlayer(action);
//        userPlayer.play();
//        DFS dfs = new DFS(action);
//        dfs.search();
        BFS bfs = new BFS(action);
        bfs.search();
    }


}
