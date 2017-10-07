package com.tA.sudoku.protocol;

import java.util.Stack;

import com.tA.sudoku.utils.Const;
import com.tA.sudoku.utils.SolveUtils;

/**
 * 解题者
 * @author thisAmateur
 *
 */
public class Solver {
	/** 用来放Memory的栈 */
	public static Stack<Memory> stack = new Stack<Memory>();
	
	/** 要解的目标map */
	private SudokuMap map;
	
	/**
	 * 处理刚初始化的map
	 * @return boolean 当前map是否已经填完
	 */
	public boolean scanInitialMap() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (map.getNode(i, j).getValue() != 0) {
					SolveUtils.solveByOneNode(map, i, j);
				}
			}
		}
		int scanRst = Const.FALSE;
		do {
			scanRst = SolveUtils.scanForStableNode(map);
		}while (scanRst == Const.TRUE);
		//判断是否map已经填好了
		if (SolveUtils.isAllSolved(map)) {
			return true;
		}
		createMemoryAndPush(map);
		return false;
	}
	
	/**
	 * 递归处理'解分支栈'上的memory
	 */
	public void solveByStack() {
		Memory mem = stack.elementAt(stack.size()-1);
		
		Node node = mem.getNode();
		SudokuMap map = mem.getMap().clone();
		int row = node.getLocation().getRow();
		int col = node.getLocation().getCol();
		
		if (!SolveUtils.solveByOneNode(map, row, col)) {
			//如果解错
			popAndPush();
			solveByStack();
			return;
		}
		int scanRst = Const.FALSE;
		do {
			scanRst = SolveUtils.scanForStableNode(map);
		}while (scanRst == Const.TRUE);
		if (scanRst == Const.WRONG) {
			//如果解错
			popAndPush();
			solveByStack();
			return;
		}
		//判断是否map已经填好了
		if (SolveUtils.isAllSolved(map)) {
			System.out.println("all solved");
			map.printResult();
			return;
		}
		createMemoryAndPush(map);
		solveByStack();
	}
	
	/**
	 * <pre>
	 * 当发现当前解分支是错误的时候，需要回溯栈顶的存档，并开始新的分支
	 * 如果当前栈顶的存档的分支点的每个候选值都尝试完了，需要递归处理
	 */
	public void popAndPush() {
		Memory mem = stack.pop();
		Node node = mem.getNode();
		if (node.getCandidates().size() == 0) {
			popAndPush();
			return;
		}
		int guessValue = (Integer)node.getCandidates().toArray()[0];
		node.killCandidate(guessValue);
		node.setValue(guessValue);
		Memory memory = new Memory(node, mem.getMap());
		stack.push(memory);
	}
	
	/**
	 * <pre>
	 * 根据当前map中候选值个数最少的待定点，创建新的解分支存档
	 * 创建完push到栈中
	 * @param map SudokuMap
	 */
	public void createMemoryAndPush(SudokuMap map) {
		Node nodeNext = SolveUtils.findNodeHasMinCandidates(map);
		int guessValue = (Integer)nodeNext.getCandidates().toArray()[0];
		nodeNext.killCandidate(guessValue);
		nodeNext.setValue(guessValue);
		Memory memNext = new Memory(nodeNext, map);
		stack.push(memNext);
	}
	
	public void setMap(SudokuMap map) {
		this.map = map;
	}

}
