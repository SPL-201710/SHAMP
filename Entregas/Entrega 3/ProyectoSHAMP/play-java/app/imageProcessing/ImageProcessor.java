package imageProcessing;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;

import javax.imageio.ImageIO;

public final class ImageProcessor 
{
	public static File applyBlackandWhite(File originalImage,String convertedImage) throws Exception
	{
		
        BufferedImage grayScale;
		try
		{
			grayScale = ImageIO.read(originalImage);
            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(grayScale, grayScale);
            File outputfile = new File(convertedImage);
            ImageIO.write(grayScale,"png",outputfile);
            return outputfile;
            
		}
		catch(Exception ex)
		{
			throw new Exception("Se ha presentado un error el aplicar el filtro Black and White: " + ex.getMessage());
		}
	}
	
	public static String applySephia(String originalImage)
	{
		return null;
	}
	
	public static String applyBlur(String originalImage)
	{
		return null;
	}
	
	public static File applyNegative(File originalImage, String convertedImage) throws Exception
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(originalImage);
            
			int width = img.getWidth();
	        int height = img.getHeight();
	        //convert to negative
	        for(int y = 0; y < height; y++){
	            for(int x = 0; x < width; x++){
	                int p = img.getRGB(x,y);
	                int a = (p>>24)&0xff;
	                int r = (p>>16)&0xff;
	                int g = (p>>8)&0xff;
	                int b = p&0xff;
	                //subtract RGB from 255
	                r = 255 - r;
	                g = 255 - g;
	                b = 255 - b;
	                //set new RGB value
	                p = (a<<24) | (r<<16) | (g<<8) | b;
	                img.setRGB(x, y, p);
	            }
	        }
			
			
            File outputfile = new File(convertedImage);
            ImageIO.write(img,"png",outputfile);
            return outputfile;
            
		}
		catch(Exception ex)
		{
			throw new Exception("Se ha presentado un error el aplicar el filtro Black and White: " + ex.getMessage());
		}
	}
	
}
