package bounce;

import java.awt.Color;

/**
 * DynamicRectangle: Paint as a filled rectangle if the Shape collides with a vertical boundary,
 * paint as an outlined rectangle if the Shape collides with a horizontal boundary.
 * 
 * @author Will Molloy
 *
 */
public class DynamicRectangleShape extends Shape {

	private Color _color;
	private boolean _fill = false;	

	public DynamicRectangleShape(){
		super();
		_color = Color.BLACK;		// Default colour is black
	}

	/**
	 * Instance constructor that will create a DynamicRectangleShape with values
	 * for: Initial position and speed.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
		_color = Color.BLACK;		
	}

	/**
	 * Instance constructor that will create a DynamicRectangleShape with 
	 * values given for: Initial position, speed, dimensions and colour.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color java.awt.Color value.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color){
		super(x, y, deltaX, deltaY , width, height);
		_color = color;
	}

	/**
	 * Constructor with support for text.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color clr) {
		super(x, y, deltaX, deltaY, width, height, text);
		_color = clr;
	}

	// Change the value of _fill if a collision has occured
	@Override
	protected void shapeHasMoved(){
		
		if (shapeCollidedWithAVerticalBoundary()){			
			_fill = true;						
		} else if (shapeCollidedWithAHorizontalBoundary()){	
			_fill = false;
		}
	}


	/**
	 *  Paint method paints a DynamicRectangleShape as follows:
	 *  Initially the shape is painted with a filled colour (_fill = true by default).
	 *  If the shape bounces of the top/bottom boundary it should only be painted with an outline.
	 *  If the shape bounces of the left/right boundary it paints itself with a filled colour.
	 *  If the shape bounces of a corner, the vertical boundary takes priority; 
	 *  i.e. it paints itself with a filled colour.
	 */
	@Override
	protected void doPaint(Painter painter) {
		
		painter.setColour(_color);	// set colour of this DynamicRectangle instance

		if (_fill){
			painter.fillRect(_x, _y, _width, _height);
		} else {
			painter.drawRect(_x, _y, _width, _height);
		}
	}
}