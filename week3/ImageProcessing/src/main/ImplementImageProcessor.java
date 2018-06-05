package main;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {

	@Override
	public Image showChanelB(Image sourceImage) {
		// TODO Auto-generated method stub
        try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("blue")));

        } catch (Exception e) {
        	return null;
        }     
	}

	@Override
	public Image showChanelG(Image sourceImage) {
		// TODO Auto-generated method stub
        try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("green")));

        } catch (Exception e) {
        	return null;
        }   
	}

	@Override
	public Image showChanelR(Image sourceImage) {
		// TODO Auto-generated method stub
        try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("red")));

        } catch (Exception e) {
        	return null;
        }   
	}

	@Override
	public Image showGray(Image sourceImage) {
		// TODO Auto-generated method stub
        try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("gray")));

        } catch (Exception e) {
        	return null;
        }   
	}

}

class colorFilter extends RGBImageFilter {
	private String color;
	
	public colorFilter(String a) {
		color = a;
		// TODO Auto-generated constructor stub
	}
	
	public int filterRGB(int arg1, int arg2, int rgb) {
		if (color == "red") {
			return (int)(rgb & 0xffff0000);
		}
		else if (color == "blue") {
			return (int)(rgb & 0xff0000ff);
		}
		else if (color == "green") {
			return (int)(rgb & 0xff00ff00);
		}
		else if (color == "gray") {
            int r = (rgb & 0x00ff0000) >> 16;
            int g = (rgb & 0x0000ff00) >> 8;
            int b = (rgb & 0x000000ff);
            int result = (int)(0.299*r + 0.587*g + 0.114*b);
            return (0xff000000 | result << 16 | result << 8 | result);
		}
		else {
			return rgb;
		}
	}
}