package com.tA.sudoku.main;

import com.tA.sudoku.protocol.Solver;
import com.tA.sudoku.protocol.SudokuMap;

public class MainThread {

    public static void main(String[] args) {
        //芬兰数学家因卡拉设计的，号称世界最难数独
        int[][] in = {{0,0,5, 3,0,0, 0,0,0},
                       {8,0,0, 0,0,0, 0,2,0},
                       {0,7,0, 0,1,0, 5,0,0},
                       {4,0,0, 0,0,5, 3,0,0},
                       {0,1,0, 0,7,0, 0,0,6},
                       {0,0,3, 2,0,0, 0,8,0},
                       {0,6,0, 5,0,0, 0,0,9},
                       {0,0,4, 0,0,0, 0,3,0},
                       {0,0,0, 0,0,9, 7,0,0}};

        // first step: init a SudokuMap Object
        SudokuMap map = new SudokuMap();
        map.init(in);
        // second step: init a Solver Object
        Solver solver = new Solver(map);
        // third step: do solve
        solver.doSolve();
    }

}
