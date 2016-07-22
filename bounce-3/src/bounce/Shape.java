package bounce;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * @author Ian Warren, Will Molloy
 * 
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;

	protected static final int DEFAULT_Y_POS = 0;

	protected static final int DEFAULT_DELTA_X = 5;

	protected static final int DEFAULT_DELTA_Y = 5;

	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;

	protected int _nextX;

	protected int _nextY;

	// === Booleans for if a collision has occured 

	private boolean _shapeCollidedNorth;

	private boolean _shapeCollidedSouth;

	private boolean _shapeCollidedEast;

	private boolean _shapeCollidedWest;

	// === NestingShape and text support
	protected NestingShape _parent = null;		

	protected String _text;

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}

	/**
	 * Constructor with for text support within a Shape instance. With
	 * specified x, y, deltaX, deltaY, width and height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_text = text;
	}

	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width width of two-dimensional world.
	 * @param height height of two-dimensional world.
	 */
	public final void move(int animationWidth, int animationHeight) {
		
		getNextXAndYPositions();
		setCollisionsToFalse();

		if (shapeCollidedWithNorthBoundary()) {
			moveShapeSouth();
		} else if (shapeCollidedWithSouthBoundary(animationHeight)) {
			moveShapeNorth(animationHeight);
		}
		
		if (shapeCollidedWithWestBoundary()) {
			moveShapeEast();
		} else if (shapeCollidedWithEastBoundary(animationWidth)) {
			moveShapeWest(animationWidth);
		}

		updateXAndYPositions();
		
		shapeHasMoved();
	}

	private void getNextXAndYPositions() {
		_nextX = _x + _deltaX;
		_nextY = _y + _deltaY;
	}
	
	private void setCollisionsToFalse() {
		_shapeCollidedNorth = false;
		_shapeCollidedSouth = false;
		_shapeCollidedEast = false;
		_shapeCollidedWest = false;
	}

	private void updateXAndYPositions() {
		_x = _nextX;
		_y = _nextY;
	}

	private boolean shapeCollidedWithNorthBoundary(){
		_shapeCollidedNorth = _nextY <= 0 ? true : false;
		return _shapeCollidedNorth;
	}

	private boolean shapeCollidedWithSouthBoundary(int animationHeight){
		_shapeCollidedSouth = _nextY + _height >= animationHeight ? true : false;
		return _shapeCollidedSouth;
	}

	private boolean shapeCollidedWithWestBoundary(){
		_shapeCollidedWest = _nextX <= 0 ? true : false;
		return _shapeCollidedWest;
	}

	private boolean shapeCollidedWithEastBoundary(int animationWidth){
		_shapeCollidedEast = _nextX + _width >= animationWidth ? true : false;
		return _shapeCollidedEast;
	}

	private void moveShapeNorth(int animationHeight) {
		_nextY = animationHeight - _height;
		_deltaY = -_deltaY;
	}

	private void moveShapeSouth() {
		_nextY = 0;
		_deltaY = -_deltaY;
	}

	private void moveShapeWest(int animationWidth) {
		_nextX = animationWidth - _width;
		_deltaX = -_deltaX;
	}

	private void moveShapeEast() {
		_nextX = 0;
		_deltaX = -_deltaX;
	}

	/**
	 *  Optional hook method for additional behaviour within bounce.Shape.move()
	 */
	protected void shapeHasMoved(){
	}
	
	/**
	 * Method to be called by subclasses for additional behaviour on a vertical bounce
	 */
	protected boolean shapeCollidedWithAVerticalBoundary(){
		return _shapeCollidedEast || _shapeCollidedWest ? true : false;
	}

	/**
	 * Method to be called by subclasses for additional behaviour on a horizontal bounce
	 */
	protected boolean shapeCollidedWithAHorizontalBoundary(){
		return _shapeCollidedNorth || _shapeCollidedSouth ? true : false;
	}

	/**
	 * Template method to paint this shape, the mandatory hook method
	 * paintShape will take care of subclasses specific painting while this method
	 * ensures a Shape associated with text will have its text painted.
	 * @param painter the Painter object used for drawing.
	 */
	public final void paint(Painter painter){
		painter.setColour(Color.BLACK); // default Color
		doPaint(painter);	// hook
		if (isShapeAssociatedWithText()){
			paintText(painter);
		}
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 */
	protected abstract void doPaint(Painter painter);

	/**
	 * Default method for painting text, paints text with default values within
	 * the centre of a Shape.
	 */
	void paintText(Painter painter){
		int shapeCentreX = _width/2 + _x;	
		int shapeCentreY = _height/2 + _y;
		painter.drawCenteredText(_text, shapeCentreX, shapeCentreY);
	}
	/**
	 * Returns whether this Shape object contains text or not
	 */
	public boolean isShapeAssociatedWithText(){
		return _text != null ? true : false;
	}

	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}

	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}

	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}

	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}

	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}

	/**
	 * Returns this Shape's text
	 */
	public String text() {
		return _text;
	}

	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "bounce.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}

	/**
	 * Returns the NestingShape that contains the Shape that the method is called on.
	 * If the callee object is not a child within a NestingShape instance this method
	 * returns null.
	 */
	public NestingShape parent(){
		return _parent;	
	}

	/**
	 * Returns the path of the specified Shape up to its root NestingShape.
	 */
	public List<Shape> path(){

		List<Shape> path = new ArrayList<Shape>();
		Shape temp = this;
		path.add(temp);		

		// Keep adding Shape's parents 
		while (temp.parent() != null){
			temp = temp.parent();
			path.add(temp);
		}

		// Reverse the list 
		Collections.reverse(path);
		return path;
	}
}
