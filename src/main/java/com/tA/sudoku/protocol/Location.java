package com.tA.sudoku.protocol;

/**
 * <pre>
 * 坐标
 * map总共9*9个点，左上角为(0,0)原点
 * 横纵坐标范围为[0,8]
 * @author thisAmateur
 *
 */
public class Location {
	/** 横坐标 */
	private int row;
	
	/** 纵坐标 */
	private int col;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "row: " + this.row + ", col: " + this.col;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
}
