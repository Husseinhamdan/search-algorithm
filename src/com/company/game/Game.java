package com.company.game;

import com.company.strategies.*;
import com.company.structure.Action;
import com.company.structure.Grid;

import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Game {

    Action action;

    public Game() {
        this.action = new Action();
        selectGame();

    }

    public void selectGame() {
        boolean end = false;
        while (!end) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- user player");
            System.out.println("2- DFS");
            System.out.println("3- BFS");
            System.out.println("4- UCS");
            System.out.println("5- AStar");
            System.out.println("6- Exit");
            System.out.print("Select Strategies : ");
            int x = scanner.nextInt();
            switch (x) {
                case 1: {
                    UserPlayer userPlayer = new UserPlayer(action);
                    userPlayer.play();
                }
                break;
                case 2: {
                    DFS dfs = new DFS(action);
                    dfs.search();
                }
                break;
                case 3: {
                    BFS bfs = new BFS(action);
                    bfs.search();
                }
                break;
                case 4: {
                    UCS ucs = new UCS(action);
                    ucs.search();
                }
                case 5: {
                    Astar astar = new Astar(action);
                    astar.search();
                }
                break;
                case 6: {
                    end = true;
                }
                break;
            }
        }
    }

}
