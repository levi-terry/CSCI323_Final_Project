package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Enemy.java
 *	Purpose: Creates the Enemy and renders to the screen, enemy will have attack and move functionality
 *	Extra Notes: Enemy extends the sprite class
 */
public class Enemy extends Sprite
{
	private int health;
	private int damage;
	private Weapon weapon;
	// need to get image array


	public Enemy(String imgURL)
	{
		super(imgURL);
	}
	// Getters
	public Weapon getWeapon() {
		return weapon;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}
	//Health Setter

	public void setHealth(int health) {
		this.health = health;
	}



}
