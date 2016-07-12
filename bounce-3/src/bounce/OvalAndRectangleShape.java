package bounce;

import java.awt.Color;

/**
 * Switches between Oval And Rectangle Shape appearances, also cycles through several colours.
 * @author Will Molloy
 *
 */
public class OvalAndRectangleShape extends Shape {

	private boolean _shapeIsRectangle = true;
	private int _colorValue = 0;				
	private static final Color[] _colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

	public OvalAndRectangleShape() {
		super();
	}

	/**
	 * Instance constructor that will create a DynamicRectangleShape with values
	 * for: Initial position and speed.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public OvalAndRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	/**
	 * Constructor with support for text.
	 */
	public OvalAndRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
		

	@Override
	protected void doPaint(Painter painter) {

		// Set the color based on the color value
		painter.setColour(_colors[_colorValue]);

		if (_shapeIsRectangle){
			painter.fillRect(_x, _y, _width, _height);	
		} else {
			painter.fillOval(_x, _y, _width, _height);
		}
	}

	@Override
	protected void shapeHasMoved() {
		if (shapeCollidedWithAVerticalBoundary() || shapeCollidedWithAHorizontalBoundary()){	
			changeShape();
		}
	}

	private void changeShape() {
		_colorValue = (_colorValue+1) % (_colors.length);	// Change to the next colour value
		_shapeIsRectangle = !_shapeIsRectangle;
	}
}
