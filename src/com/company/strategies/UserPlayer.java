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
    Action action;
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public UserPlayer(Action action) {
        this.action = action;
    }

    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
    public void play() {
        boolean EndGame = true;
        while (EndGame) {
            if (action.isGoal()) {
                System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK+"you are win "+ ANSI_RESET);
                System.out.println("---------------------------------------------------------------");
                break;
            }
            Grid grid=action.getGrid();
            List<Grid> grids = grid.getNext();
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
            action.move(x, y);
            System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK+"number of move ="+action.getCountMove()+ ANSI_RESET);
            System.out.println();
            action.getGrid().printGrid();
            System.out.println();
            System.out.println("--------------------------------");

        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
