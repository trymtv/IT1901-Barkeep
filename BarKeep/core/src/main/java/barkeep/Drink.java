package barkeep;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class representing a Drink
 */
@Entity
@Table(name = "drinks")
public class Drink implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private double value;

    public Drink() { }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Constructor
     * @param name the name of the drink
     * @param value the value of the drink
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
