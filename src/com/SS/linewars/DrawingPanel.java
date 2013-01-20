package com.SS.linewars;
import android.graphics.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawingPanel {

	private ArrayList line_list = new ArrayList();
	
	private class line_object {
		public int x,y,x2,y2;
		line_object(int x, int y, int x2, int y2) {
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	/**
	 * This function randomly picks a point along the x-axis and y-axis then draws a line across it
	 * @param BL bottom-left
	 * @param TR top-right
	 * @param HL # of horizontal lines
	 * @param VL # of vertical lines
	 */
	public ArrayList Draw_Random_Lines(Point BL, Point TR, int HL, int VL) {
		
		int min_x = BL.x; // parse the Points
		int min_y = BL.y;
		int max_x = TR.x;
		int max_y = TR.y;
		int x,y;
		
		Random rd = new Random();
		
		/*
		 * The purpose of the algorithm is to prevent any odd number integers to be randomized
		 * By dividing the random number by 2 and flooring it will force the random number to be an even integer
		 * Then multiplying it by 2 will bring it back to its original value or +/-1 if the number was odd to begin with
		 * This will make error checking a lot easier because we can take out any duplicate numbers that are being generated
		 * Also not needing to worry about lines being generated right next to each other
		 */
		for (int i=0;i<HL;i++) { // draw horizontal lines
			y = (int) Math.floor((double)(rd.nextInt(max_y-min_y)+min_y)/2)*2; // pick a point on the y-axis to draw the horizontal line
			if (line_list.contains(y) || y < min_y+2 || y > max_y-2) // check to see if the y-axis generated is already in the list
				i--;
			else {
				line_object LO = new line_object(BL.x,y,TR.x,y);
				line_list.add(LO); // put into array to check for errors
				// drawLine(BL.x,y,TR.x,y);
			}
		}
				
		
		for (int j=0; j<VL;j++) { // draw vertical lines
			x = (int) Math.floor((double)(rd.nextInt(max_x-min_x)+min_x)/2)*2; // pick a point on the x-axis to draw the vertical line
			if (line_list.contains(x) || x < min_x+2 || x > max_x-2)
				j--;
			else {
				line_object LO = new line_object(x,BL.y,x,TR.y);
				line_list.add(LO);
				// drawLine(x,BL.y,x,TR.y);
			}
		}
		
		return line_list;
	}
	
	/**
	 * This function uses an algorithm to create an n amount of points for the level
	 * @param BL bottom-left
	 * @param TR top-right
	 * @param level current level
	 */
	public void Create_Level(Point BL, Point TR, int level) {
		
		int number_of_lines = level+2; // # of lines to be drawn for the level
		Random rd = new Random();
		int HL = rd.nextInt(number_of_lines); // # of horizontal lines to be drawn
		if (HL == 0)
			HL = 1;
		int VL = number_of_lines - HL; // # of vertical lines to be drawn
		int number_of_intersections = HL * VL; // # of intersections (answer)
		line_list.add(number_of_intersections); // first element in the list is the answer
		
		Draw_Random_Lines(BL,TR,HL,VL);
	}
	/*
	public static void main (String args[]) {
		
		Point BL = new Point(0,0);
		Point TR = new Point(100,100);
		DrawingPanel DP = new DrawingPanel();
		DP.Create_Level(BL,TR,1); // coordinates (0,0),(100,100) for the drawingPanel
	}
	*/
}
