package main.graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private String title;

	public Window(final String title, final DrawingSurface ds) {
		this.title = title;
		configureWindow(ds);
	}

	private void configureWindow(final DrawingSurface ds) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
//		setIconImage();
		setLayout(new BorderLayout());
		add(ds, BorderLayout.CENTER);
//		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
