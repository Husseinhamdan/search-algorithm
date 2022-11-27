package com.company.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Grid {
    //dimension of small grid (color grid)
    private int n;
    //dimension of main grid (big grid)
    private int d;
    //    level of game
    private int level;
    private int depth;
    private int cost;
    private int MaxCost;
    private int heuristic = 0;
    private int AStarCost=0;

    public int getAStarCost() {
        return AStarCost;
    }

    public void setAStarCost() {
        this.AStarCost =this.MaxCost+this.heuristic;
    }

    public int getCost() {
        return cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public void calcHeuristic() {
        if (this.cells[n-1][n-1].getType() != CellType.SPACE){
            this.heuristic += 1;
        }
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    if (this.cells[i][j].getType() != CellType.BLUE) {
                        this.heuristic += 1;
                    }
                }
        for (int i = this.n - 1; i < this.d; i++)
            for (int j = this.n - 1; j < this.d; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    if (this.cells[i][j].getType() != CellType.RED) {
                        this.heuristic += 1;
                    }
                }
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMaxCost() {
        return MaxCost;
    }

    public void setMaxCost() {
        MaxCost = this.parent.getMaxCost() + cost;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // create Main Grid
    private Cell[][] cells;
    //children
    List<Grid> nextState;
    //parent grid
    Grid parent;
    String Code = "";

    public void setCode() {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                if (this.cells[i][j].getType() == CellType.BLOCK) {
                    this.Code += "X";
                } else if (this.cells[i][j].getType() == CellType.RED) {
                    this.Code += "R";
                } else if (this.cells[i][j].getType() == CellType.BLUE) {
                    this.Code += "B";
                } else {
                    this.Code += " ";
                }
            }

        }
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public List<Grid> getNextState() {
        return nextState;
    }

    public void setNextState(List<Grid> nextState) {
        this.nextState = nextState;
    }

    public Grid getParent() {
        return parent;
    }

    public void setParent(Grid parent) {
        this.parent = parent;
    }


    // color console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public Grid(int level) {
        this.level = level;
        this.n = level + 1;
        this.d = n + level;
        this.depth = 1;
        this.cost = 1;
        this.MaxCost = 0;
        this.InitializeGrid();
    }

    public Grid(int n, int d, Cell[][] cells) {
        this.n = n;
        this.d = d;
        this.cells = new Cell[d][d];
        this.depth = 1;
        this.cost = 1;
        this.MaxCost = 0;
        for (int i = 0; i < this.cells.length; i++)
            for (int j = 0; j < this.cells.length; j++) {
                this.cells[i][j] = new Cell(cells[i][j]);
            }
    }

    public Grid(Grid that) {
        this(that.getN(), that.getD(), that.getCells());
    }

    public void InitializeGrid() {
        this.cells = new Cell[this.d][this.d];
        for (int i = 0; i < this.cells.length; i++)
            for (int j = 0; j < this.cells.length; j++) {
                this.cells[i][j] = new Cell(i, j);
                this.cells[i][j].setType(CellType.BLOCK);
            }
        this.cells[this.n - 1][this.n - 1].setType(CellType.SPACE);
    }

    public void StartGrid() {

        this.createBlueGrid();
        this.createRedGrid();
        this.setCode();


    }

    public void setGoalGrid() {

        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.getCells()[i][j].setType(CellType.BLUE);
                }
        for (int i = this.n - 1; i < this.d; i++)
            for (int j = this.n - 1; j < this.d; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.getCells()[i][j].setType(CellType.RED);
                }
        setCode();

    }

    public void createRedGrid() {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.cells[i][j].setType(CellType.RED);
                }
    }

    public void createBlueGrid() {
        for (int i = this.n - 1; i < this.d; i++)
            for (int j = this.n - 1; j < this.d; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.cells[i][j].setType(CellType.BLUE);
                }

    }

    public void printGrid() {

        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                if (this.cells[i][j].getType() == CellType.BLOCK) {
                    System.out.print(ANSI_WHITE_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);

                } else if (this.cells[i][j].getType() == CellType.RED) {

                    System.out.print(ANSI_RED_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
                } else if (this.cells[i][j].getType() == CellType.BLUE) {
                    System.out.print(ANSI_BLUE_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);

                } else {

                    System.out.print(ANSI_BLACK_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
                }

            }
            System.out.println();
        }


    }

    public void printCode() {


        System.out.println("code :" + Code);


    }

    @Override
    public int hashCode() {            //Hashcode generated from String version of board

        return Code.hashCode();

    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getN() {
        return n;
    }

    public int getD() {
        return d;
    }


}
