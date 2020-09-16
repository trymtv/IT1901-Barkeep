package barkeep;

/**
 * Class representing a Drink
 */
public class Drink {
    private String name;
    private int id;
    private float value;

    /**
     * Get name of drink
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of drink
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get id of drink
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get value of drink
     * @return value
     */
    public float getValue() {
        return value;
    }

    /**
     * Set value of Drink
     * @param value
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Constructor
     * @param name
     * @param value
     */
    public Drink(String name, float value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
