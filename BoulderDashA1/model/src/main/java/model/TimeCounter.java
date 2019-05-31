package model;

/**
 * The TimeCounter class. Use a Thread.
 *
 * @author ETIENNE CANDAT AND GREGORY DENEUVE
 */
public class TimeCounter extends Thread {

    /** The model to which the thread is attached */
    private Model model;

    /**
     * Instantiates a new TimeCounter thread.
     *
     * @param model sets the model attached
     */
    public TimeCounter (Model model)
    {
        this.model = model;
    }

    /**
     * The run method of a thread.
     * In this one, the remaining time is updated.
     */
    public void run()
    {
        while(true)
        {
            try
            {
                if(!this.model.isWin() && this.model.getIsAlivePlayer())
                {
                    Thread.sleep(1000);
                    this.model.setRemainingTime(this.model.getRemainingTime()-1);
                }
                else if(this.model.isWin() && this.model.getIsAlivePlayer())
                {
                    Thread.sleep(20);
                    this.model.setRemainingTime(this.model.getRemainingTime()-1);
                    this.model.setScore(this.model.getScore()+1);
                    if(this.model.getRemainingTime() == 0)
                    {
                        break;
                    }
                }
                else
                {
                    Thread.sleep(1000);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
