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
	Entity world = new Entity[][];
	int score;
	Player player= new Player();

	public Model(int levelID)
	{

	}
	public Observable getObservable() {

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
	public Entity getWorld()
	{

	}
	public Player getPlayer()
	{

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
