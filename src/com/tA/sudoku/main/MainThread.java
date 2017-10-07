package com.tA.sudoku.main;

import com.tA.sudoku.protocol.Solver;
import com.tA.sudoku.protocol.SudokuMap;

public class MainThread {

	public static void main(String[] args) {
		/*int[][] in = {{0,7,0, 0,1,6, 0,9,0},
		  			   {0,0,0, 0,0,0, 0,0,0},
		  			   {0,9,0, 7,3,0, 0,6,0},
		  			   {0,3,2, 0,0,0, 0,0,7},
		  			   {9,0,0, 4,2,0, 1,0,3},
		  			   {0,0,0, 0,0,1, 0,0,9},
		  			   {3,0,0, 8,0,0, 0,0,0},
		  			   {8,0,5, 0,9,7, 0,0,1},
		  			   {7,0,0, 0,0,0, 5,8,0}};*/
		/*int[][] in = {{0,0,8, 0,9,0, 0,0,0},
		  			   {0,7,0, 0,0,0, 2,8,0},
		  			   {0,6,4, 1,0,0, 3,0,9},
		  			   {0,0,0, 8,0,5, 9,0,0},
		  			   {5,0,0, 0,0,0, 0,0,1},
		  			   {0,0,9, 3,0,4, 0,0,0},
		  			   {8,0,2, 0,0,7, 5,6,0},
		  			   {0,9,7, 0,0,0, 0,1,0},
		  			   {0,0,0, 0,6,0, 7,0,0}};*/
		int[][] in = {{0,0,0, 7,0,2, 0,0,0},
		  			   {1,0,0, 0,4,0, 0,0,7},
		  			   {6,5,0, 0,0,0, 0,9,4},
		  			   {4,7,0, 8,0,1, 0,6,2},
		  			   {0,0,0, 0,0,0, 0,0,0},
		  			   {5,8,0, 2,0,9, 0,1,3},
		  			   {8,6,0, 0,0,0, 0,7,5},
		  			   {9,0,0, 0,6,0, 0,0,8},
		  			   {0,0,0, 9,0,8, 0,0,0}};
		long start = System.currentTimeMillis();
		SudokuMap map = new SudokuMap();
		map.init(in);
		Solver solver = new Solver();
		solver.setMap(map);
		if (solver.scanInitialMap()) {
			map.printResult();
		}else if (Solver.stack.size() > 0) {
			solver.solveByStack();
		}else {
			System.out.println("Something is wrong.");
		}
		System.out.println("Time : " + (System.currentTimeMillis()-start) + " ms");

	}

}
