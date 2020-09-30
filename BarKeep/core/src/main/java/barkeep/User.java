package barkeep;

/**
 * Class representing a User
 */
public class User {
    private String name;
    private int id;

    /**
     * Get id of User
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Get name of User
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of User
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor for User
     * @param name
     */
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
