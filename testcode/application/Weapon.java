package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Weapon.java
 *	Purpose: Creates our weapon for our player, can change weapons and render an arraylist of bullets/ammo that can be used
 *	Extra Notes: Weapon extends the sprite class so it may be rendered as an image just like the player
 */

public class Weapon extends Sprite {
	private int damage;
	private int ammunition;
	private ArrayList<Ammo> magazine;
	private String weaponName;
	private int serialNum;

	public Weapon(String imgURL) {
		super(imgURL);
		Random r = new Random();
		damage = r.nextInt(50) + 10;
		ammunition = r.nextInt(12) + 6;
		weaponName = imgURL;
		magazine = new ArrayList<>();
		for(int i = 0; i < ammunition; i++)
		{
			magazine.add(new Ammo("Images/Missle.png"));
		}
		serialNum = r.nextInt(100000) + 10000;
		System.out.println("Weapon instantiated");
	}

	public void fire(double dx, double x, double y, GraphicsContext gc)
	{
		if(ammunition > 0 && !magazine.isEmpty())
		{
			magazine.get(ammunition-1).setX(x);
			magazine.get(ammunition-1).setY(y);
			magazine.get(ammunition-1).setVelocity(dx, 0);
			magazine.get(ammunition-1).visible = true;
			System.out.println("Ammo shot");
			
		}
	}
	//Getters
	public int getDamage() {
		return damage;
	}

	public int getAmmunition() {
		return ammunition;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public String getWeaponName() {
		return weaponName;
	}

	// Ammo Setter
	public void setAmmunition(int Ammo) {
		this.ammunition = Ammo;
	}
	public ArrayList<Ammo> getAmmo()
	{
		return magazine;
	}
}

class Ammo extends Sprite
{
	boolean visible;
	public Ammo(String imageURL) {
		super(imageURL);
		visible = true;
		
		// TODO Auto-generated constructor stub
	}
	
	
}
