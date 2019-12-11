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
	private boolean owned = false;
	private boolean selected = false;
	private String weaponName;
	private int serialNum, index;

	public Weapon(String imgURL, int weaponIndex) {
		super(imgURL);
		Random r = new Random();
		damage = r.nextInt(50) + 10;
		ammunition = 100;
		weaponName = imgURL;
		magazine = new ArrayList<>();
		for(int i = 0; i < ammunition; i++) {
			magazine.add(new Ammo("Images/Missle.png"));
		}
		serialNum = r.nextInt(100000) + 10000;

		index = weaponIndex;
		//System.out.println("Weapon instantiated"); // TODO: Remove test line
	}
	public void fire(double dx, double x, double y, GraphicsContext gc) {
			ammunition--;
			if(ammunition > 0) {
				magazine.get(ammunition).updatePosition(x, y);
				magazine.get(ammunition).setVelocity(dx, 0);
				magazine.get(ammunition).visible = true;
			}
			//System.out.println("Ammo shot"); // TODO: Remove test line
	}
	//Getters
	public boolean isOwned() {
		return owned;
	}
	public void setOwned(boolean owned) {
		this.owned = owned;
	}
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
	
	public ArrayList<Ammo> getMagazine() {
		return magazine;
	}

	public void setMagazine(ArrayList<Ammo> magazine) {
		this.magazine = magazine;
	}

	public boolean isSelected() {
		return selected;
	}
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int i) {
		index = i;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public ArrayList<Ammo> getAmmo() {
		return magazine;
	}
}

class Ammo extends Sprite {
	boolean visible;
	public Ammo(String imageURL) {
		super(imageURL);
		visible = false;
	}
}
