package model;

/**
 * This class use a thread to update the entities that needs to move over
 * the time. Every 150 milliseconds, call {@link Model#updateSlidingBlocks()}
 * and {@link Model#updateEnemies()} to update every Sliding Block and enemies
 * in the model's world.
 *
 * @author ETIENNE CANDAT AND GREGORY DENEUVE
 *
 * @see java.lang.Thread
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
     *
     * @see Thread#run()
     */
    public void run()
    {
        while(true)
        {
            try
            {
                if(this.model.isWin() || !this.model.getPlayer().isAlive())
                {
                    break;
                }
                this.model.updateSlidingBlocks();
                this.model.updateEnemies();
                Thread.sleep(150);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
