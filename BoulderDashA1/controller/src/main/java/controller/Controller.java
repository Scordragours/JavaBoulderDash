package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
/**
 * The class Controller
 *
 * @author Nathan PORET
 */
public final class Controller implements IController {

	/** The  view*/
	private IView view;
	/** The  model*/
	private IModel model;
	/** A timer*/
	private int timer = 0;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}
	/**
	 * 	Set the view.
	 * @param pview
	 * 			the view
	 *
	 */

	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 *  Set the model.
	 * @param model
	 * 			the model
	 */

	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Control if the time when the player is immobile.
	 * @param timerActive
	 * 			the active timer
	 */

	private void MotionLessControl(boolean timerActive) {
		if (timerActive) {
			if (this.timer == 0) {
				this.timer = this.model.getRemainingTime();
			}
			if ((this.timer - this.model.getRemainingTime()) >= 1) {
				this.view.setStandBy(true);
			}
		}
		else {
			this.timer = 0;
			this.view.setStandBy(false);
		}
	}

	/**
	 * Look the movement of the player and his status.
	 *
	 * @param controllerOrder
	 *            the controller order
	 *
	 * @throws Exception for bad direction assign to the player
	 *
	 */

	public void orderPerform(final ControllerOrder controllerOrder) throws Exception {
		switch (controllerOrder) {
			case LEFT:
				this.model.setMovePlayer(-1,0);
				MotionLessControl(false);
				break;
			case RIGHT:
				this.model.setMovePlayer(1,0);
				MotionLessControl(false);
				break;
			case UP:
				this.model.setMovePlayer(0,-1);
				MotionLessControl(false);
				break;
			case DOWN:
				this.model.setMovePlayer(0,1);
				MotionLessControl(false);
				break;
			default:
				MotionLessControl(true);
				break;
		}
	}
}
