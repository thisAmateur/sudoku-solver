package com.tA.sudoku.protocol;

/**
 * <pre>
 * 全部9*9个Node组成的Map
 * @author thisAmateur
 *
 */
public class SudokuMap {
	/** Node数组 */
	private Node[][] nodeMap = new Node[9][9];
	
	/** 已经填好的点的个数 */
	private int solvedNodesNum;
	
	/**
	 * 根据坐标读Node
	 * @param row 横坐标
	 * @param col 纵坐标
	 * @return Node
	 */
	public Node getNode(int row, int col) {
		return nodeMap[row][col];
	}
	
	/**
	 * 根据横坐标读一行Node
	 * @param index 横坐标
	 * @return Node[]
	 */
	public Node[] getRow(int index) {
		if (nodeMap[index] != null) {
			return nodeMap[index];
		}
		return null;
	}
	
	/**
	 * 根据纵坐标读一行Node
	 * @param index 纵坐标
	 * @return Node[]
	 */
	public Node[] getCol(int index) {
		Node[] result = new Node[9];
		for (int i=0; i<9; i++) {
			result[i] = nodeMap[i][index];
		}
		return result;
	}
	
	/**
	 * 根据当前点的坐标，读当前宫格的9个点
	 * @param row 横坐标
	 * @param col 纵坐标
	 * @return Node[][]
	 */
	public Node[][] getArea(int row, int col) {
		Node[][] result = new Node[3][3];
		int rowStart = row/3 * 3;
		int colStart = col/3 * 3;
		for (int i=rowStart; i<rowStart+3; i++) {
			for (int j=colStart; j<colStart+3; j++) {
				result[i-rowStart][j-colStart] = nodeMap[i][j];
			}
		}
		return result;
	}
	
	/**
	 * 根据输入的9*9数组，初始化map
	 * @param in 9*9的数组
	 */
	public void init(int[][] in) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				nodeMap[i][j] = new Node(in[i][j], i, j);
			}
		}
		solvedNodesNum = 0;
	}
	
	/**
	 * 深拷贝
	 */
	@Override
	public SudokuMap clone() {
		Node[][] result = new Node[9][9];
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				result[i][j] = this.nodeMap[i][j].clone();
			}
		}
		SudokuMap map = new SudokuMap();
		map.setNodeMap(result);
		map.setSolvedNodesNum(this.solvedNodesNum);
		return map;
	}
	
	/**
	 * 已解点个数+1
	 * @return 加完之后的已解点个数
	 */
	public int addByOne() {
		return ++this.solvedNodesNum;
	}
	
	/**
	 * 打印结果
	 */
	public void printResult() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				System.out.print(nodeMap[i][j].getValue());
			}
			System.out.println();
		}
	}
	
	public Node[][] getNodeMap() {
		return nodeMap;
	}
	public void setNodeMap(Node[][] nodeMap) {
		this.nodeMap = nodeMap;
	}
	
	public int getSolvedNodesNum() {
		return solvedNodesNum;
	}
	public void setSolvedNodesNum(int value) {
		this.solvedNodesNum = value;
	}
}
