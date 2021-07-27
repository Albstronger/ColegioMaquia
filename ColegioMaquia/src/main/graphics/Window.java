package main.graphics;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.tools.ResourceLoader;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private String title;
	private final ImageIcon icon;

	public Window(final String title, final DrawingSurface ds) {
		this.title = title;
		BufferedImage image = ResourceLoader.loadTranslucentCompatibleImage("/images/icons/WindowIcon.png");
		icon = new ImageIcon(image);
		configureWindow(ds);
	}

	private void configureWindow(final DrawingSurface ds) {
		setTitle(title);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		add(ds, BorderLayout.CENTER);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
