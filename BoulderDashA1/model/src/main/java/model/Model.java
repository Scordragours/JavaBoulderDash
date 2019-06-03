package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import contract.IModel;
import model.level.Level;
import model.db.DAOLevel;
import model.db.DBConnection;


/**
 * The Class Model.
 *
 * @author CANDANT ETIENNE AND DENEUVE GREGORY
 */
public final class Model extends Observable implements IModel {

	/** The level. */
	private Level level;
	/** The current remaining time. */
	private int remainingTime;
	/** The current remaining diamonds to unlock the exit. */
	private int remainingDiamonds;
	/** The world as an Entity array in two dimensions. */
	private Entity[][] world = new Entity[26][48];
	/** The current score. */
	private int score;
	/** The player entity of the level. */
	private Player player;
	/** The type of texture to display for the level. */
	private int levelTexture;
	/** The win state of the game. */
	private boolean win;
	/** The explosions is the game are registered here. */
	private ArrayList<int[]> explosions = new ArrayList<>();
	/** The exit entity of the level. */
	private Exit exit;
	/** Used to control the display frequency and avoid performance issues */
	private boolean canUpdate;

	/**
	 * Instantiates a new model.
	 *
	 * @param levelID
	 * 			the ID of the level in the database
	 * @param levelTexture
	 * 			the textures wanted
	 * @throws Exception
	 * 			when the levelTexture is out of range
	 */
	public Model(final int levelID, final int levelTexture) throws Exception
	{
	    if ((levelTexture<1) || (levelTexture>6))
        {
            throw new Exception("levelTexture out of range");
        }
	    this.levelTexture = levelTexture;
		this.loadLevel(levelID);
		this.remainingDiamonds = this.level.getNbDiamond();
		this.remainingTime = this.level.getTime();
		this.score = 0;
		this.buildWorld();
		this.canUpdate = true;
	}

	/**
	 * Loads the level from the database.
	 *
	 * @param levelID
	 * 			the ID of the level in the database
	 */
	private void loadLevel(final int levelID)
	{
		try {
			final DAOLevel daoLevel = new DAOLevel(DBConnection.getInstance().getConnection());
			this.level = daoLevel.find(levelID);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts the level extracted from the database to an usable Entity array in two dimension.
	 */
	private void buildWorld()
	{
		int x = 0;
		int y = 0;
		for(char c : level.getLevel().toCharArray())
		{
			if(c == ';')
			{
				y++;
				x = 0;
			}
			else if(c == '.')
			{
				break;
			}
			else
			{
				switch (c)
				{
					case 'P' :
						world[y][x] = new Player(this, x,y);
						this.player = (Player)world[y][x];
						break;
					case 'B' :
						world[y][x] = new Dirt(this, x,y);
						break;
					case 'R' :
						world[y][x] = new RollingRock(this, x,y);
						break;
					case 'D' :
						world[y][x] = new Diamond(this, x,y);
						break;
					case 'I' :
						world[y][x] = new Wall(this, x,y, EntityType.WALL);
						break;
					case '1' :
						world[y][x] = new EnemyDiamond(this, x,y);
						break;
					case '2' :
						world[y][x] = new EnemyPoint(this, x,y);
						break;
					case 'E' :
						world[y][x] = new Exit(this, x,y);
						this.exit = (Exit)world[y][x];
						break;
					case 'O' :
						world[y][x] = new Wall(this, x,y, EntityType.OUTLINE);
						break;
					default:
						world[y][x] = null;
						break;
				}
				x++;
			}

		}
	}

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	public Observable getObservable() { return this; }

	/**
	 * Gets the level texture type.
	 *
	 * @return the level texture type
	 */
	public int getLevelTexture() { return this.levelTexture; }

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	Level getLevel() { return this.level; }

	/**
	 * Sets the remaining time.
	 *
	 * @param remainingTime
	 * 			the remaining time
	 * @throws Exception when the given positions are out of the world
	 */
	void setRemainingTime(final int remainingTime) throws Exception
	{
		this.remainingTime = remainingTime;

		if(!this.isWin() && this.remainingTime <= 0)
        {
            this.remainingTime = 0;
            this.getPlayer().die(true);
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the remaining time.
	 *
	 * @return the remaining time
	 */
	public int getRemainingTime() { return this.remainingTime; }

	/**
	 * Decrements the remaining diamonds.
	 */
	void decrementRemainingDiamonds()
	{
		this.remainingDiamonds--;

		if(this.remainingDiamonds <= 0)
		{
			this.setScore(this.getScore() + 15);
			this.remainingDiamonds = 0;


			this.exit.openned();
		}
		else
		{
			this.setScore(this.getScore() + 10);
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the remaining diamonds.
	 *
	 * @return the remaining diamonds
	 */
	public int getRemainingDiamonds() { return this.remainingDiamonds; }

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() { return this.score; }

	/**
	 * Sets the score. Also notify the observers.
	 *
	 * @param score
	 * 			the score
	 */
	void setScore(final int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public Entity[][] getWorld() { return this.world; }

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() { return this.player; }

	/**
	 * Updates a slot in the world array and notify the observers.
	 *
	 * @param x
	 * 			the x position of the updated slot
	 * @param y
	 * 			the y position of the updated slot
	 * @param entity
	 * 			the entity put in the slot (can be null)
	 *
	 * @throws Exception when the given positions are out of the world
	 */
	void updateEntity(final int x, final int y, Entity entity) throws Exception
	{
	    if(entity != null)
        {
            entity.setPositionX(x);
            entity.setPositionY(y);
        }

		world[y][x] = entity;

	    if(canUpdate)
		{
			setChanged();
			notifyObservers();
		}

	}

    /**
     * Tries to update every sliding block in the world if they can move.
	 *
     * @throws Exception when the given positions are out of the world
     */
	void updateSlidingBlocks() throws Exception
	{
		canUpdate = false;
		for(Entity[] line : world)
		{
			for(Entity e : line)
			{
				if(e instanceof SlidingBlock)
				{
					((SlidingBlock) e).pathFinder();
				}
			}
		}

		for(Entity[] line : world)
		{
			for(Entity e : line)
			{
				if(e instanceof SlidingBlock)
				{
					((SlidingBlock) e).resetMove();
				}
			}
		}
		setChanged();
		notifyObservers();
		canUpdate = true;
	}

	/**
	 * Tries to update every enemy in the world if they can move.
	 *
	 * @throws Exception when the given positions are out of the world
	 */
	void updateEnemies() throws Exception
	{
		canUpdate = false;
		for(Entity[] line : world)
		{
			for(Entity e : line)
			{
				if(e instanceof Enemy)
				{
					((Enemy) e).pathFinder();
				}
			}
		}

		for(Entity[] line : world)
		{
			for(Entity e : line)
			{
				if(e instanceof Enemy)
				{
					((Enemy) e).resetMove();
				}
			}
		}
		setChanged();
		notifyObservers();
		canUpdate = true;
	}

	/**
	 * Converts the world to a char array in two dimensions.
	 *
	 * @return the converted char array
	 */
	public char[][] convertWorld()
	{
		char[][] cw = new char[26][48];

		for(int y = 0; y<26; y++)
		{
			for(int x = 0; x<48; x++)
			{
				if(world[y][x] != null) {
					switch (world[y][x].getType()) {
						case DIRT:
							cw[y][x] = 'B';
							break;
						case DIAMOND:
							cw[y][x] = 'D';
							break;
						case PLAYER:
							cw[y][x] = 'P';
							break;
						case WALL:
							cw[y][x] = 'I';
							break;
						case OUTLINE:
							cw[y][x] = 'O';
							break;
						case EXIT:
							cw[y][x] = 'E';
							break;
						case ENEMYPOINT:
							cw[y][x] = '2';
							break;
						case ENEMYDIAMOND:
							cw[y][x] = '1';
							break;
						case ROLLINGROCK:
							cw[y][x] = 'R';
							break;
						default:
							cw[y][x] = ' ';
							break;
					}
				}
				else
				{
					cw[y][x] = ' ';
				}
			}
		}

        return cw;
	}

	/**
	 * Gets the win state.
	 *
	 * @return the win state
	 */
	public boolean isWin() { return this.win; }

	/**
	 * Sets the win state to true. Also notify the observers.
	 */
	void won()
	{
		this.win = true;
		setChanged();
		notifyObservers();
	}

	/**
	 * Adds an explosion.
	 *
	 * @param coordinates
	 * 			the coordinates of the explosion
	 */
	void addExplosion(final int[] coordinates)
    {
        this.explosions.add(coordinates);
    }

	/**
	 * Gets the ArrayList of explosions coordinates.
	 *
	 * @return the ArrayList of explosions coordinates
	 */
	public ArrayList<int[]> getExplosions() { return this.explosions; }

	/**
	 * Gets the alive state from the player.
	 *
	 * @return the alive state from the player
	 */
    public boolean getIsAlivePlayer()
    {
        return this.getPlayer().isAlive();
    }

	/**
	 * Tries to move the player to a relative location.
	 *
	 * @param x
	 * 			the relative X position
	 * @param y
	 * 			the relative Y position
	 *
	 * @throws Exception when the given positions are out of the world
	 */
    public void setMovePlayer(final int x, final int y) throws Exception
    {
        this.getPlayer().move(x, y);
    }

	/**
	 * Gets the position of the player.
	 *
	 * @return the position of the player
	 */
    public int[] getPositionsPlayer()
	{
		int[] pos = new int[2];
		pos[0] = this.getPlayer().getPositionX();
		pos[1] = this.getPlayer().getPositionY();
		return pos;
	}

	/**
	 * Gets the open state of the exit.
	 *
	 * @return the open state of the exit
	 */
	public boolean getIsOpenExit()
	{
		return this.exit.isOpen();
	}
}
