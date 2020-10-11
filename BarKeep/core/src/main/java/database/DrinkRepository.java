package database;

import barkeep.Drink;
import barkeep.User;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {

	public static List<Drink> getAll() throws SQLException, ClassNotFoundException{
		List<Drink> drinks;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM DRINKS");
		drinks = parseResultSet(rs);
		Database.close();
		return drinks;
	}
	public static Drink get(int id) throws SQLException, ClassNotFoundException {
		Drink tmp = null;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM DRINKS WHERE id=" + id);
		List<Drink> drinkResult = parseResultSet(rs);
		if(!drinkResult.isEmpty()){
			tmp = drinkResult.get(0);
		}
		Database.close();
		return tmp;
	}

	public static List<Drink> parseResultSet(ResultSet rs){
		List<Drink> drinks = new ArrayList<>();

		try {
			while (rs.next()) {
				Drink drink = new Drink(rs.getString("NAME"), rs.getFloat("VALUE"));
				drink.setId(rs.getInt("ID"));
				drinks.add(drink);
			}
		} catch (SQLException e){
			System.out.println(e);
		}
		return drinks;
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
