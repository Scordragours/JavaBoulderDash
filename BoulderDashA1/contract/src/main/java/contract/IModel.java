package contract;

import java.util.ArrayList;
import java.util.Observable;
/**
 * The Interface IModel.
 *
 * @author CANDAT ETIENNE DENEUVE GREGORY
 */
public interface IModel {
	int getRemainingTime();

	boolean isWin();

	boolean getIsAlivePlayer();

	int[] getPositionsPlayer();

	void setMovePlayer(int x, int y) throws Exception;

	int getRemainingDiamonds();

	int getScore();

	char[][] convertWorld();

	ArrayList<int[]> getExplosions();

	int getLevelTexture();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
}
