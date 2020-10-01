package barkeep;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.BarKeepModule;
import json.DrinkSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Class representing a Drink
 */
public class Drink {
    private String name;
    private int id;
    private double value;

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
    public double getValue() {
        return value;
    }

    /**
     * Set value of Drink
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Constructor
     * @param name
     * @param value
     */
    public Drink(String name, double value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
