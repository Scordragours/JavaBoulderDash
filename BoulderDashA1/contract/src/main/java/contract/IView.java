package contract;

/**
 * The Interface IView.
 *
 * @author Arthur Lecras
 */
public interface IView{
	/**
	 * The method that allows the controller to put the person on hold.
	 *
	 * @param StandBy Says whether or not the person should be on standby.
	 */
	void setStandBy(boolean StandBy);
}