package com.tA.sudoku.protocol;

/**
 * 解分支的存档，存在栈上
 * @author thisAmateur
 *
 */
public class Memory {
	/** 解分支 分叉点 */
	private Node node;
	
	/** 分叉前的map存档 */
	private SudokuMap map;
	
	public Memory(Node node, SudokuMap map) {
		this.node = node;
		this.map = map;
	}
	
	public Node getNode() {
		return node;
	}
	
	public SudokuMap getMap() {
		return map;
	}
}
