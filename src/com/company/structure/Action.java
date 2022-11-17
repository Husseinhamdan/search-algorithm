package com.company.structure;

import java.util.ArrayList;
import java.util.List;

public class Action {
    Grid grid;
    Grid goal;
    Cell[][] cells;
   private int CountMove = 0;

    public int getCountMove() {
        return CountMove;
    }

    public void setCountMove(int countMove) {
        CountMove = countMove;
    }

    public Grid getGrid() {
        return grid;
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

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Action(Grid grid, Grid goal) {
        this.grid=grid;
        this.goal=goal;
        this.cells=grid.getCells();
    }

    public boolean checkBoundary(int x, int y, Cell[][] cells) {
        if (x < cells.length && x >= 0 && y >= 0 && y < cells.length) {
            return true;
        }

        return false;
    }

    public boolean checkMoves(int rowSpace,int columnSpace,int x, int y) {
        if (checkBoundary(x,y,this.cells)){
            if (this.cells[x][y].getType()!=CellType.BLOCK && x == rowSpace || y == columnSpace) {
                return true;
            }
        }

        return false;
    }
    public boolean equals(Grid grid) {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                if (this.cells[i][j].getType() != grid.getCells()[i][j].getType())
                    return false;
            }
        }
        return true;
    }
    public boolean isGoal(){
        if (this.equals(this.goal)){
            return true;
        }
        return false;
    }
    public Grid move(int x, int y) {
        this.CountMove++;
        CellType type=cells[x][y].getType();
        int row = 0, column = 0;
        SearchSpace:
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j <cells.length; j++) {
                if (cells[i][j].getType() == CellType.SPACE) {
                    row = i;
                    column = j;
                    break SearchSpace;
                }
            }
        }
        if(checkMoves(row,column,x,y)){
            grid.getCells()[row][column].setType(type);
            grid.getCells()[x][y].setType(CellType.SPACE);
        }
        else{
            System.out.println("a cell can not moves");
        }
        Grid grid1 = new Grid(this.grid);
        return grid1;

    }
}
