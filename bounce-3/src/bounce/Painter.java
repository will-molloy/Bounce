package bounce;

import java.awt.Color;
import java.awt.Image;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Ian Warren -- Updated by Will Molloy
 * 
 */
public interface Painter {
	
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Fills a rectangle shape with a solid color. 
	 * Parameters: x, y the coordinates of the rectangle.
	 * Width, height the dimensions of the rectangle.
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * Returns the current java.awt.Color() value.
	 */
	public Color getColour();
	
	/**
	 * Given a java.awt.Color() value sets the color.
	 */
	public void setColour(Color c);
	
	/**
	 * Paints a filled oval with a solid colour.
	 */
	public void fillOval(int x, int y, int width, int height);
	
	/**
	 * Translates the origin of the graphics context to the 
	 * point (x, y) in the current coordinate system.
	 */
	public void translate(int x, int y);
	
	/**
	 * Draws a String of centered text given the centre co-ords (x, y) 
	 * where it should be drawn.
	 */
	public void drawCenteredText(String text, int x, int y);
	
	/**
	 * Paints a rectangle image
	 */
	public void drawImage(Image img, int x, int y, int width, int height);

	/**
	 * Paints a hexagon by drawing straight lines in a clockwise fashion.
	 * @param w: width
	 * @param h: height
	 * @param x,y the co-ordinates of the top left corner 
	 */
	public void drawHexagon(int w, int h, int x, int y) ;

	/**
	 * Paints a diamond by drawing straight lines in a clockwise fashion.
	 * @param w: width
	 * @param h: height
	 * @param x,y the co-ordinates of the top left corner 
	 */
	public void drawDiamond(int _width, int _height, int _x, int _y);
}	
