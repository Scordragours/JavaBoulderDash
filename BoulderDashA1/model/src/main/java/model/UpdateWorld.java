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
                System.out.println("boucle");
                this.model.updateSlidingBlocks();

                for(Entity[] eTab : this.model.getWorld())
                    for(Entity e : eTab)
                        if(e instanceof Enemy)
                            ((Enemy) e).pathFinder();

                Thread.sleep(250);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
