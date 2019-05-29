package model;

public class UpdateWorld extends Thread {

    private Model model;

    public UpdateWorld(Model model)
    {
        this.model = model;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                this.model.updateSlidingBlocks();

                this.model.updateEnemies();

                Thread.sleep(250);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
