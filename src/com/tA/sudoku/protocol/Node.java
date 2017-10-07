package com.tA.sudoku.protocol;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * 每个单独的点
 * map共有9*9个点
 * @author thisAmateur
 *
 */
public class Node {
	/** 实际填的值，范围[0,9]，0表示该点未解 */
	private int value = 0;
	
	/** 该点的坐标，横纵坐标从0~8，左上角的(0,0)是原点 */
	private Location location = null;
	
	/** 可能的值 */
	private Set<Integer> candidates = null;
	
	/**
	 * 构造函数
	 * @param value 要填的值
	 * @param row 横坐标
	 * @param col 纵坐标
	 */
	public Node(int value, int row, int col) {
		this.value = value;
		this.location = new Location(row,col);
		Set<Integer> initCandidates = new HashSet<Integer>();
		for (int i=1; i<=9; i++) {
			initCandidates.add(i);
		}
		if (value != 0) {
			initCandidates.remove(value);
		}
		this.candidates = initCandidates;
	}
	
	/**
	 * 构造函数，带初始化候选值
	 * @param value 要填的值
	 * @param row 横坐标
	 * @param col 纵坐标
	 * @param candidates 初始化候选值
	 */
	public Node(int value, int row, int col, Set<Integer> candidates) {
		this.value = value;
		this.location = new Location(row, col);
		this.candidates = new HashSet<Integer>();
		this.candidates.addAll(candidates);
	}
	
	/**
	 * 删除候选值
	 * @param value 被删除的候选值
	 * @return boolean 删除之后，该点的候选值个数是否为0
	 */
	public boolean killCandidate(Integer value) {
		candidates.remove(value);
		return candidates.size() != 0;
	}
	
	/**
	 * 深拷贝
	 */
	@Override
	public Node clone() {
		Node cloneNode = new Node(this.value, this.location.getRow(), this.location.getCol(), this.candidates);
		return cloneNode;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("value: ").append(this.value)
		.append("\r\n")
		.append("location: ").append(this.location)
		.append("\r\n")
		.append("candidates:");
		for (Integer i : this.candidates) {
			sb.append(i).append(",");
		}
		return sb.toString();
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public Set<Integer> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<Integer> candidates) {
		this.candidates = candidates;
	}
	
	public Location getLocation() {
		return location;
	}
}
