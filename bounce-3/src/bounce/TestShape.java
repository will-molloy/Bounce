package bounce;

import org.junit.Test;

import junit.framework.TestCase;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and RectangleShape, OvalShape and GemShape.
 * 
 * @author Ian Warren -- Updated by Will Molloy
 * 
 */
public class TestShape extends TestCase{
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)", 
				_painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.doPaint(_painter);
		shape.move(135, 10000);
		shape.doPaint(_painter);
		shape.move(135, 10000);
		shape.doPaint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"		// x:=110 cause it's hitting the boundary
				+ "(rectangle 98,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		RectangleShape shape = new RectangleShape(10, 20, -12, 15);
		shape.doPaint(_painter);
		shape.move(10000, 10000);
		shape.doPaint(_painter);
		shape.move(10000, 10000);
		shape.doPaint(_painter);
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {
		RectangleShape shape = new RectangleShape(10, 90, -12, 15);
		shape.doPaint(_painter);
		shape.move(125, 135);
		shape.doPaint(_painter);
		shape.move(125, 135);
		shape.doPaint(_painter);
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)", _painter.toString());
	}
	
	/**
	 * Test to ensure that an OvalShape paints itself correctly.
	 * I.e. a call to painter.drawOval() is made.
	 */
	@Test
	public void testOvalShape() {
		OvalShape shape = new OvalShape();
		shape.doPaint(_painter);		
		assertEquals("(oval 0,0,25,35)", _painter.toString());		
	}
	
	/**
	 * Test to ensure a small gem shape paints itself correctly.
	 * I.e. it is diamond shaped (4 sided).
	 */
	@Test
	public void testSmallGemShape() {
		GemShape shape = new GemShape(0,0,0,0,20,20);
		shape.doPaint(_painter);	
		assertEquals("(diamond 20,20,0,0)" ,_painter.toString());
		
	}

	/**
	 * Test to ensure a large gem shape paints itself correctly.
	 * I.e. it is hexagonally shaped (6 sided).
	 */
	public void testLargeGemShape() {
		GemShape shape = new GemShape(0,0,0,0,80,80);
		shape.doPaint(_painter);
		assertEquals("(hexagon 80,80,0,0)" ,_painter.toString());
	}
	
	/**
	 * Test that ensures an OvalAndRectangleShape instance will initialise itself as a red rectangle. 
	 * Also making sure the painter reverts to black.
	 */
	@Test
	public void testOvalAndRectangleShapeConstruction(){
		OvalAndRectangleShape shape = new OvalAndRectangleShape();
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=255,g=0,b=0])(filledRect 0,0,25,35)",
				_painter.toString());
	}
		
	/**
	 * Test that ensures an OvalAndRectangleShape instance will change to an oval (if it is a rectangle) 
	 * and to the next colour (red -> orange etc) after a bounce. Also making sure the painter reverts to black.
	 */
	@Test
	public void testOvalAndRectangleShapeSingleBounce(){
		OvalAndRectangleShape shape = new OvalAndRectangleShape(100, 20, 0, -20, 35, 25);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		// Orange is r:255, g:200 
		assertEquals("(colour java.awt.Color[r=255,g=0,b=0])(filledRect 100,20,35,25)"
				+ "(colour java.awt.Color[r=255,g=200,b=0])(filledOval 100,0,35,25)"
				+ "(colour java.awt.Color[r=255,g=200,b=0])(filledOval 100,20,35,25)",
				_painter.toString());
	}
}
