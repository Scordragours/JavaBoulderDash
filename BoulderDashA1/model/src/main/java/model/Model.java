package model;


import java.util.Observable;

import contract.IModel;


/**
 * The Class Model.
 *
 * @author CANDANT ETIENNE AND DENEUVE GR2GORY
 */
public final class Model extends Observable implements IModel {

	String level = new String();
	int time;
	int remainingTime;
	int diamond;
	int remainingDiamond;
	Entity[][] world;
	int score;
	Player player;

	public Model(int levelID)
	{

	}
	public Observable getObservable() {
		return null;
	}
	private void loadLevel(int levelID)
	{

	}

	private void buildWorld()
	{

	}
	public int getTime()
	{
		return 0;
	}
	public void setRemainingTime(int time)
	{

	}
	public int getRemainingTime()
	{
		return 0;
	}
	public int getDiamonds()
	{
		return 0;
	}
	public int getScore()
	{
		return 0;
	}
	public void setScore()
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
	public void updateStats()
	{

	}
	public void updateEntitiesState()
	{

	}
	public void updateSlidingBlocks()
	{

	}
}
