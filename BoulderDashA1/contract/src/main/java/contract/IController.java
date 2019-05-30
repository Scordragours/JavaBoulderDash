package contract;

/**
 * The Interface IController.
 *
 * @author Nathan PORET
 */
public interface IController {

	/**
	 * Order perform.
	 *
	 * @param controllerOrder
	 *          the controller order
	 * @throws Exception
	 * 			for bad direction assign to the player.
	 *
	 */
	public void orderPerform(ControllerOrder controllerOrder) throws Exception;
}
