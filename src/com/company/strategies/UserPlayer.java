package com.company.strategies;

import com.company.structure.Action;
import com.company.structure.Cell;
import com.company.structure.CellType;
import com.company.structure.Grid;

import java.util.List;
import java.util.Scanner;

public class UserPlayer {
    int x;
    int y;
    Grid start;
    Grid goal;
    Action action;
    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public UserPlayer(Action action) {
        this.action = action;
        InitializeGame();

    }

    public void play() {
        boolean EndGame = true;
        Grid grid = start;
        while (EndGame) {
            if (action.isGoal(grid, goal)) {
                System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "you are win " + ANSI_RESET);
                System.out.println("---------------------------------------------------------------");
                break;
            }

            List<Grid> grids = action.getNext(grid);
            System.out.println("Number of possible state:" + grids.size());
            for (Grid grid2 : grids) {
                System.out.println("-------------------------------------------");
                grid2.printGrid();
            }
            System.out.println("*******************************************");

            System.out.print("Enter X :  ");
            x = sc.nextInt();
            System.out.print("Enter Y :  ");
            y = sc.nextInt();
            grid = action.move(x, y, grid);
            System.out.println("grid hash code: " + grid.hashCode());
            System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "number of move =" + action.getCountMove() + ANSI_RESET);
            System.out.println();
            grid.printGrid();
            System.out.println();
            System.out.println("--------------------------------");

        }
    }

    public void InitializeGame() {
        System.out.print("Enter level of game: ");
        int level = sc.nextInt();
        start = new Grid(level);
        goal = new Grid(level);
        start.StartGrid();
        goal.setGoalGrid();
        System.out.println("level =" + level);
        System.out.println("Dimension of color grid =" + "(" + start.getN() + "," + start.getN() + ")");
        System.out.println("Dimension of Big grid =" + "(" + start.getD() + "," + start.getD() + ")");
        System.out.println("-----------------start grid---------------");
        System.out.println("start hash code: " + start.hashCode());
        start.printGrid();
        System.out.println("--------------------------------------------");
        System.out.println("-----------------goal grid---------------");
        System.out.println("goal hash code: " + goal.hashCode());
        goal.printGrid();
        System.out.println("--------------------------------------------");

    }


}
