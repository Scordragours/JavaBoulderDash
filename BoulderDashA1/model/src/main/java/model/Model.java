package model;


import java.sql.SQLException;
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
				x++;
				y = 0;
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
						world[x][y] = new Player(this, x,y);
						this.player = (Player)world[x][y];
						break;
					case 'B' :
						world[x][y] = new Dirt(this, x,y);
						break;
					case 'R' :
						world[x][y] = new RollingRock(this, x,y);
						break;
					case 'D' :
						world[x][y] = new Diamond(this, x,y);
						break;
					case 'I' :
						world[x][y] = new Wall(this, x,y, EntityType.WALL, true);
						break;
					case '1' :
						world[x][y] = new EnemyDiamond(this, x,y);
						break;
					case '2' :
						world[x][y] = new EnemyPoint(this, x,y);
						break;
					case 'E' :
						world[x][y] = new Exit(this, x,y);
						break;
					case 'O' :
						world[x][y] = new Wall(this, x,y, EntityType.OUTLINE, false);
						break;
					default:
						world[x][y] = null;
						break;
				}
				y++;
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
	    entity.setPositionX(x);
	    entity.setPositionY(y);
		world[x][y] = entity;

		setChanged();
		notifyObservers();
	}

	public void updateSlidingBlocks()
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

		for(int x = 0; x<26; x++)
		{
			for(int y = 0; y<48; y++)
			{
				if(world[x][y] != null) {
					switch (world[x][y].getType()) {
						case DIRT:
							cw[x][y] = 'B';
							break;
						case DIAMOND:
							cw[x][y] = 'D';
							break;
						case PLAYER:
							cw[x][y] = 'P';
							break;
						case WALL:
							cw[x][y] = 'I';
							break;
						case OUTLINE:
							cw[x][y] = 'O';
							break;
						case EXIT:
							cw[x][y] = 'E';
							break;
						case ENEMYPOINT:
							cw[x][y] = '2';
							break;
						case ENEMYDIAMOND:
							cw[x][y] = '1';
							break;
						case ROLLINGROCK:
							cw[x][y] = 'R';
							break;
						default:
							cw[x][y] = ' ';
							break;
					}
				}
				else
				{
					cw[x][y] = ' ';
				}
			}
		}

        return cw;
	}

	public boolean isWin() { return this.win; }
}
