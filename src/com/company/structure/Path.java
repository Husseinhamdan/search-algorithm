package com.company.structure;

import java.util.ArrayList;
import java.util.List;

public class Path {

    List<Grid> path;

    public Path(Grid initialNode, Grid goalNode) {  //the arguments are goalNode, info and intialNode so a path can be found.
        path = this.getPath(initialNode, goalNode);
    }


    private List<Grid> getPath(Grid initialNode, Grid goalNode) {  //given a goalNode and initialNode this method uses node's parents to trace it's way back up
        Grid tempNode = goalNode;
        List<Grid> list = new ArrayList<Grid>();

        while (!(tempNode.equals(initialNode))) {
            list.add(tempNode);
            tempNode = tempNode.getParent();

        }
        list.add(initialNode);
        return list;  // a list of the path is returned in reverse order
    }


    public void printPath() {
        int size = path.size();
        System.out.println("number of steps:"+size);
        for (int i = size - 1; i >= 0; i--) {
            path.get(i).printGrid();
            System.out.println("--------------------------------");
        }

    }
}
