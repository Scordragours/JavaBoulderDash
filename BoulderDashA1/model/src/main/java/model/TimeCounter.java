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
                Thread.sleep(1000);
                System.out.println("sec-1");
                this.model.setRemainingTime(this.model.getRemainingTime() -1 );
                System.out.println("time remaining " + this.model.getRemainingTime());
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
