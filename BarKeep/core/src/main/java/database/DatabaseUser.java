package database;

import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUser {
	public static User get(String username) throws SQLException, ClassNotFoundException {
		User tmp = null;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE username='" + username + "'");
		while (rs.next()){
			tmp = new User(rs.getInt("id"), rs.getString("username"), "", "", "");
		}
		Database.close();
		return tmp;
	}
	public static User get(int id) throws SQLException, ClassNotFoundException {
		User tmp = null;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE ID=" + id);
		while (rs.next()){
			tmp = get(rs.getString("username"));
		}
		Database.close();
		return tmp;
	}

	public static void delete(User user) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("USERS", "USERNAME='" + user.getName() + "'");
		Database.close();
	}

	public static void store(User user) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.insert("USERS", "NULL", user.getName(), "test", "test");
		Database.close();
	}
}
