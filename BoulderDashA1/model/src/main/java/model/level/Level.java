package model.level;

public class Level extends Data {
    private int id;
    private int time;
    private int nbDiamond;
    private String level;

    public Level()
    {
        this(0, 0, 0, "");
    }
    public Level(final int id, final int time, final int nbDiamond, final String level)
    {
        this.setId(id);
        this.setTime(time);
        this.setNbDiamond(nbDiamond);
        this.setLevel(level);
    }

    public int getId()
    {
        return this.id;
    }

    private void setId(int id) { this.id = id; }

    public int getTime()
    {
        return this.time;
    }

    private void setTime(int time) { this.time = time; }

    public int getNbDiamond()
    {
        return this.nbDiamond;
    }

    private void setNbDiamond(int nbDiamond) { this.nbDiamond = nbDiamond; }

    public String getLevel()
    {
        return this.level;
    }

    public void setLevel(String level) { this.level = level; }

}
