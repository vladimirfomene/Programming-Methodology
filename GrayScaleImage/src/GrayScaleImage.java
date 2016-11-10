/* Program to demonstrate how to produce the grayscale of 
 * and image.
 */
import acm.graphics.*;
import acm.program.*;


public class GrayScaleImage extends GraphicsProgram{
	public void run(){
		GImage image = new GImage("touboro.PNG");
		GImage grayImage = createGrayscaleImage(image);
		
		image.scale(0.75);
		add(image, 10, 10);
		
		grayImage.scale(0.75);
		add(grayImage, 680,10);
		
	}
	
	/* create a gray scale image from a GImage */
	private GImage createGrayscaleImage(GImage image){
		int[][] array = image.getPixelArray();
		int height = array.length;
		int width = array[0].length;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				int pixel = array[i][j];
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				
				int xx = computeLuminosity(r, g, b);
				
				array[i][j] = GImage.createRGBPixel(xx,xx,xx);
				
			}
		}
		return new GImage(array);
	}
	
	/* compute the luminosity of a pixel when given the individual rgb values */
	private int computeLuminosity(int r, int g, int b){
		return GMath.round(0.299 * r + 0.587 * g + 0.114 * b);
	}
}
