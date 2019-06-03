package model.level;

/** This class convert the data from database into level
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Level extends Data {
   /** The id of level **/
    private int id;
    /** The time of level **/
    private int time;
    /** The number of diamond **/
    private int nbDiamond;
    /** The level content **/
    private String level;

    /**
     * Level constructor
     */
    public Level()
    {
        this(0, 0, 0, "");
    }

    /**
     *
     * @param id the number of level
     * @param time the time of level
     * @param nbDiamond the number of diamond
     * @param level the content level
     */
    public Level(final int id, final int time, final int nbDiamond, final String level)
    {
        this.setId(id);
        this.setTime(time);
        this.setNbDiamond(nbDiamond);
        this.setLevel(level);
    }

    /**
     * Gets the id
     * @return the id
     */

    public int getId()
    {
        return this.id;
    }

    /**
     * Sets the id
     * @param id the number of level
     */

    private void setId(int id) { this.id = id; }

    /**
     * Gets the time
     * @return the time
     */
    public int getTime()
    {
        return this.time;
    }

    /**
     * Sets the time
     * @param time the time
     */
    private void setTime(int time) { this.time = time; }

    /**
     * Gets the number of diamond
     * @return the number of diamond
     */
    public int getNbDiamond()
    {
        return this.nbDiamond;
    }

    /**
     * Sets number of diamonds
     * @param nbDiamond the number of diamond
     */
    private void setNbDiamond(int nbDiamond) { this.nbDiamond = nbDiamond; }

    /**
     * Gets Level
     * @return level
     */
    public String getLevel()
    {
        return this.level;
    }

    /**
     * Sets Level
     * @param level the level
     */
    public void setLevel(String level) { this.level = level; }

}
