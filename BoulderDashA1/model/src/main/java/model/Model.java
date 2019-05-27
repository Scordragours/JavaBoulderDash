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
	private Entity[][] world;
	private int score;
	private Player player;
	private int LevelTexture;

	public Model(int levelID, int levelTexture)
	{
		this.loadLevel(levelID);
	}
	public Observable getObservable() {
		return null;
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
	public int getLevelTexture()
	{
		return 1;
	}

	private void buildWorld()
	{

	}

	public Level getLevel() { return this.level;}


	public void setRemainingTime(int time)
	{

	}
	public int getRemainingTime()
	{
		return 0;
	}

	public void setRemainingDiamonds(int remainingDiamond) { }
	public int getRemainingDiamonds() { return this.remainingDiamonds; }

	public int getScore()
	{
		return 0;
	}
	public void setScore(int score)
	{

	}
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
