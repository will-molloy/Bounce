package bounce;

import java.awt.Color;
import java.awt.Image;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Ian Warren -- updated by Will Molloy
 * 
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();

	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}
	
	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}
		
	/**
	 * Logs the fillRect call.
	 */
	@Override
	public void fillRect(int x, int y, int width, int height) {
		_log.append("(filledRect " + x + "," + y + "," + width + "," + height+")");
	}

	/**
	 * Logs the setColour call.
	 */
	@Override
	public void setColour(Color color) {
		_log.append("(colour " + color + ")");
	}
	
	/**
	 * getColour() - not being logged
	 */
	@Override
	public Color getColour() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Logs the fillOval call
	 */
	@Override
	public void fillOval(int x, int y, int width, int height){
		_log.append("(filledOval " + x + "," + y + "," + width + "," + height+")");
	}

	/**
	 * translate() - not being logged
	 */
	@Override
	public void translate(int x, int y) {
	}

	/**
	 * Logs the drawCenteredText call
	 */
	@Override
	public void drawCenteredText(String text, int x, int y) {
		_log.append("(text " + text +")");
	}

	/**
	 * drawImage() - not being logged
	 */
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
	}

	/**
	 * Logs the drawHexagon call
	 */
	@Override
	public void drawHexagon(int w, int h, int x, int y) {
		_log.append("(hexagon " + w + ","+ h + ","+ x + ","+ y +")");
	}

	/**
	 * Logs the drawDiamond call
	 */
	@Override
	public void drawDiamond(int w, int h, int x, int y) {
		_log.append("(diamond " + w + ","+ h + ","+ x + ","+ y +")");
	}
}