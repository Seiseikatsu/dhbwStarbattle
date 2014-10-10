package com.starbattle.mapeditor.resource;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ResourceLoader {

	private static Image notFound = new BufferedImage(1, 1,
			BufferedImage.TYPE_INT_RGB);
	private static JFrame frame;
	
	public static void init(JFrame frame)
	{
		ResourceLoader.frame=frame;
	}
	
	public static Image loadImage(File file)
	{
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return notFound;
		}
	}

	public static Image loadImage(String name) {
	
		URL url = ResourceLoader.class
				.getResource("/com/starbattle/mapeditor/resource/images/" + name);
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return notFound;
		}
	}

	public static Image loadImage(String name, int width, int height) {
		return loadImage(name).getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
	}

	public static ImageIcon loadIcon(String icon) {
		return new ImageIcon(loadImage(icon));
	}

	public static ImageIcon loadIcon(String icon, int width, int height) {
		return new ImageIcon(loadImage(icon, width, height));
	}

	public static Image cutImage(Image image, int x, int y, int width,
			int height) {
		return frame.createImage(new FilteredImageSource(image.getSource(),
				new CropImageFilter(x, y, width, height)));
	}

	public static void writeObjectFile(Object object, File file) throws Exception {
		ObjectOutputStream o = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			o = new ObjectOutputStream(fos);
			o.writeObject(object);
		} catch (IOException e) {
			System.err.println("Error while Saving: " + e);
			throw new Exception("Error while Saving Object \n"+e.getMessage());
		} finally {
			try {
				o.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Object readObjectFile(File f) throws IOException {
		InputStream file;
		Object o = null;
		try {
			file = new FileInputStream(f);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			o = input.readObject();
			input.close();
			return o;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException("Error while reading Object File");
			
		}
	}



	public static BufferedImage loadBufferedImage(String name, int type) {
		// TODO Auto-generated method stub
		Image img=loadImage(name);
		BufferedImage bf=new BufferedImage(img.getWidth(null),img.getHeight(null),type);
		bf.getGraphics().drawImage(img,0,0,null);
		return bf;
	}

	
	public static Font loadFont(String name)
	{
	     try {
	    	 String path="com/starbattle/mapeditor/resource/fonts/"+name;
	         return Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(path));
	        
	        } catch (FontFormatException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	}
}
