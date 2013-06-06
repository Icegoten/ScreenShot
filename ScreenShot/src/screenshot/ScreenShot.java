package screenshot;

import java.awt.AWTException; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.imageio.ImageIO; 
import java.awt.event.KeyEvent; 

public class ScreenShot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		robotTest();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void robotTest() throws Exception{
		//Get the current screen dimensions
		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int width = (int) d.getWidth();	//getWidth and getHeight return doubles by default
		int height = (int) d.getHeight();
		
		
	       try {
	        Robot robot = new Robot();
	        
	        robot.delay(5000);	//Wait 5 seconds before taking screenshot to allow time to switch to screen/window
	        
	        //Creates the screen capture of the active window
	        Image capturedScreen = robot.createScreenCapture(new Rectangle(0, 0, width, height));
	        
	        //Creates a buffered image for use to save to file
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        
	        //Creates a Graphics2D which can be used to draw into this BufferedImage
	        Graphics g = bi.createGraphics();
	        
	        //Draws as much of the image in the first parameter as you can using the x, y, width, and height of it
	        g.drawImage(capturedScreen,  0,  0,  width,  height,  null);
	        
	        //Filename of where to save the file with timestamp
	        createDirectoryIfNeeded("C:/temp/");
	        String fileNameToSaveTo = "C:/temp/screenCapture_" + createTimeStampStr() + ".jpg";
	        
	        //Write the capturedScreen to a file
	        writeImage(bi, fileNameToSaveTo, "jpg");
	        
	        System.out.println("Screen captured successfully and saved to:\n"+fileNameToSaveTo);
	        }
	        catch(AWTException e){
	        e.printStackTrace(); 

	        } 
	    }
	
	/**
	 * This method writes a buffered image to a file
	 * @param img --> BufferedImage
	 * @param fileLocation --> e.g. "C:/testImage.jpg"
	 * @param extension --> e.g. "jpg","gif","png"
	 */
	public static void writeImage(BufferedImage img, String fileLocation, String extension) {
	
		try {
			BufferedImage bi = img;
			File outputfile = new File(fileLocation);
			ImageIO.write(bi, extension, outputfile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return String representation of timestamp
	 * in the format of yyyy.MM.dd HH:mm:ss (e.g. 2001.07.04 12:08:56)
	 * @throws Exception
	 */
	public static String createTimeStampStr() throws Exception {
		Calendar mycalendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String timeStamp = formatter.format(mycalendar.getTime());
		
		return timeStamp;
	}
	
	private static void createDirectoryIfNeeded(String directoryName)
	{
	  File theDir = new File(directoryName);

	  // if the directory does not exist, create it
	  if (!theDir.exists())
	  {
	    System.out.println("creating directory: " + directoryName);
	    theDir.mkdir();
	  }
	}

}
