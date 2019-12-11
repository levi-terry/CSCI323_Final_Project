package application;

/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Player.java
 *	Purpose: Creates our user with basic characteristics and variables such as atk, def, hp and etc.
 *	Extra Notes: Extends the Sprite class to make our player a sprite image
 */

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite {
	private int hp, atk, lvl, money;
	//booleans flags to check enable certain game conditions
	private boolean alive, colliding, lookingRight;
	private Weapon[] weapon;
	private int ammo = 0;

	//Image Arrays are used for Player Animation
	Image[] walkRight, walkLeft, jump, attack, hurt, die;

	//Player Default SuperConstructor
	public Player()	{
		super("Images/playeranimation/WalkRightAnimation/Standing.png");
		initPlayer();
	}
	//initializes the player
	private void initPlayer() {
		hp = 100;
		atk = 5;
		lvl = 0;
		money = 0;
		alive = true;
		colliding = false;
		ammo = 50;
		lookingRight = true;
		//instantiates weapon arms - 
		weapon = new Weapon[6];
		initAnimationFrames();
		weapon[5].setOwned(true);
		weapon[4].setOwned(true);
		weapon[5].updatePosition(getX()+150, getY());
	}
	//Initializes the players Image Arrays for player animation
	private void initAnimationFrames() {
		String[] imgLoc = getImgLocTemp();
		String[] leftImgLoc = getLeftAnimationImgLoc();
		String[] weaponArms = getWeaponsImgLoc();
		walkLeft = new Image[8];
		walkRight = new Image[8];
		jump = new Image[8];
		attack = new Image[8];
		die = new Image[8];
		hurt = new Image[8];
		for(int i = 0; i < 8; i++) {
			walkLeft[i] = new Image(leftImgLoc[i]);
		}
		for(int i = 0; i < 8; i++) {
			walkRight[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++) {
			jump[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 6; i++) {
			weapon[i] = new Weapon(weaponArms[i], i);
			attack[i] = new Image(weaponArms[i]);
		}
		for(int i = 0; i < 8; i++) {
			die[i] = new Image(imgLoc[i]);
		}
		for(int i = 0; i < 8; i++) {
			hurt[i] = new Image(imgLoc[i]);
		}
	}
	//TEMPORARY METHOD to return the current images locations 
	private String[] getImgLocTemp() {
		String[] tempLoc = new String[8];
		for(int i = 0; i < tempLoc.length; i++) {
			tempLoc[i] = "Images/WalkRightAnimation/WalkRight" + (i+1) + ".png";
		}
		return tempLoc;
	}
	private String[] getWeaponsImgLoc() {
		String[] locs = {"MinigunArmsLeft", "MinigunArmsRight", "RifleArmsLeft", "RifleArmsRight", "ShotgunArmsLeft", "ShotgunArmsRight"};
		String[] tempLoc = new String[6];
		for(int i = 0; i < tempLoc.length; i++) {
			tempLoc[i] = "Images/playeranimation/WeaponArms/" + locs[i] + ".png";
		}
		return tempLoc;
	}
	private String[] getLeftAnimationImgLoc() {
		String[] tempLoc = new String[8];
		{
			for(int i = 0; i < tempLoc.length; i++) {
				tempLoc[i] = "Images/playeranimation/WalkLeftAnimation/WalkLeft" + (i+1) + ".png";
			}
			return tempLoc;
		}
	}
	// WORK IN PROGRESS -- CANNOT COMPLETE FULL IMAGE LOCATION ARRAY UNTIL ALL FRAMES ARE DRAWN AND LOADED
	private String[] getImgLoc() {
		String[] loc = new String[36];
		String[] action = {"idle", "WalkLeftAnimation", "WalkRightAnimation", "jump", "attack", "die", "hurt"};
		String png = ".png";
		int l = 0;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 5; j++) {
				loc[l] = "playeranimation/" + action[i] + j + png;
				l++;
			}
		}
		return loc;
	}

	//Method to return the next image in the image array
	public Image getFrame(Image[] frames) {
		return frames[getNextIndex(frames)];
	}

	//Method to find and return the next index to while iterating through Image array
	private int getNextIndex(Image[] g) {
		int c = 0;
		for(int i = 0; i < g.length; i++) {
			if(g[i].equals(getImage()))	{
				//TEST if Player is using image, then return next index
				//System.out.println("Image match found at index: " + i);
				c = i + 1;
				if(c > 7) {
					c = 0;
				}
			}
		}
		return c;
	}
	//Creates an AnimationTimer to iterate through our Image Array
	private AnimationTimer itrFrames(Image[] f)	{
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
	public void implementGravity(GraphicsContext gc) {
		setVelocity(getDX(), 2);
		move();
	}
	//This method moves our player across the screen, by taking a graphics argument to draw the player, a string argument which is our..
	//	..Key Listener keycode, and the main game loops time so we may iterate through the players animation frames
	public void movePlayer(GraphicsContext gc, String way, long now) {
		//This is where we set which frame animations to use. We create an AnimationTimer and will call that timer to iterate
		//	through the frames with the t.handle() method when an action is called (by default set to walkRight)
		AnimationTimer t;
		if(getSelectedWeapon() != null)
			selectWeapon(getSelectedWeapon().getIndex());
		else
			selectWeapon(5);
		//This is our player listener, where we take a keycode and move our player based off the key
		//We first start the animation timer, set the velocity of which way we want to move, then render the player
		long lastUpdate = 0;
		if(way == "UP") {
			//**THIS COMMENTED STUB IS USED TO SET WHICH IMAGE ARRAY TO USE FOR ANIMATIONTIMER
			t = itrFrames(jump);
			if(!lookingRight) {
				t = itrFrames(walkLeft);
			}
			render(gc);
			t.handle(now);
			setVelocity(getDX(), -10);
			move();
		}
		else if(way == "DOWN") {
			t = itrFrames(walkRight);
			t.handle(now);
			setVelocity(0, 5);
			move();
			//System.out.println("PLAYER MOVING"); // TODO: Remove test line
			render(gc);
		}
		else if(way == "RIGHT")	{
			t = itrFrames(walkRight);
			lookingRight = true;
			selectWeapon(5);
			t.handle(now);
			setVelocity(5, 0);
			move();
			render(gc);
		}
		else if(way == "LEFT") {
			t = itrFrames(walkLeft);
			lookingRight = false;
			selectWeapon(4);

			t.handle(now);
			setVelocity(-5, 0);
			move();
			render(gc);
		}
		else {
		}


	}
	public void updateWeapon(GraphicsContext gc) {
		for(Weapon w : weapon) {
			if(w.isOwned() && w.isSelected()) {
				w.render(gc);
				if(lookingRight) {
					w.updatePosition(getX(), getY() + 5);
				}
				else {
					//System.out.println("Weapon update looking left -- TEST"); // TODO: Remove test line
					w.updatePosition(getX() - 20, getY() +5);
				}
			}
		}
	}

	public void fireWeapon(GraphicsContext gc) {
		Weapon w = getSelectedWeapon();
		if(ammo > 0) {
			//System.out.println("Weapon firing"); //  TODO: Remove test line
			if(lookingRight) {
				w.fire(5, getX(), getY(), gc);
			}
			if(!lookingRight){
				w.fire(-5, getX(), getY(), gc);
			}
			ammo--;
			//System.out.println("Weapon fired"); // TODO: Remove test line
		}
	}
	//Attempt to create a player jump. W.I.P
	public void jump(double y) {
		//System.out.println("Player jumping"); // TODO: Remove test line
		/*final double initY = y;
		setVelocity(getDX(), -5);
		move();
		//setY(getY() - (getHeight()/4));
		if(getY() < initY - 100)
		{
			System.out.println("Testing testing... init Y:" + initY + "| curr Y: " + getY());
			setVelocity(getDX(), 4);
		}*/



	}
	//Method is used to only get the top quarter of a Tile 2D dimension so the player has a more accurate movement (Any questions I can explain)
	public boolean intersectsEdge(Tile t) {
		if(t.getEdgeBoundary().intersects(getBoundary())) {
			return true;
		}
		else {
			return false;
		}
	}
	public Weapon getSelectedWeapon() {
		Weapon ww = null;
		for(Weapon w : weapon)
		{
			if(w.isSelected())
				ww = w;
		}
		return ww;
	}
	public void selectWeapon(int wnum) {
		for(int i = 0; i < weapon.length; i++) {
			weapon[i].setSelected(false);
		}
		weapon[wnum].setSelected(true);
	}
	public void setWeaponOwned(int wnum) {
		weapon[wnum].setOwned(true);
	}

	//Player Class Getters & Setters: Very Much Required.
	public void deposit(int c) {
		money += c;
	}
	public void withdraw(int c) {
		money -= c;
	}
	public int getMoney() {
		return money;
	}
	public int getAmmo()
	{
		return ammo;
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
	public Weapon[] getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon[] weapon) {
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