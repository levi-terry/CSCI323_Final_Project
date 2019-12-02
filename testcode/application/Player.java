package application;

import java.io.InputStream;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Player.java
 *	Purpose: Creates our user with basic characteristics and variables such as atk, def, hp and etc.
 *	Extra Notes: Extends the Sprite class to make our player a sprite image
 */
public class Player extends Sprite
{
	int hp, atk, lvl, money;
	//booleans flags to check enable certain game conditions
	boolean alive, colliding;
	Weapon weapon;
	
	//Image Arrays are used for Player Animation
	Image[] walkRight, walkLeft, jump, attack, hurt, die;
	
	//Player Default SuperConstructor
	public Player()
	{
		super("Images/Untitled_Artwork.png");
		initPlayer();
	}
	//initializes the player
	private void initPlayer()
	{
		hp = 100;
		atk = 5;
		lvl = 0;
		money = 0;
		alive = true;
		colliding = false;
		initAnimationFrames();
	}
	//Initializes the players Image Arrays for player animation
	private void initAnimationFrames()
	{
		String[] imgLoc = getImgLocTemp();
		walkLeft = new Image[8];
		walkRight = new Image[8];
		jump = new Image[8];
		attack = new Image[8];
		die = new Image[8];
		hurt = new Image[8];
		for(int i = 0; i < 8; i++)
		{
			walkLeft[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++)
		{
			walkRight[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++)
		{
			jump[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++)
		{
			attack[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++)
		{
			die[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++)
		{
			hurt[i] = new Image(imgLoc[i]);
		}
	}
	//TEMPORARY METHOD to return the current images locations 
	private String[] getImgLocTemp()
	{
		String[] tempLoc = new String[8];
		for(int i = 0; i < tempLoc.length; i++)
		{
			tempLoc[i] = "Images/WalkRightAnimation/WalkRight" + (i+1) + ".png";
		}
		return tempLoc;
	}
	
	// WORK IN PROGRESS -- CANNOT COMPLETE FULL IMAGE LOCATION ARRAY UNTIL ALL FRAMES ARE DRAWN AND LOADED
	private String[] getImgLoc()
	{
		String loc[] = new String[36];
		String action[] = {"idle", "walk", "run", "jump", "attack", "die", "hurt"};
		String png = ".png";
		int l = 0;
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				loc[l] = "playerframes/" + action[i] + j + png;
				l++;
			}
		}
		return loc;
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
				if(c > 7)
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
					//Every game ms the frame is set to the next frame in the animation
					setFrame(getFrame(f));			
			}
			
		};
		return t;
	}
	//Implements gravity for the player (sets the player y movement to 2)
	public void implementGravity(GraphicsContext gc)
	{
		setVelocity(getDX(), 2);
		move();
	}
	//This method moves our player across the screen, by taking a graphics argument to draw the player, a string argument which is our..
	//	..Key Listener keycode, and the main game loops time so we may iterate through the players animation frames
	public void movePlayer(GraphicsContext gc, String way, long now)
	{
		//This is where we set which frame animations to use. We create an AnimationTimer and will call that timer to iterate
		//	through the frames with the t.handle() method when an action is called (by default set to walkRight)
		AnimationTimer t = itrFrames(walkRight);
		
		//This is our player listener, where we take a keycode and move our player based off the key
		//We first start the animation timer, set the velocity of which way we want to move, then render the player
		if(way == "UP")
		{
			//**THIS COMMENTED STUB IS USED TO SET WHICH IMAGE ARRAY TO USE FOR ANIMATIONTIMER
			//t = itrFrames(jump);
			t.handle(now);
			
			//attempt at a proper jump animation
			if(getY() < (getY() - getHeight()))
				render(gc);
			else
				jump();
				move();
				render(gc);
		}
		else if(way == "DOWN")
		{
			t.handle(now);
			setVelocity(0, 5);
			move();
			System.out.println("PLAYER MOVING");
			render(gc);
		}
		else if(way == "RIGHT")
		{
			t.handle(now);
			setVelocity(5, 0);
			move();
			render(gc);
		}
		else if(way == "LEFT")
		{
			//t = itrFrames(walkLeft);
			t.handle(now);
			setVelocity(-5, 0);
			move();
			render(gc);
		}
		else
		{

		}
	}
	//Attempt to create a player jump. W.I.P
	public void jump()
	{
			System.out.println("Player jumping");
			setVelocity(getDX(), -5);
			setY(getY() - (getHeight()/4));
	}
	//Method is used to only get the top quarter of a Tile 2D dimension so the player has a more accurate movement (Any questions I can explain)
	public boolean intersectsEdge(Tile t)
	{
		if(t.getEdgeBoundary().intersects(getBoundary()))
			return true;
		else
			return false;
	}
	
	
	//Player Class Getters & Setters: Very Much Required.
	public void deposit(int c)
	{
		money += c;
	}
	public void withdrawl(int c)
	{
		money -= c;
	}
	public int getMoney()
	{
		return money;
	}
	public int getHp() {
		return hp;
	}
	public int getAtk() {
		return atk;
	}
	public int getLvl() {
		return lvl;
	}
	public Image[] getWalkLeft() {
		return walkLeft;
	}
	public Image[] getWalkRight() {
		return walkRight;
	}
	public Image[] getJump() {
		return jump;
	}
	public Image[] getAttack() {
		return attack;
	}
	public Image[] getDie() {
		return die;
	}
	public Image[] getHurt() {
		return hurt;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isColliding() {
		return colliding;
	}
	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
