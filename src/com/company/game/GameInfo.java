package com.company.game;

import com.company.structure.Cell;
import com.company.structure.CellType;
import com.company.structure.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


public class GameInfo {
    //level of game
    private int level;
    List<Grid> AllNodes = new ArrayList<Grid>();
    //dimension of small grid (color grid)
    private int n;
    //dimension of main grid (big grid)
    private int d;
    public Stack<Grid> stack;
    public HashMap<Integer, Grid> visited;


    public GameInfo(int level) {
        this.level = level;
        this.n = level + 1;
        this.d = this.n + level;
        stack = new Stack<Grid>();
        visited = new HashMap<Integer,Grid>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Grid> getAllNodes() {
        return AllNodes;
    }

    public void setAllNodes(List<Grid> allNodes) {
        AllNodes = allNodes;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
