package bounce;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren, Will Molloy
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		_g = g;
	}

	/**
	 * @see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * @see bounce.Painter.fillRect.
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.getColour.
	 */
	public Color getColour() {
		return _g.getColor();
	}

	/**
	 * @see bounce.Painter.setColour.
	 */
	public void setColour(Color c) {
		_g.setColor(c);
	}

	/**
	 * @see bounce.Painter.fillOval
	 */
	public void fillOval(int x, int y, int width, int height) {
		_g.fillOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.translate
	 */
	public void translate(int x, int y) {
		_g.translate(x,	y);
	}

	/**
	 * @see bounce.Painter.drawCenteredText
	 */
	public void drawCenteredText(String text, int x, int y) {
		this.setColour(Color.black);
		FontMetrics font = _g.getFontMetrics();
		int textWidth = (int) font.getStringBounds(text, _g).getWidth();

		// Calculate position (xPos, yPos) where text should be drawn 
		int xPos = x - textWidth/2;
		int yPos = y + Math.abs(font.getAscent() - font.getDescent())/2;

		_g.drawString(text, xPos, yPos);
	}

	/**
	 * @see bounce.Painter.drawImage
	 */
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}

	/**
	 * @see bounce.Painter.drawHexagon
	 */
	@Override
	public void drawHexagon(int w, int h, int x, int y) {
		drawLine(x, y+h/2, 	x+20, y);
		drawLine(x+20, y, 	x+w-20, y);			
		drawLine(x+w-20, y, x+w, y+h/2);			
		drawLine(x+w, y+h/2, x+w-20, y+h);
		drawLine(x+w-20, y+h, x+20, y+h);
		drawLine(x+20, y+h, x, y+h/2);
	}

	/**
	 * @see bounce.Painter.drawDiamond
	 */
	@Override
	public void drawDiamond(int w, int h, int x, int y) {
		drawLine(x, y+h/2, 	x+w/2, y);
		drawLine(x+w/2, y, 	x+w, y+h/2);
		drawLine(x+w, y+h/2, x+w/2, y+h);
		drawLine(x+w/2, y+h, x, y+h/2);
	}


}
