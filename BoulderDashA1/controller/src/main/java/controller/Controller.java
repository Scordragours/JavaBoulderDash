package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The class Controller
 * @Author Nathan PORET
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
	 */

	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 *  Set the model.
	 * @param model
	 */

	private void setModel(final IModel model) {
		this.model = model;
	}

	public void control() {

	}

	/**
	 * Control if the time when the player is immobile.
	 * @param timerActive
	 */

	private void MotionLessControl(boolean timerActive) {
		if (timerActive == true) {
			if (this.timer == 0) {
				this.timer = this.model.getTime();
			}
			if (this.timer - this.model.getTime() == 3) {
				this.view.setStanby(true);
			}
		}
		else{
			this.timer = 0;
			this.view.setStanby(false);
		}
	}

	/**
	 * Look the movement of the player and his status.
	 *
	 * @param controllerOrder
	 *            the controller order
	 */

	public void orderPerform(final ControllerOrder controllerOrder) {
		switch (controllerOrder) {
			case LEFT:
				this.model.getPlayer().move(-1,0);
				MotionLessControl(false);
				break;
			case RIGHT:
				this.model.getPlayer().move(1,0);
				MotionLessControl(false);
				break;
			case UP:
				this.model.getPlayer().move(0,-1);
				MotionLessControl(false);
				break;
			case DOWN:
				this.model.getPlayer().move(0,1);
				MotionLessControl(false);
				break;
			case WIN:
				this.model.isWin(true);
				break;
			case LOSE:
				this.model.getPlayer().isAlive(false);
				break;
			default:
				MotionLessControl(true);
				break;
		}
	}
}
