package bounce.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;

/**
 * FormHandler for instantiating the class ImageRectangleShape using
 * a SwingWorker to handle loading an image and scaling if nessesary on a
 * background thread.
 * 
 * @author Will Molloy
 */
public class ImageShapeFormHandler<T> implements FormHandler {

	private ShapeModel _model;
	private NestingShape _nest;
	private Form _imageForm;
	private SwingWorker<BufferedImage, Void> _worker;

	public ImageShapeFormHandler (ShapeModel model, NestingShape nest){
		_model = model;
		_nest = nest;
	}

	/**
	 * Processes a form which desbribes an ImageRectangleShape by adding
	 * the form to a StringWorker - ImageShapeWorker, which instantiates a
	 * worker thread to process images in the background.
	 */
	@Override
	public void processForm(Form form) {
		_imageForm = form;
		_worker = new ImageShapeWorker();
		_worker.execute(); 
	}

	/**
	 * Worker class --
	 * Loads and Scales (if needed) an Image on a background thread.
	 * Then instantiates an ImageRectangleShape and adds it to the animation on
	 * the ED thread.
	 * 
	 * @author Will Molloy
	 */ 
	private class ImageShapeWorker extends SwingWorker<BufferedImage, Void> {

		private File _imageFile;
		private int _width;
		private int _deltaX;
		private int _deltaY;

		private int _originalImageWidth;
		private int _originalImageHeight;

		@Override
		protected BufferedImage doInBackground() throws Exception {

			BufferedImage originalImage, scaledImage;
			
			getFieldValuesFromForm();

			originalImage = loadOriginalImageFromImageFile(_imageFile);
			getImageFieldValues(originalImage);

			// Scale the image if necessary.
			scaledImage = originalImage;
			if (_originalImageWidth > _width){
				scaledImage = scaleImage(originalImage);
			}
			
			return scaledImage;
		}

		private void getFieldValuesFromForm() {
			_imageFile = (File)_imageForm.getFieldValue(File.class, ImageFormElement.IMAGE);
			_width = _imageForm.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
			_deltaX = _imageForm.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
			_deltaY = _imageForm.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);
		}

		// Load the original image (ImageIO.read() is a blocking call).
		private BufferedImage loadOriginalImageFromImageFile(File imageFile) {
			BufferedImage fullImage = null;
			try {
				fullImage = ImageIO.read(imageFile);
			} catch(IOException e) {
				System.out.println("Error loading image.");
			}
			return fullImage;
		}

		private void getImageFieldValues(BufferedImage originalImage) {
			_originalImageWidth = originalImage.getWidth();
			_originalImageHeight = originalImage.getHeight();
		}

		private BufferedImage scaleImage(BufferedImage originalImage) {
			long startTime = System.currentTimeMillis();

			BufferedImage scaledImage = originalImage;
			
			double scaleFactor = (double)_width / (double)_originalImageWidth;
			int height = (int)((double)_originalImageHeight * scaleFactor);

			scaledImage = new BufferedImage(_width,height,BufferedImage.TYPE_INT_RGB); 
			Graphics2D g = scaledImage.createGraphics();

			// Method drawImage() scales an already loaded image. The 
			// ImageObserver argument is null because we don't need to monitor 
			// the scaling operation.
			g.drawImage(originalImage, 0, 0, _width, height, null);

			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println("Image loading ans scaling took " + elapsedTime + "ms.");

			return scaledImage;
		}

		// Instantiate an ImageRectangleShape and add it to the animation -- on the ED thread
		@Override
		protected void done(){
			try {
				BufferedImage image = this.get();
				// Create the new Shape and add it to the model.
				ImageRectangleShape imageShape = new ImageRectangleShape(_deltaX, _deltaY, image);
				_model.add(imageShape, _nest);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}