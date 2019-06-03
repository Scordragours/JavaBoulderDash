package main;

import controller.Controller;
import model.Model;
import model.TimeCounter;
import model.UpdateWorld;
import view.ViewFrame;

/**
 * The Class Main.
 *
 * @author Everyone in the groupe one
 */
public abstract class Main {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     *
     * @throws Exception
     *          all exception in model, view or controller
     */
    public static void main(final String[] args) throws Exception {
        Model model = new Model(6,6);

        UpdateWorld uw = new UpdateWorld(model);
        uw.start();
        TimeCounter tc = new TimeCounter(model);
        tc.start();

        ViewFrame viewFrame = new ViewFrame();
        viewFrame.Observer(model);
        Controller controller = new Controller(viewFrame, model);
        controller.start();

        viewFrame.setController(controller);
        viewFrame.Run();
    }
}
