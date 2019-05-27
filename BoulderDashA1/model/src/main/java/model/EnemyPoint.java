package model;

public class EnemyPoint extends Enemy {

    public EnemyPoint(Model model,int x, int y)
    {
        super(model,x,y,EntityType.ENEMYPOINT);
    }

    public void die() throws Exception
    {
        this.explode();


    }
}
