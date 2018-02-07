package com.tA.sudoku.utils;

import com.tA.sudoku.protocol.Node;
import com.tA.sudoku.protocol.SudokuMap;

/**
 * 各种解题方法的工具类
 * @author thisAmateur
 *
 */
public final class SolveUtils {
	
	/**
	 * 构造函数
	 */
	private SolveUtils() {
	}

	/**
	 * <pre>
	 * 根据一个确定的点，删除该行、该列、该宫格内所有待定点的候选值
	 * 被删除的候选值就是确定点的实际值
	 * 同时，若某个待定点的候选值个数被删光了，说明当前解分支是错误的
	 * @param map SudokuMap
	 * @param row 确定点的横坐标
	 * @param col 确定点的纵坐标
	 * @return boolean 是否有某个待定点的候选值被删光了，即当前解分支是否正确
	 */
	public static boolean solveByOneNode(SudokuMap map, int row, int col) {
		boolean rst = true;
		map.addByOne();
		int value = map.getNode(row, col).getValue();
		process: {
			for (Node node : map.getRow(row)) {
				if (node.getValue() == 0) {
					if (!node.killCandidate(value)) {
						rst = false;
						break process;
					}
				}
			}
			for (Node node : map.getCol(col)) {
				if (node.getValue() == 0) {
					if (!node.killCandidate(value)) {
						rst = false;
						break process;
					}
				}
			}
			int rowStart = row/3 * 3;
			int colStart = col/3 * 3;
			Node node = null;
			for (int i=rowStart; i<rowStart+3; i++) {
				for (int j=colStart; j<colStart+3; j++) {
					node = map.getNode(i, j);
					if (node.getValue() == 0) {
						if (!node.killCandidate(value)) {
							rst = false;
							break process;
						}
					}
				}
			}
		}
		return rst;
	}

	/**
	 * <pre>
	 * 找到当前map中候选值最少的待定点
	 * 用来作为新的解分支的起点
	 * @param map SudokuMap
	 * @return Node
	 */
	public static Node findNodeHasMinCandidates(SudokuMap map) {
		int min = 9;
		Node result = null;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (map.getNode(i, j).getValue() == 0) {
					if (map.getNode(i, j).getCandidates().size() < min) {
						result = map.getNode(i, j);
						min = result.getCandidates().size();
					}
				}
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * 根据当前map中的各待定点候选值情况来确定某些点的实际值
	 * 如果某个待定点的候选值个数为1，则该待定点的实际值就是剩下的最后一个候选值
	 * @param map SudokuMap
	 * @return <pre>FALSE:没有一个待定点变为确定点
	 * TRUE:有待定点变为确定点
	 * WRONG:当前解分支错误
	 */
	public static int scanForStableNode(SudokuMap map) {
		int result = Const.FALSE;
		process:{
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					Node node = map.getNode(i, j);
					if (node.getValue() == 0) {
						if (node.getCandidates().size() == 1) {
							result = Const.TRUE;
							int value = (Integer)(node.getCandidates().toArray()[0]);
							node.setValue(value);
							if (!SolveUtils.solveByOneNode(map, i, j)) {
								result = Const.WRONG;
							}
							break process;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 判断一个map是否已经解完
	 * @param map SudokuMap
	 * @return boolean 当前map的已解点个数是否为81
	 */
	public static boolean isAllSolved(SudokuMap map) {
		return map.getSolvedNodesNum() == Const.ALL_NODE_NUM;
	}
}
