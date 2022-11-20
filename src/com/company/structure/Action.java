package com.company.structure;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private int CountMove = 0;


    public boolean checkBoundary(int x, int y, Cell[][] cells) {
        if (x < cells.length && x >= 0 && y >= 0 && y < cells.length) {
            return true;
        }

        return false;
    }

    public boolean checkMoves(int rowSpace, int columnSpace, int x, int y, Grid grid) {
        if (checkBoundary(x, y, grid.getCells())) {
            if (grid.getCells()[x][y].getType() != CellType.BLOCK && x == rowSpace || y == columnSpace) {
                return true;
            }
        }

        return false;
    }

    public Grid move(int x, int y, Grid grid) {
        this.CountMove++;
        CellType type = grid.getCells()[x][y].getType();
        int row = 0, column = 0;
        SearchSpace:
        for (int i = 0; i < grid.getD(); i++) {
            for (int j = 0; j < grid.getD(); j++) {
                if (grid.getCells()[i][j].getType() == CellType.SPACE) {
                    row = i;
                    column = j;
                    break SearchSpace;
                }
            }
        }
        if (checkMoves(row, column, x, y, grid)) {
            grid.getCells()[row][column].setType(type);
            grid.getCells()[x][y].setType(CellType.SPACE);
        } else {
            System.out.println("a cell can not moves");
        }
        Grid grid1 = new Grid(grid);
        grid1.setCode();
        return grid1;
    }

    public boolean equals(Grid grid1, Grid grid2) {
        if (grid1.hashCode() == grid2.hashCode()) {
            return true;
        }
        return false;
    }

    public boolean isGoal(Grid grid, Grid goal) {

        if (equals(grid, goal)) {
            return true;
        }
        return false;
    }

    public List<Grid> getNext(Grid grid) {
        List<Grid> nextState = new ArrayList<Grid>();
        int row = 0, column = 0;
        SearchSpace:
        for (int i = 0; i < grid.getD(); i++) {
            for (int j = 0; j < grid.getD(); j++) {
                if (grid.getCells()[i][j].type == CellType.SPACE) {
                    row = i;
                    column = j;
                    break SearchSpace;
                }
            }
        }
        for (int i = 0; i < grid.getD(); i++) {
            if (grid.getCells()[i][column].type != CellType.BLOCK && grid.getCells()[i][column].type != CellType.SPACE) {
                Grid grid1 = new Grid(grid);
                grid1.parent = grid;
                CellType type = grid1.getCells()[i][column].getType();
                grid1.getCells()[row][column].setType(type);
                grid1.getCells()[i][column].setType(CellType.SPACE);
                grid1.setCode();
                nextState.add(grid1);
            }
        }
        for (int i = 0; i < grid.getD(); i++) {
            if (grid.getCells()[row][i].type != CellType.BLOCK && grid.getCells()[row][i].type != CellType.SPACE) {
                Grid grid1 = new Grid(grid);
                grid1.parent = grid;
                CellType type = grid1.getCells()[row][i].getType();
                grid1.getCells()[row][column].setType(type);
                grid1.getCells()[row][i].setType(CellType.SPACE);
                grid1.setCode();
                nextState.add(grid1);
            }
        }
        return nextState;
    }


    public int getCountMove() {
        return CountMove;
    }

    public void setCountMove(int countMove) {
        CountMove = countMove;
    }

}
