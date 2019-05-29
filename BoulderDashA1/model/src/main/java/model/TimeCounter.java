package model;

public class TimeCounter extends Thread {

    private Model model;

    public TimeCounter (Model model)
    {
        this.model = model;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                if(!this.model.isWin() && this.model.getIsAlivePlayer())
                {
                    Thread.sleep(1000);
                    this.model.setRemainingTime(this.model.getRemainingTime()-1);;
                }
                else if(this.model.isWin() && this.model.getIsAlivePlayer())
                {
                    Thread.sleep(20);
                    this.model.setRemainingTime(this.model.getRemainingTime()-1);
                    this.model.setScore(this.model.getScore()+1);
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
