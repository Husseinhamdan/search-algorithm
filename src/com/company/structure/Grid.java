package com.company.structure;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    //dimension of small grid (color grid)
    private int n;
    //dimension of main grid (big grid)
    private int d;
    // create Main Grid
    private Cell[][] cells;
    //children
    List<Grid> nextState;
    //parent grid
    Grid parent;

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

    public Grid(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public Grid(int n, int d, Cell[][] cells) {
        this.n = n;
        this.d = d;
        this.cells = new Cell[d][d];
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

    public void createGrid() {
        InitializeGrid();
        createBlueGrid();
        createRedGrid();

    }

    public void createGoalGrid() {
        InitializeGrid();
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.cells[i][j].setType(CellType.BLUE);
                }
        for (int i = this.n - 1; i < this.d; i++)
            for (int j = this.n - 1; j < this.d; j++)
                if (i == this.n - 1 && j == this.n - 1) {
                    continue;
                } else {
                    this.cells[i][j].setType(CellType.RED);
                }

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
        int num = 0;

        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                num++;
                if (this.cells[i][j].getType() == CellType.BLOCK) {
                    System.out.print(ANSI_WHITE_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
//                    System.out.print('X');
                } else if (this.cells[i][j].getType() == CellType.RED) {
//                    System.out.print('R');
                    System.out.print(ANSI_RED_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
                } else if (this.cells[i][j].getType() == CellType.BLUE) {
                    System.out.print(ANSI_BLUE_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
//                    System.out.print('B');
                } else {
//                    System.out.print(' ');
                    System.out.print(ANSI_BLACK_BACKGROUND + "(" + i + "," + j + ")" + ANSI_RESET);
                }
//                System.out.print(' ');
            }
            System.out.println();
        }


    }

    public List<Grid> getNext() {
        nextState =new ArrayList<Grid>();
        int row = 0, column = 0;
        SearchSpace:
        for (int i = 0; i < this.getD(); i++) {
            for (int j = 0; j < this.getD(); j++) {
                if (this.cells[i][j].type == CellType.SPACE) {
                    row = i;
                    column = j;
                    break SearchSpace;
                }
            }
        }
        for (int i = 0; i < this.getD(); i++) {
            if (this.cells[i][column].type!= CellType.BLOCK && this.cells[i][column].type!=CellType.SPACE) {
                Grid grid1 = new Grid(this);
                grid1.parent=this;
                CellType type=grid1.getCells()[i][column].getType();
                grid1.getCells()[row][column].setType(type);
                grid1.getCells()[i][column].setType(CellType.SPACE);
                this.nextState.add(grid1);
            }
        }
        for (int i = 0; i < this.getD(); i++) {
            if (this.cells[row][i].type != CellType.BLOCK && this.cells[row][i].type!=CellType.SPACE) {
                Grid grid1 = new Grid(this);
                grid1.parent=this;
                CellType type=grid1.getCells()[row][i].getType();
                grid1.getCells()[row][column].setType(type);
                grid1.getCells()[row][i].setType(CellType.SPACE);
                this.nextState.add(grid1);
            }
        }
        return this.nextState;
    }
    public int randomKey(){
        int key =97;
        key=37 * key + this.getCells().hashCode();
        return key;
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
