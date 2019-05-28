package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import contract.IModel;
import entity.Level;


/**
 * The Class Model.
 *
 * @author CANDANT ETIENNE AND DENEUVE GR2GORY
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

	private void loadLevel(final int levelID)
	{
		try {
			final DAOLevel daoLevel = new DAOLevel(DBConnection.getInstance().getConnection());
			this.level = daoLevel.find(levelID);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

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

	public Observable getObservable() { return this; }

	public int getLevelTexture() { return this.levelTexture; }

	public Level getLevel() { return this.level; }

	public void setRemainingTime(int remainingTime)
	{
		this.remainingTime = remainingTime;

		setChanged();
		notifyObservers();
	}

	public int getRemainingTime() { return this.remainingTime; }

	public void setRemainingDiamonds(int remainingDiamond)
	{
		this.remainingDiamonds = remainingDiamond;

		if(this.remainingDiamonds <= 0)
        {
            this.remainingDiamonds = 0;
            for(Entity[] eTab : this.world)
                for(Entity e : eTab)
                    if(e != null && e.getType() == EntityType.EXIT)
                        ((Exit)e).openned();
        }

		setChanged();
		notifyObservers();
	}
	public int getRemainingDiamonds() { return this.remainingDiamonds; }

	public int getScore() { return this.score; }
	public void setScore(int score) { this.score = score; }

	public Entity[][] getWorld() { return this.world; }
	public Player getPlayer() { return this.player; }


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
	}

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

	public boolean isWin() { return this.win; }

	public void winned() { this.win = true; }

	public void addExplosion(final int[] coordinates)
    {
        this.explosions.add(coordinates);
    }

    public ArrayList<int[]> getExplosions() { return this.explosions; }
}
