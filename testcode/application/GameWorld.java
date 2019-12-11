package application;

/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: GameWorld.java
 *	Purpose: Creates our world by creating a frame with a canvas inside and drawing images to the canvas0.
 *	Extra Notes: Extends the Stage class and essentially makes it a JavaFX Stage (Which is a Window/JFrame/Whatever_U_Call_It)
 */

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld extends Stage {
	//GLOBAL VARIABLES
	private Player player;

	private Tile tiles[];
	private Tile door;
	private Tile coins[];
	private ArrayList<String> keyinput = new ArrayList<>();
	private ArrayList<Enemy> goblins;
	private boolean paused = false;
	private boolean playing = true;
	private boolean win = false;
	private AnimationTimer gameloop;

	//DEFAULT CONSTRUCTOR: Point of Execution
	public GameWorld() {
		renderGame();
	}

	//Method to instantiate our sprites & world tiles
	private void instantiateSprite() {
		//Instantiate player
		player = new Player();
		player.setVelocity(0, 0);

		instantiateGoblins();

		//Instantiates tiles
		instantiateTiles();
	}
	//Method to instantiate goblins, we must input a valid img location and also update the goblins starting positions
	private void instantiateGoblins() {
		//unit test
		int t = 125;
		goblins = new ArrayList<>();
		for(int i = 0; i < 11; i++) {
			goblins.add(new Enemy("Images/EnemySprites/goblins/running/runLeft0.png"));
			goblins.get(i).updatePosition(t*(i+2), t*(i+1));
		}
	}

	//Creates our tiles (Not calling this method would result in a NullPointerException)
	private void instantiateTiles()	{
		//Generates an int array of X and Y coords of where our tiles should be drawn
		int[] tileXPos = {125,380,250,625,550,420,690,920,380,800};
		int[] tileYPos = {125,900,250,625,550,420,690,920,900,900};
		String[] platforms = {"platform1.png", "platformdoor.png", "platformx256.png", "platform512x128.png"};
		Random r = new Random();

		int[] platXPos = {10, 560, 320, 10, 820};
		int[] platYPos = {920, 920, 720, 420, 500};

		int[] coinPos = {900,700,500,400,600};
		//Instantiates the tiles with our tile images (AS OF NOW)
		tiles = new Tile[10];
		for(int i = 0; i < 5; i++) {
			tiles[i] = new Tile("Images/platform256x128.png");
			tiles[i].updatePosition(platXPos[i], platYPos[i]);
		}
		for(int i = 5; i < 10; i++)	{
			tiles[i] = new Tile("Images/TileSprites/LevelObjects/DungeonPlatform.png");
			tiles[i].updatePosition(tileXPos[i], tileYPos[i]);
		}
		//instantiates coins
		coins = new Tile[5];
		for(int i = 0; i < coins.length; i++) {
			coins[i] = new Tile("Images/Coin.png");
			coins[i].updatePosition(coinPos[i], coinPos[i]);
			coins[i].setReward(1);
		}

		//instantiate door
		door = new Tile("Images/door.png");
		door.updatePosition(1000, 100);
	}
	//Renders our Tiles
	private void renderTiles(GraphicsContext gc) {
		//iterate through tiles in the array and render
		for(Tile t : tiles)	{
			//We have this conditional for now for safety but it's not required
			if(t.isVisible()) {
				t.render(gc);
			}
		}
		//Do the same for coins
		for(Tile c : coins)	{
			if(c.isVisible()) {
				c.render(gc);
			}
		}

		if(door.isVisible())
		{
			door.render(gc);
		}
	}
	//This method is called continuously throughout our game loop to continuously render our objects to the screen
	private void updateSprites(GraphicsContext gc, long now) {
		drawBackground(gc);

		//Check is player is alive, then update player
		if(player.getHp() > 0) {
			player.move();
			player.render(gc);
			player.updateWeapon(gc);
		}
		else
		{
			player.setAlive(false);
		}

		for(Ammo a : player.getSelectedWeapon().getAmmo())
		{
			if(a.visible = true)
			{
				a.render(gc);
				a.move();
			}
			else
			{
				player.getSelectedWeapon().getAmmo().remove(a);
			}
		}

		//and enemy sprites
		for(Enemy e : goblins) {
			if(e.isAlive()) {
				e.update(player, gc, now);
			}
			else
			{
				//goblins.remove(e);]
			}
		}


		//checkCollisions is called when we are updating our sprites
		checkCollisions(gc);
	}

	//This is GameWorlds main execution point. Our game loop is handled in this method and our sprites are instantiates and updated in this method as well
	private void renderGame() {
		instantiateSprite();

		//These 6 lines of code instantiate our games frame
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,1240,960);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		setScene(scene);
		setTitle("Dungeons Deep - version 1.06");
		Canvas canvas = new Canvas(1240, 960);
		root.getChildren().add(canvas);

		//Class to handle our 2D Graphics
		GraphicsContext gc = canvas.getGraphicsContext2D();


		//render the player, background, and tiles
		drawBackground(gc);
		player.render(gc);

		//INSTANTIATES KEY LISTENER: Key Listener events are called and handled in the game loop
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				System.out.println(code + " Pressed"); //TODO: Remove test line
				if(!keyinput.contains(code)) {
					keyinput.add(code);
				}
			}
		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				System.out.println(code + " Released"); //TODO: Remove test line
				keyinput.remove(code);
			}
		});


		//**GAME LOOP**: AnimationTimer() is our main game loop, it updates our objects in the game real time
		gameloop = gameLoop(gc);
		gameloop.start();
		show();
	}


	private AnimationTimer gameLoop(GraphicsContext gc) {
		return new AnimationTimer() {
			private long lastUpdate = 0;
			public void handle(long now) {
				keyListener(keyinput, gc, now);
				if(!paused && player.isAlive()) 
				{
					//updates the frame every 25 ms
					if(now - lastUpdate >= 25_000_000)
					{
						//These 4 Method calls are to be called continuously while the game is running

						//check if game is paused (Work in progress)
						gravity(gc);
						updateSprites(gc, now);

						lastUpdate = now;
					}
				}
				else if(!player.isAlive())
				{
					drawGameOver(gc);
				}
				else
				{
					drawPause(gc);
					player.setVelocity(0, 0);
				}

				if(win == true)
				{
					drawWin(gc);
					gameloop.stop();

					//renderGame();
					//We can create a new gameworld
				}
				
				if(playing == false)
				{
					close();
					gameloop.stop();
					hide();
				}
			}
		};
	}
	//this method implements gravity by setting the velocity of the sprites
	public void gravity(GraphicsContext gc)	{
		player.implementGravity(gc);
		for(Enemy e : goblins) {
			e.setVelocity(e.getDX(), 10);
			//this method allows smoother rendering
			e.setY(e.getY() + 5);
		}
	}
	//this is a test and a test only
	int p = 0;
	//This method handles our KeyListener by taking in Keyboard input as a string and the current game time to move the player with the movePlayer() method
	private void keyListener(ArrayList<String> keyinput, GraphicsContext gc, long now) {
		if(keyinput.contains("DOWN")) {
			player.movePlayer(gc, "DOWN", now);
		}
		if(keyinput.contains("UP")) {
			player.movePlayer(gc, "UP", now);
			
		}
		if(keyinput.contains("LEFT")) {
			player.movePlayer(gc, "LEFT", now);
		}
		if(keyinput.contains("RIGHT")) {
			player.movePlayer(gc, "RIGHT", now);
		}
		player.setVelocity(0,0);
		if(keyinput.isEmpty()) {
			player.movePlayer(gc, "STOP", now);
		}
		if(keyinput.contains("R") && keyinput.size() == 1)
		{	

		}
		if(keyinput.contains("SPACE"))
		{
			if(player.getDX() >= 0)
				player.fireWeapon(gc, now, 5);
			else
				player.fireWeapon(gc, now, -5);
		}
		if(keyinput.contains("P"))
		{
			pause();
			p++;
			System.out.println("P pressed: " + p);
			return;


		}

	}

	//method to pause and unpause the game; when the method is called, it changes the boolean, currently a bug exists where the method is called twice due to the keylistener calling the method twice
	private void pause() {
		if(paused) {
			paused = false;
		}
		else {
			paused = true;
		}
	}
	//This is our check collision method. We iterate through the tiles
	private void checkCollisions(GraphicsContext gc) {
		//check for platform collision
		for(Tile t : tiles)	
		{
			if(player.intersectsEdge(t))
			{
				//System.out.println("PLAYER COLLIDING WITH TILE"); // TODO: Remove test line
			}
		}
		for(Tile t : tiles)
		{
			if(player.intersectsEdge(t) && player.getDY() > 0)
			{
				//System.out.println("PLAYER COLLIDING WITH TILE");
				player.setY(t.getY() - 55);
				//This flag is used to make a smoother jump when the player jumps
				player.setColliding(true);
			}
		}

		player.setColliding(false);

		for(Enemy g : goblins)
		{
			for(Ammo a : player.getSelectedWeapon().getAmmo())
			{
				if(a.visible && g.intersects(a))
				{
					g.setHealth(g.getHealth()-player.getSelectedWeapon().getDamage());
				}
			}
		}

		//goblin to ammo



		//check for coin collision
		for(Tile c : coins) {
			if(player.intersects(c)) {
				System.out.println("You found a coin and this is a test... coin now going to sleep"); // TODO: Remove test line
				player.deposit(c.getReward());
				c.setVisible(false);
			}
		}

		//check enemy to tile collision
		for(Enemy e : goblins) {
			for(Tile t : tiles)
			{
				if(t.getEdgeBoundary().intersects(e.getBoundary()))
				{
					e.setY(t.getY() - 50);
				}
			}
		}
		//Goblin to goblin to ensure the images don't overlap. Currently a work in progress
		for(Enemy e1 : goblins)	{
			for(Enemy e2 : goblins)	{
				if(e1.getBoundary().contains(e2.getX(), e2.getY()))	{
					e1.setX(e2.getX() - 50);
					e2.setX(e1.getX() + 50);
				}	
			}
		}

		//check win if player reaches door
		if(player.intersects(door))
		{
			//win game
			win = true;
		}

		//check player out of bounds
		if(player.getX() > 1280 || player.getX() < 0) {
			player.setX(1);
		}
		if(player.getY() > 960)	{
			player.setAlive(false);
		}
	}

	//Draws the Game Start Screen has only two options for now; start & quit
	private void drawSplashScreen(GraphicsContext gc) {

	}
	//Draws the Game Over Screen
	private void drawGameOver(GraphicsContext gc) {
		//DRAW GAME OVER SCREEN HERE gc.drawImage or gc.fillText
		gc.drawImage(new Image("Images/gameover.png"), 0, 0);
	}
	//draw win screen
	private void drawWin(GraphicsContext gc)
	{
		gc.drawImage(new Image("Images/winscreen.png"), 0, 0);
		Image nextBtn = new Image("Images/nextlvlbtn.png");
		gc.drawImage(nextBtn, 620, 480);
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				if(e.getX() > 620 && e.getX() < (620 + nextBtn.getWidth()))
				{
					close();
					new GameWorld();
					win = false;
					playing = false;
					
				}

			} 
		};   
		//Adding event Filter 
		addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	//draw pause
	private void drawPause(GraphicsContext gc) {
		gc.drawImage(new Image("Images/pausemenu.png"), 0, 0);
	}
	//Draw the player stats gui
	private void drawStats(GraphicsContext gc) {
		gc.drawImage(new Image("Images/invstats.png"), 0, 0);
		gc.fillText("Coins: " + player.getMoney(), 0, 30);
		gc.fillText("HP: " + player.getHp(), 0, 60);
		gc.fillText("LVL: " + player.getLvl(), 0, 95);
		gc.fillText("ATK: " + player.getAtk(), 0, 130);
		gc.fillText("AMMO: " + player.getAmmo(), 200, 130);
	}
	//METHOD drawBackground() is called to draw our background image before drawing our sprite and tile objects on top
	private void drawBackground(GraphicsContext gc)	{
		Image background = new Image("Images/TileSprites/Background.png");
		gc.drawImage(background, 0, 0);
		drawStats(gc);

		//OPTIONAL: Added Player Coordinates to the screen 
		gc.fillText("Player Coords: (" + player.getX() + " : " + player.getY() + ")" , 100, 20);

		//method renderTiles() is appended to this method because this method is called continuously in the game loop
		//WITHOUT renderTiles() our background would be drawn over our tiles.
		renderTiles(gc);

		//This method sets our text font and font size
		gc.setFont(new Font(18));
		gc.setFill(Paint.valueOf("white"));
	}
}