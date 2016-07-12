package bounce.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;

/**
 * FormHandler for instantiating class ImageRectangleShape using
 * a SwingWorker to process and add the ImageRectangleShape on a 
 * background thread.
 * 
 * @author Will
 */
public class ImageShapeFormHandler<T> implements FormHandler {

	private ShapeModel _model;
	private NestingShape _nest;

	private SwingWorker<List<Form>, Form> _worker;

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
		_worker = new ImageShapeWorker(
				new ArrayList<Form>(Arrays.asList(form))
				);
		_worker.execute(); 
	}

	/**
	 * Worker class, instantiates ImageRectangleShape objects in the background process.
	 * @author Will
	 *
	 */
	private class ImageShapeWorker extends SwingWorker<List<Form>, Form> {

		List<Form> _forms;

		public ImageShapeWorker(List<Form> forms){
			_forms = forms;
		}

		/**
		 * Processes the RectangleImageShape forms on a background thread
		 */
		@Override
		protected List<Form> doInBackground() throws Exception {
			
			for (Form f : _forms){
				try { 
					processForm(f); 
				} 
				catch (Exception e) {}
			}
			return null;			
		}
		
		/**
		 * Reads form data that describes an ImageRectangleShape. Based on the 
		 * data, this SimpleImageShapeFormHandler creates a new ImageRectangleShape 
		 * object, adds it to a ShapeModel and to a NestingShape within the model.
		 * 
		 * @param form the Form that contains the ImageRectangleShape data.
		 */
		private void processForm(Form form){
			long startTime = System.currentTimeMillis();

			// Read field values from the form.
			File imageFile = (File)form.getFieldValue(File.class, ImageFormElement.IMAGE);
			int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
			int deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
			int deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);


			// Load the original image (ImageIO.read() is a blocking call).
			BufferedImage fullImage = null;
			try {
				fullImage = ImageIO.read(imageFile);
			} catch(IOException e) {
				System.out.println("Error loading image.");
			}

			int fullImageWidth = fullImage.getWidth();
			int fullImageHeight = fullImage.getHeight();

			BufferedImage scaledImage = fullImage;

			// Scale the image if necessary.
			if(fullImageWidth > width) {
				double scaleFactor = (double)width / (double)fullImageWidth;
				int height = (int)((double)fullImageHeight * scaleFactor);

				scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
				Graphics2D g = scaledImage.createGraphics();

				// Method drawImage() scales an already loaded image. The 
				// ImageObserver argument is null because we don't need to monitor 
				// the scaling operation.
				g.drawImage(fullImage, 0, 0, width, height, null);
			}

			// Create the new Shape and add it to the model.
			ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
			_model.add(imageShape, _nest);
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println("Image loading ans scaling took " + elapsedTime + "ms.");
		}	
	}
}