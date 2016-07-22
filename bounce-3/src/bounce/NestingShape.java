package bounce;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a NestingShape: A shape which can contain other shapes.
 * @author Will Molloy
 */
public class NestingShape extends Shape {

	private List<Shape> _childShapes = new ArrayList<Shape>();	

	/**
	 * NestingShape with default values.
	 */
	public NestingShape(){
		super();
	}

	/**
	 * NestingShape with specified initial position.
	 */
	public NestingShape(int x, int y){
		super(x, y);
	}

	/**
	 * NestingShape with specified initial position and speed.
	 */
	public NestingShape(int x, int y, int deltaX, int deltaY){
		super(x, y, deltaX, deltaY);
	}

	/**
	 * NestingShape with specified initial position, speed and dimensions.
	 */
	public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height){
		super(x, y, deltaX, deltaY, width, height);
	}

	/**
	 * Constructor with for text support within a Shape instance. With
	 * specified x, y, deltaX, deltaY, width and height values.
	 */
	public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}

	/** 
	 * Moves a NestingShapes children
	 */
	@Override
	protected void shapeHasMoved(){
		for (Shape s : _childShapes){	
			s.move(_width, _height);	// Move child using its parents width/height as the boundary
		}
	}

	/**
	 * Paints a NestingShape by drawing a rectangle around the edge and then it's children.
	 */
	protected void doPaint(Painter painter){
		
		paintOuterBoundary(painter);
		translateCoordinatesAndPaintChildren(painter);	
	}

	private void paintOuterBoundary(Painter painter){
		painter.drawRect(_x, _y, _width, _height);
	}
	
	private void translateCoordinatesAndPaintChildren(Painter painter){
		
		painter.translate(_x, _y);		// translate coordinates to this NestingShape instances
		
		for (Shape s : _childShapes){	
			s.paint(painter);			
		}
		painter.translate(-_x, -_y);	// Revert translation
	}
	
	/**
	 * Attempts to add a Shape to a NestingShape object. If successful a two way linked is
	 * established between a NestingShape and the newly added shape.
	 * @param shape the Shape object to be added.
	 * @throws IllegalArgumentException thrown if an attempt to add an already added shape or
	 * a shape that is too big to fit inside the NestingShape's boundary box.
	 */
	void add(Shape shape) throws IllegalArgumentException {	

		if (shapeIsContainedWithinANestingShape(shape)){	
			throw new IllegalArgumentException("Shape: " + shape.getClass() + " is already contained within a Nesting Shape");
		} 
		else if (!(shapeWillFitInThisNestingShape(shape))){
			throw new IllegalArgumentException("Shape: " + shape.getClass() + " cannot fit within its parent: " + this.getClass());
		} 
		else {
			linkShapeWithThisNestingShape(shape); 
		}
	}

	private boolean shapeIsContainedWithinANestingShape(Shape shape){
		if (shape.parent() != null || this.contains(shape)){	
			return true;
		}
		return false;
	}

	private boolean shapeWillFitInThisNestingShape(Shape shape){
		if (shape._width + shape._x > _width){
			return false;
		} else if (shape._height + shape._y > _height){
			return false;
		}
		return true;
	}

	// Establish two-way link between a Shape and a NestingShape
	private void linkShapeWithThisNestingShape(Shape shape){
		_childShapes.add(shape);		// NestingShape -> shape 
		shape._parent = this;			// shape -> NestingShape 
	}

	// Remove the two-way link between a NestingShape and the specified child Shape
	void remove(Shape shape){
		_childShapes.remove(indexOf(shape));	
		shape._parent = null;					
	}

	/**
	 * Returns the shape at the specified position within a NestingShapes list of children.
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		return _childShapes.get(index);
	}

	/**
	 * Returns the number of children Shapes contained within a NestingShape object.
	 */
	public int shapeCount(){
		return _childShapes.size();
	}

	/**
	 * Returns the index of the specified shape within its NestingShape parent instance.
	 */
	public int indexOf(Shape shape){
		return _childShapes.indexOf(shape);
	}

	/**
	 * Returns true if the Shape argument is a child of the NestingShape
	 * object on which this method is called, false otherwise.
	 */
	public boolean contains(Shape shape){
		return _childShapes.contains(shape);
	}
}
