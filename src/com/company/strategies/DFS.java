package com.company.strategies;

import com.company.game.Game;
import com.company.structure.Action;
import com.company.structure.Grid;
import com.company.structure.Path;


import java.util.*;

public class DFS {
    Grid start;
    Grid goal;
    Action action;
    public Stack<Grid> stack;
    public HashMap<Integer, Grid> visited;
    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream

    public DFS(Action action) {
        this.action = action;
        InitializeGame();
        stack = new Stack<Grid>();
        visited = new HashMap<Integer, Grid>();

    }

    public boolean search() {
        long startTime = System.nanoTime();
        Grid node = start;
        stack.push(node);  // a stack is used to ensure LIFO
        while (!(stack.isEmpty())) {
            System.out.println("max cost : "+node.getMaxCost());
            System.out.println("cost : "+node.getCost());
            node.printGrid();
            System.out.println("---------------------------------------");
            node = stack.pop();
            visited.put(node.hashCode(), node);
            if (action.isGoal(node, goal)) {
                node.printGrid();
                System.out.println("***********DFS************");
                System.out.println("number of visited node: " + visited.size());
                System.out.println("depth :" + node.getDepth());
                long endTime = System.nanoTime();
                long durationInNano = (endTime - startTime);  //Total execution time in nano seconds
                double durationInSecond = (double) durationInNano / 1000000000;
                System.out.println("time of execution:" + durationInSecond + " seconds.");
                System.out.println("max cost : "+node.getMaxCost());
                System.out.println("---------------------------------------------------------");
                System.out.println("******** Path ************");
                System.out.println("print path: 1-yes   2-no");
                int select = sc.nextInt();
                if (select == 1) {
                    Path path = new Path(start, node);
                    path.printPath();

                }
                return true;
            }
            List<Grid> list = action.getNext(node);
            for (Grid temp : list) {
                boolean ans = visited.containsKey(temp.hashCode());
                if (ans == false) {
//                    if (!(stack.contains(temp))) {
                        stack.push(temp);
//                    }

                }
            }
        }
        return false;
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
        start.printGrid();
        System.out.println("-----------------goal grid---------------");
        goal.printGrid();
        System.out.println("--------------------------------------------");

    }


}




