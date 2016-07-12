package bounce;

/**
 * Represents a GemShape: a GemShape is displayed as a hexagon (6 sided)
 * if its width is greater than 40 pixels, otherwise it is displayed as a diamond (4 sided).
 * 
 * @author Will Molloy
 *
 */
public class GemShape extends Shape {
	
	public GemShape(){
		super();
	}

	/**
	 * Instance constructor with specified values for instance 
	 * variables: Initial position and speed. 
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY){
		super(x, y, deltaX, deltaY);
	}

	/**
	 * Instance constructor with specified values for instance 
	 * variables: Initial position, speed and size.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}


	/**
	 * Instance constructor with support for text.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/**
	 * 	Paints either a hexagon or diamond depending on the width of the GemShape.
	 */
	@Override
	protected void doPaint(Painter painter) {
		if (_width > 40) {
			painter.drawHexagon(_width, _height, _x, _y);
		} else {
			painter.drawDiamond(_width, _height, _x, _y);
		}
	}
}