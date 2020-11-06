package database;

import barkeep.Drink;
import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	/**
	 * Returns a user from the database defined by the {@link User} name
	 * @param username the user username
	 * @return the {@link User} with the given username, else null
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
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

	public static List<User> getAllExcept(String username) throws SQLException, ClassNotFoundException {
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE username!='" + username + "'");
		List<User> userResult = parseResultSet(rs);
		Database.close();
		return userResult;
	}

	/**
	 * Returns a user from the database defined by the {@link User} id
	 * @param id the user id
	 * @return the {@link User} with the given id, else null
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
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

	/**
	 * Parses a given {@link ResultSet} to a list of {@link User}s
	 * @see ResultSet
	 * @param rs the given resultset
	 * @return the parsed list of {@link User}s, if the set is empty an empty list
	 */
	public static List<User> parseResultSet(ResultSet rs){
		List<User> users = new ArrayList<>();
		try {
			while(rs.next()){
				users.add(new User(rs.getInt("id"), rs.getString("username")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * Deletes the given {@link User}
	 * @param user the {@link User} to delete
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void delete(User user) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("USERS", "USERNAME='" + user.getName() + "'");
		Database.close();
	}

	/**
	 * Stores the given {@link User}
	 * @param user the {@link User} to store
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void store(User user) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.insert("USERS", "NULL", user.getName(), "test", "test");
		Database.close();
	}
}
