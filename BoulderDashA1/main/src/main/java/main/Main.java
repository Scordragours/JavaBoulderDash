/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package main;

import controller.Controller;
import model.Model;
import model.TimeCounter;
import model.UpdateWorld;
import view.ViewFrame;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     *
     * @throws Exception
     *          for bad moving assignation
     */
    public static void main(final String[] args) throws Exception {
        Model model = new Model(41,1);

        UpdateWorld uw = new UpdateWorld(model);
        uw.start();
        TimeCounter tc = new TimeCounter(model);
        tc.start();
        ViewFrame viewFrame = new ViewFrame(model);
        Controller controller = new Controller(viewFrame, model);
        viewFrame.setController(controller);
        viewFrame.Run();
    }
}
