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
