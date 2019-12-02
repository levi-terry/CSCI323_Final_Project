package application;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
	private Image[] walkRight, walkLeft, attackRight, attackLeft, hurt, die, idle;
	private boolean alive;
	private AnimationTimer animateEnemy;
	
	public Enemy(String imgURL)
	{
		super(imgURL);
		instantiateEnemy();
	}
	//Method to instantiate the enemy; sets random stats and initializes variables and animations
	private void instantiateEnemy()
	{
		Random randomStat = new Random();
		health = randomStat.nextInt(50) + 10;
		damage = randomStat.nextInt(3) + 1;
		weapon = null;
		alive = true;
		instantiateAnimation();
	}
	//Instantiates the animations for the goblin.
	private void instantiateAnimation()
	{
		//7 Image arrays are used and will be iterated through individually based on character state (such as walking/attacking etc.)
		walkRight = new Image[5];
		walkLeft = new Image[5];
		attackLeft = new Image[5];
		attackRight = new Image[5];
		hurt = new Image[5];
		die = new Image[5];
		idle = new Image[5];
		
		//goblins main image location
		String imgLoc = "Images/EnemySprites/goblins/";
		
		//for loop to create the images in the arrays
		for(int i = 0; i < 5; i++)
		{
			walkRight[i] = new Image(imgLoc + "running/runRight" + i + ".png");
			walkLeft[i] = new Image(imgLoc + "running/runLeft" + i + ".png");
			attackLeft[i] = new Image(imgLoc + "attack/attackLeft" + i + ".png");
			attackRight[i] = new Image(imgLoc + "attack/attackRight" + i + ".png");
			hurt[i] = new Image(imgLoc + "hurt/hurt" + i + ".png");
			die[i] = new Image(imgLoc + "dying/dying" + i + ".png");
			idle[i] = new Image(imgLoc + "idle/idle" + i + ".png");
		}
		
		//animateEnemy is an AnimationTimer that is used to iterate through the frames. We set the timer to iterate through the idle frames by default
		animateEnemy = itrFrames(idle);
	}
	
	//method to update the enemy animation and position based off player location and current game time
	public void update(Player p, GraphicsContext gc, long now)
	{
		if(p.getX() < getX() && (getX() - p.getX()) < 200)
		{
			animateEnemy = itrFrames(walkLeft);
			if(p.intersects(this))
			{
				animateEnemy = itrFrames(attackLeft);
				p.setHp(p.getHp() - getDamage());
				System.out.println("OUCH Damage Dealt: " + getDamage());
			}
			animateEnemy.handle(now);
			setVelocity(-2, 0);
			move();
			render(gc);
		}
		else if(p.getX() == getX())
		{
			animateEnemy = itrFrames(attackLeft);
			animateEnemy.handle(now);
			render(gc);
		}
		else if(p.getX() > getX() && (p.getX() - getX()) < 200)
		{
			animateEnemy = itrFrames(walkRight);
			if(p.intersects(this))
			{
				animateEnemy = itrFrames(attackRight);
				p.setHp(p.getHp() - getDamage());
			}
			animateEnemy.handle(now);
			setVelocity(2, 0);
			move();
			render(gc);
		}
		else
		{
			animateEnemy = itrFrames(idle);
			animateEnemy.handle(3000);
			render(gc);
		}
		
		gc.fillText("HP: " + getHealth() + "\nATK: " + getDamage(), getX(), getY() + getHeight() + 20);
		
	}
	//Method to return the next image in the image array
	public Image getFrame(Image[] frames)
    {
        return frames[getNextIndex(frames)];
    }
    //Method to find and return the next index to while iterating through Image array
	private int getNextIndex(Image[] g)
	{
		int c = 0;
		for(int i = 0; i < g.length; i++)
		{
			if(g[i].equals(getImage()))
			{
				//TEST if Player is using image, then return next index
				System.out.println("Image match found at index: " + i);
				c = i + 1;
				if(c > 4)
				{
					c = 0;
				}
			}
		}
		return c;
	}
	//Creates an AnimationTimer to iterate through our Image Array
	private AnimationTimer itrFrames(Image[] f)
	{
		AnimationTimer t;
		t = new AnimationTimer() {
			@Override
			public void handle(long now) {
					setFrame(getFrame(f));
			}
			
		};
		return t;
	}
	// Getters
	public Weapon getWeapon() 
	{
		return weapon;
	}
	public boolean isAlive()
	{
		return alive;
	}
	public void alive(boolean alive)
	{
		this.alive = alive;
	}
	public int getHealth() {
		return health;
	}
	public int getDamage() {
		return damage;
	}
	public Image[] getWalkRight() {
		return walkRight;
	}
	public Image[] getWalkLeft() {
		return walkLeft;
	}
	public Image[] getAttackRight() {
		return attackRight;
	}
	public Image[] getAttackLeft() {
		return attackLeft;
	}
	public Image[] getHurt() {
		return hurt;
	}
	public Image[] getDie() {
		return die;
	}
	//Setters
	public void setHealth(int health) {
		this.health = health;
	}
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	
}
