package com.company.strategies;

import com.company.structure.Action;
import com.company.structure.Grid;
import com.company.structure.Path;

import java.util.*;

public class Astar {
    Grid start;
    Grid goal;
    Action action;
    int depthTree;
    public PriorityQueue<Grid> pQueue;
    public HashMap<Integer, Grid> visited;
    Scanner sc = new Scanner(System.in);    //System.in is a standard input stream

    public Astar(Action action) {
        this.action = action;
        InitializeGame();
        pQueue = new PriorityQueue<Grid>();
        visited = new HashMap<Integer, Grid>();
        this.depthTree = 1;

    }

    public int getDepthTree() {
        return depthTree;
    }

    public void setDepthTree(int depthTree) {
        if (depthTree > this.depthTree) {
            this.depthTree = depthTree;
        }
    }

    public void setPQueue(Comparator c) {
        pQueue = new PriorityQueue<Grid>(c);
    }

    public boolean search() {
        long startTime = System.nanoTime();
        Grid node = start;
        setPQueue(new AstarComparator());
        pQueue.add(node);
        while (!(pQueue.isEmpty())) {
//            System.out.println("max cost : " + node.getMaxCost());
//            System.out.println("cost : " + node.getCost());
//            node.printGrid();
//            System.out.println("---------------------------------------");
            node = pQueue.poll();
            setDepthTree(node.getDepth());
//            if (node.getDepth() > 5000) {
//
//                continue;
//            }
            visited.put(node.hashCode(), node);
            if (action.isGoal(node, goal)) {
                node.printGrid();
                System.out.println("*********** AStar ************");
                System.out.println("number of visited node: " + visited.size());
                System.out.println("solution depth :" + node.getDepth());
                long endTime = System.nanoTime();
                long durationInNano = (endTime - startTime);  //Total execution time in nano seconds
                double durationInSecond = (double) durationInNano / 1000000000;
                System.out.println("time of execution:" + durationInSecond + " seconds.");
                System.out.println("cost of path : " + node.getMaxCost());
                System.out.println("depth tree =" + this.getDepthTree());
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
                // if not in open and close
                if (ans == false && !pQueue.contains(temp)) {
                    pQueue.add(temp);
                }
//                open condition
                else if (pQueue.removeIf(grid -> (grid.hashCode() == temp.hashCode() && grid.getMaxCost()>temp.getMaxCost()))) {
                    pQueue.add(temp);
                }
//                close condition
                else if (ans == true && visited.get(temp.hashCode()).getMaxCost() > temp.getMaxCost()) {
                    visited.get(temp.hashCode()).setAStarCost(temp.getAStarCost());
                    visited.get(temp.hashCode()).setParent(temp.getParent());
                } else continue;
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
