package application;

import javafx.scene.image.Image;

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
	private Image[] animation;


	public Enemy(String imgURL)
	{
		super(imgURL);
	}
	// Getters
	public Weapon getWeapon() {
		return weapon;
	}

	private Image[] getAnimation() {
		return animation; }

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

	//Setters
	public void setAnimation(Image[] animation) {
		this.animation = animation;
	}
	public void setHealth(int health) {
		this.health = health;
	}
}
