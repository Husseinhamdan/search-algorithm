package com.company.strategies;

import com.company.game.Game;
import com.company.structure.Grid;


import java.util.List;

public class DFS {


    Game game;

    public DFS(Game game) {
        this.game = game;
    }

//    public boolean search() {
//        Grid grid = game.getGrid();
//        game.getGameInfo().stack.push(grid);
//        while (!(game.getGameInfo().stack.isEmpty())) {
//            grid.printGrid();
//            System.out.println("------------------------------------------------");
//            grid = game.getGameInfo().stack.pop();
//            game.getGameInfo().visited.add(grid);
//            if (game.getAction().equals(game.getGoal())) {
//                return true;
//            }
//
//            List<Grid> list = grid.getNext();
//            for (Grid temp : list) {
////                System.out.println(list.size());
//                boolean check = game.getGameInfo().visited.contains(grid.hashCode());
//                if (check == false) {
//                    if (!(game.getGameInfo().stack.contains(temp))) { //checking the stack for duplicate children
//                        game.getGameInfo().stack.push(temp);
//                    }
//
//
//                }
//            }
//        }
//
//        return false;
//
//
//    }
}




