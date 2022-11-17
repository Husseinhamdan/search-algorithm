package com.company.structure;

public class Cell {
    CellType type;
    private int x;
    private int y;

    public CellType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setType(CellType type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, CellType cellType) {
        this.x = x;
        this.y = y;
        this.type = cellType;
    }
    public Cell(Cell that){
        this(that.getX(),that.getY(),that.getType());
    }

    public boolean IsSpace() {
        if (this.type == CellType.SPACE)
            return true;
        return false;
    }

    public boolean IsBlue() {
        if (this.type == CellType.BLUE)
            return true;
        return false;
    }

    public boolean IsRed() {
        if (this.type == CellType.RED)
            return true;
        return false;
    }

    public boolean IsBlock() {
        if (this.type == CellType.BLOCK)
            return true;
        return false;
    }
}
