package application;

import java.io.InputStream;

import javafx.scene.image.Image;

public class Player extends Image
{
	//PLAYER IS A SPRITE WITH CERTAIN ATTRIBUTES SUCH AS HP
	
	public Player(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio,
			boolean smooth) {
		super(is, requestedWidth, requestedHeight, preserveRatio, smooth);
		// TODO Auto-generated constructor stub
	}
	public Player(InputStream is) {
		super(is);
		// TODO Auto-generated constructor stub
	}
	public Player(String url, boolean backgroundLoading) {
		super(url, backgroundLoading);
		// TODO Auto-generated constructor stub
	}
	public Player(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth,
			boolean backgroundLoading) {
		super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
		// TODO Auto-generated constructor stub
	}
	public Player(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
		super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
		// TODO Auto-generated constructor stub
	}
	public Player(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

}
