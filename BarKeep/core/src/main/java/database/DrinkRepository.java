package database;

import barkeep.Drink;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {

    /**
     * Returns all the {@link Drink} rows from the database.
     *
     * @return a list of all {@link Drink}
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static List<Drink> getAll() throws SQLException, ClassNotFoundException {
        List<Drink> drinks;
        Database.open();
        ResultSet rs = Database.read("SELECT * FROM DRINKS");
        drinks = parseResultSet(rs);
        Database.close();
        return drinks;
    }

    /**
     * Returns a drink from the database defined by the {@link Drink} id.
     *
     * @param id the row id
     * @return the {@link Drink} with the given id, else null
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static Drink get(int id) throws SQLException, ClassNotFoundException {
        Drink tmp = null;
        Database.open();
        ResultSet rs = Database.read("SELECT * FROM DRINKS WHERE id=" + id);
        List<Drink> drinkResult = parseResultSet(rs);
        if (!drinkResult.isEmpty()) {
            tmp = drinkResult.get(0);
        }
        Database.close();
        return tmp;
    }


    /**
     * Parses a given {@link ResultSet} to a list of {@link Drink}.
     *
     * @see ResultSet
     * @param rs the given resultset
     * @return the parsed list of {@link Drink}
     */
    public static List<Drink> parseResultSet(ResultSet rs) {
        List<Drink> drinks = new ArrayList<>();

        try {
            while (rs.next()) {
                Drink drink = new Drink(rs.getString("NAME"), rs.getFloat("VALUE"));
                drink.setId(rs.getInt("ID"));
                drinks.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    /**
     * Stores the given {@link Drink} with a generated id.
     *
     * @param drink the {@link Drink} to be stored
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static void store(Drink drink) throws SQLException, ClassNotFoundException {
        Database.open();
        Database.insert("DRINKS", "NULL", drink.getName(), Double.toString(drink.getValue()));
        Database.close();
    }


    /**
     * Deletes a {@link Drink} defined by the drinks id.
     *
     * @param drink the {@link Drink} to delete
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static void delete(Drink drink) throws SQLException, ClassNotFoundException {
        Database.open();
        Database.delete("DRINKS", "NAME='" + drink.getName() + "'");
        Database.close();
    }
}
