package com.company.strategies;

import com.company.structure.Action;
import com.company.structure.Grid;
import com.company.structure.Path;

import java.util.*;

public class BFS {
    Grid start;
    Grid goal;
    Action action;
    public Queue<Grid> queue;
    public HashMap<Integer, Grid> visited;
    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream

    public BFS(Action action) {
        this.action = action;
        InitializeGame();
        queue = new LinkedList<Grid>();
        visited = new HashMap<Integer, Grid>();

    }

    public boolean search() {
        Grid node = start;
        queue.add(node);
        while (!(queue.isEmpty())) {
            node.printGrid();
            System.out.println("---------------------------------------");
            node = queue.remove();
            visited.put(node.hashCode(), node);
            if (action.isGoal(node, goal)) {
                node.printGrid();
                System.out.println("***********BFS************");
                System.out.println("number of visited node: " + visited.size());
                System.out.println("depth :" + node.getDepth());
                Path path = new Path(start, node);
                path.printPath();
                return true;
            }
            List<Grid> list = action.getNext(node);
            for (Grid temp : list) {
                boolean ans = visited.containsKey(temp.hashCode());
                if (ans == false) {
                    if (!(queue.contains(temp))) { //checking the stack for duplicate children
                        queue.add(temp);
                    }

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
