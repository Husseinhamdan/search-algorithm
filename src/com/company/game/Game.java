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
        selectGame();

    }

    public void selectGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- user player");
        System.out.println("2- DFS");
        System.out.println("3- BFS");
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
        }
    }

}
