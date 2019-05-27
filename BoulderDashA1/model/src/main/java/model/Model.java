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
	private Entity[][] world = new Entity[26][48];//{{null},{null},{null}};
	private int score;
	private Player player;
	private int levelTexture;

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
						world[x][y] = new Player(this, x,y, EntityType.PLAYER);
						break;
					case 'B' :
						world[x][y] = new Dirt(this, x,y, EntityType.DIRT);
						break;
					case 'R' :
						world[x][y] = new RollingRock(this, x,y, EntityType.ROLLINGROCK);
						break;
					case 'D' :
						world[x][y] = new Diamond(this, x,y, EntityType.DIAMOND);
						break;
					case 'O' :
						world[x][y] = new Wall(this, x,y, EntityType.WALL, true);
						break;
					case '1' :
						world[x][y] = new EnemyDiamond(this, x,y, EntityType.ENEMYDIAMOND);
						break;
					case '2' :
						world[x][y] = new EnemyPoint(this, x,y, EntityType.ENEMYPOINT);
						break;
					case 'E' :
						world[x][y] = new Exit(this, x,y, EntityType.EXIT);
						break;
					case 'I' :
						world[x][y] = new Wall(this, x,y, EntityType.INLINE, false);
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

	public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
	public int getRemainingTime() { return this.remainingTime; }

	public void setRemainingDiamonds(int remainingDiamond) { this.remainingDiamonds = remainingDiamond; }
	public int getRemainingDiamonds() { return this.remainingDiamonds; }

	public int getScore() { return this.score; }
	public void setScore(int score) { this.score = score; }

	public Entity[][] getWorld()
	{
		return null;
	}
	public Player getPlayer()
	{
		return null;
	}


	public void updateEntity(int posX, int posY)
	{

	}

	public void updateStats() {

	}

	public void updateSlidingBlocks()
	{

	}
	public char [][] ConvertWorld()
	{
        return null;
	}
}
