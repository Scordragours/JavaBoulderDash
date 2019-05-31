package model;

/**
 * The UpdateWorld class. Use a Thread.
 *
 * @author ETIENNE CANDAT AND GREGORY DENEUVE
 */
public class UpdateWorld extends Thread {

    /** The model to which the thread is attached */
    private Model model;

    /**
     * Instantiates a new UpdateWorld thread.
     *
     * @param model sets the model attached
     */
    public UpdateWorld(Model model)
    {
        this.model = model;
    }

    /**
     * The run method of a thread.
     * In this one, both SlidingBlocks and Enemies are updated.
     */
    public void run()
    {
        while(true)
        {
            try
            {
                this.model.updateSlidingBlocks();

                this.model.updateEnemies();

                //this.model.updateWorld();

                Thread.sleep(100);
                this.model.updateSlidingBlocks();
                Thread.sleep(100);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
