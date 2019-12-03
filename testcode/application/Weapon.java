package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Weapon.java
 *	Purpose: Creates our weapon for our player, can change weapons and render an arraylist of bullets/ammo that can be used
 *	Extra Notes: Weapon extends the sprite class so it may be rendered as an image just like the player
 */

public class Weapon extends Sprite {
	private int damage;
	private int ammunition;
	private String weaponName;
	private int serialNum;

	public Weapon(String imgURL) {
		super(imgURL);
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
}
