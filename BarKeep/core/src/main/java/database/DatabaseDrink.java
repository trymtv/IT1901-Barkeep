package database;

import barkeep.Drink;
import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseDrink {

	public static Drink get(int id) throws SQLException, ClassNotFoundException {
		Drink tmp = null;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM DRINKS WHERE id=" + id);
		while (rs.next()){
			tmp = new Drink(rs.getString("NAME"), rs.getFloat("VALUE"));
			tmp.setId(rs.getInt("ID"));
		}
		Database.close();
		return tmp;
	}

	public static void store(Drink drink) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.insert("DRINKS", "NULL", drink.getName(), Double.toString(drink.getValue()));
		Database.close();
	}

	public static void delete(Drink drink) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("DRINKS", "NAME='" + drink.getName() + "'");
		Database.close();
	}
}
