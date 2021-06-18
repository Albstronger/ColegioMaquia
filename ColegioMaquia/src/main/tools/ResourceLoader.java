package main.tools;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ResourceLoader {

	public static BufferedImage loadOpaqueCompatibleImage(String path) {

		Image image = null;

		try {
			image = ImageIO.read(ResourceLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null),
				Transparency.OPAQUE);

		Graphics g = acceleratedImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return acceleratedImage;
	}

	public static BufferedImage loadTranslucentCompatibleImage(String path) {
		Image image = null;

		try {
			image = ImageIO.read(ResourceLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null),
				Transparency.TRANSLUCENT);

		Graphics g = acceleratedImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return acceleratedImage;
	}

	public static String readTextFile(final String path) {
		String content = "";

		InputStream byteInput = ResourceLoader.class.getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(byteInput));

		String line;

		try {
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteInput != null) {
					byteInput.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return content;
	}
}
