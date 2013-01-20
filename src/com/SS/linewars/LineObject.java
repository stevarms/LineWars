package com.SS.linewars;

public class LineObject {
	
	private int x,y,x2,y2;
	
	public LineObject(int x, int y, int x2, int y2) {
		
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
}