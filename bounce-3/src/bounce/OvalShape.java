package bounce;

/**
 * Represents an Oval Shape.
 * 
 * @Author: Will Molloy
 */

public class OvalShape extends Shape {

	/**
	 * Default constructor that will create an OvalShape instance with default values.
	 */
	public OvalShape(){
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
	public OvalShape(int x, int y, int deltaX, int deltaY){
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
	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	/**
	 * Constructor with for text support within a Shape instance. With
	 * specified x, y, deltaX, deltaY, width and height values.
	 */
	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/**
	 * Paints an OvalShape object using the supplied Painter object.
	 */
	@Override
	protected void doPaint(Painter painter) {
		painter.drawOval(_x, _y, _width, _height);
	}
}
