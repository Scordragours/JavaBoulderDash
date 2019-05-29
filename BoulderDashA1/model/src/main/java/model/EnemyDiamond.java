package model;

public class EnemyDiamond extends Enemy {

    public EnemyDiamond(Model model,int x, int y)
    {
        super(model,x,y,EntityType.ENEMYDIAMOND);
    }

    public void die() throws Exception
    {
        this.explode(true);
    }
}