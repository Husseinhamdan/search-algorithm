package com.company.game;

import com.company.strategies.DFS;
import com.company.strategies.UserPlayer;
import com.company.structure.Action;
import com.company.structure.Grid;

import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Game {
    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
    private Grid grid;
    private Grid goal;
    Action action;
    GameInfo gameInfo;
    public Grid getGrid() {
        return grid;
    }

    public Game() {
        InitializeGame();
        this.action = new Action(grid, goal);
//        UserPlayer userPlayer = new UserPlayer(action);
//        userPlayer.play();
        DFS dfs=new DFS(action,gameInfo);
        dfs.search();
    }

    public void InitializeGame() {
        System.out.print("Enter level of game: ");
        int level = sc.nextInt();
        this.gameInfo = new GameInfo(level);
        int n = gameInfo.getN();
        int d = gameInfo.getD();
        grid = new Grid(n, d);
        goal = new Grid(n, d);
        grid.createGrid();
        goal.createGoalGrid();
        System.out.println("level =" + level);
        System.out.println("Dimension of color grid =" + "(" + grid.getN() + "," + grid.getN() + ")");
        System.out.println("Dimension of Big grid =" + "(" + grid.getD() + "," + grid.getD() + ")");
        System.out.println("-----------------start grid---------------");
        grid.printGrid();
        System.out.println("--------------------------------------------");

    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getGoal() {
        return goal;
    }

    public void setGoal(Grid goal) {
        this.goal = goal;
    }
}
