package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import contract.IModel;
import entity.Level;


/**
 * The Class Model.
 *
 * @author CANDANT ETIENNE AND DENEUVE GREGORY
 */
public final class Model extends Observable implements IModel {

	private Level level;
	private int remainingTime;
	private int remainingDiamonds;
	private Entity[][] world = new Entity[26][48];
	private int score;
	private Player player;
	private int levelTexture;
	private boolean win;
	private ArrayList<int[]> explosions = new ArrayList<>();
	private Exit exit;

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
	public Model(int levelID, int levelTexture) throws Exception
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
	}

	/**
	 * Load the level from the database.
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
	 * Convert the level extracted from the database to an usable Entity array in two dimension.
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
	 */
	void setRemainingTime(int remainingTime)
	{
		this.remainingTime = remainingTime;

		if(this.remainingTime <= 0)
        {
            this.remainingTime = 0;
            this.getPlayer().die();
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

		if(this.remainingDiamonds < 0)
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
	public void setScore(int score) {
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
	 * Update a slot in the world array and notify the observers.
	 *
	 * @param x
	 * 			the x position of the updated slot
	 * @param y
	 * 			the y position of the updated slot
	 * @param entity
	 * 			the entity put in the slot (can be null)
	 *
	 * @throws Exception when a bad position is given in parameter
	 */
	public void updateEntity(int x, int y, Entity entity) throws Exception
	{
	    if(entity != null)
        {
            entity.setPositionX(x);
            entity.setPositionY(y);
        }

		world[y][x] = entity;

		setChanged();
		notifyObservers();
	}

    /**
     * Try to update every sliding block in the world if they can move.
	 *
     * @throws Exception when there was a bad move
     */
	public void updateSlidingBlocks() throws Exception
	{
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
					((SlidingBlock) e).asMove = false;
				}
			}
		}
	}

	/**
	 * Convert the world to a char array in two dimensions.
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
	public void winned()
	{
		this.win = true;
		setChanged();
		notifyObservers();
	}

	/**
	 * Add an explosion.
	 *
	 * @param coordinates
	 * 			the coordinates of the explosion
	 */
	public void addExplosion(final int[] coordinates)
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
	 * Try to move the player.
	 *
	 * @param x
	 * 			the x position of the move
	 * @param y
	 * 			the y position of the move
	 *
	 * @throws Exception when a bad position is given in parameter
	 */
    public void setMovePlayer(int x, int y) throws Exception
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
