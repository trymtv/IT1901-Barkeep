package database;

import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	public static User get(String username) throws SQLException, ClassNotFoundException {
		User user = null;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE username='" + username + "'");
		List<User> userResult = parseResultSet(rs);
		if(!userResult.isEmpty()){
			user = userResult.get(0);
		}
		Database.close();
		return user;
	}
	public static User get(int id) throws SQLException, ClassNotFoundException {
		User user = null;
		Database.open();

		ResultSet rs = Database.read("SELECT * FROM USERS WHERE ID=" + id);
		List<User> userResult = parseResultSet(rs);
		if(!userResult.isEmpty()){
			user = userResult.get(0);
		}

		Database.close();
		return user;
	}

	public static List<User> parseResultSet(ResultSet rs){
		List<User> users = new ArrayList<>();
		try {
			while(rs.next()){
				users.add(new User(rs.getInt("id"), rs.getString("username")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return users;
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
