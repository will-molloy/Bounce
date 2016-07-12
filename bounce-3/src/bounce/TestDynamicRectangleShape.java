package bounce;

import java.awt.Color;

import org.junit.Test;

import junit.framework.TestCase;

/**
 *  A class to ensure that a DynamicRectangleShape class behaves correctly
 *  
 * @author Will Molloy
 *
 */
public class TestDynamicRectangleShape extends TestCase {
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
	 *  Test to check that a DynamicRectangleShape initialises itself correctly
	 *  (with the default constructor)
	 */
	@Test
	public void testDefaultConstruction() {
		DynamicRectangleShape shape = new DynamicRectangleShape();
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 0,0,25,35)"
				,_painter.toString());
	}

	/**
	 * Test to check that a DynamicRectangleShape initialises itself correctly
	 * with the more specific constuctor which specifies the filled colour
	 */
	@Test
	public void testConstructor() {
		DynamicRectangleShape shape = new DynamicRectangleShape(200, 200, 10, 10, 20, 20, Color.RED);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=255,g=0,b=0])(rectangle 200,200,20,20)"
				,_painter.toString());		
	}

	

	/**
	 * Test to ensure that a DynamicRectangleShape paints itself correctly when it 
	 * bounces off the top boundary. i.e. it remains an outline after the bounce
	 */
	@Test
	public void testTopBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 20, 0, -20);	// default height: 35, width: 25
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,20,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,0,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,20,25,35)"
				, _painter.toString());
	}

	/**
	 * Test to ensure that a DynamicRectangleShape paints itself correctly when it 
	 * bounces of the bottom boundary. i.e. it remains as an outline after the bounce
	 */
	@Test
	public void testBottomBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 450, 0, 20);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,450,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,465,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(rectangle 100,445,25,35)"
				, _painter.toString());
	}

	/**
	 * Test to ensure that a DynamicRectangleShape paints itself correctly when it 
	 * bounces of the left boundary. i.e. it paints itself as a solid figure when
	 * hitting the boundary
	 */
	@Test
	public void testLeftBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10, 250, -15, 10);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 10,250,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 0,260,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 15,270,25,35)"
				, _painter.toString());
	}

	/**
	 * Test to ensure that a DynamicRectangleShape paints itself correctly when it 
	 * bounces of the right boundary. i.e. it paints itself as a solid figure when
	 * hitting the boundary
	 */
	@Test
	public void testRightBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(450, 250, 30, -10);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 450,250,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 475,240,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 445,230,25,35)"
				, _painter.toString());
	}
	

	
	/** 
	 * Test to ensure a DynamicRectangleShape paints itself correctly when it bounces
	 * of the top-left boundary i.e. it paints itself as a filledRect after the bounce because
	 * the vertical boundary has priority
	 */
	@Test
	public void testTopLeftBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(20, 20, -20, -20);	
		shape.doPaint(_painter);
		shape.move(400,400);
		shape.doPaint(_painter);
		shape.move(400, 400);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 20,20,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 0,0,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 20,20,25,35)",
				_painter.toString());
	}
	
	/** 
	 * Test to ensure a DynamicRectangleShape paints itself correctly when it bounces
	 * of the top-right boundary i.e. it paints itself as a filledRect after the bounce because
	 * the vertical boundary has priority
	 */
	@Test
	public void testTopRightBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(450, 20, 30, -20);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 450,20,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 475,0,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 445,20,25,35)",
				_painter.toString());
	}
	
	/** 
	 * Test to ensure a DynamicRectangleShape paints itself correctly when it bounces
	 * of the bottom-right boundary i.e. it paints itself as a filledRect after the bounce because
	 * the vertical boundary has priority
	 */
	@Test
	public void testBottomRightBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(450, 450, 30, 30);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 450,450,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 475,465,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 445,435,25,35)",
				_painter.toString());
	}
	
	/** 
	 * Test to ensure a DynamicRectangleShape paints itself correctly when it bounces
	 * of the bottom-left boundary i.e. it paints itself as a filledRect after the bounce because
	 * the vertical boundary has priority
	 */
	@Test
	public void testBottomLeftBounce() {
		DynamicRectangleShape shape = new DynamicRectangleShape(16, 450, -18, 30);	
		shape.doPaint(_painter);
		shape.move(500,500);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(colour java.awt.Color[r=0,g=0,b=0])(rectangle 16,450,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 0,465,25,35)"
				+ "(colour java.awt.Color[r=0,g=0,b=0])(filledRect 18,435,25,35)",
				_painter.toString());
	}
}
