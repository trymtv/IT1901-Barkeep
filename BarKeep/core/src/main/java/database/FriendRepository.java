package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository {

	/**
	 * Gets a list of IDs of friends from the given {@link barkeep.User}-id
	 * @param userid the given userid
	 * @return List<Integer> of IDs from the {@link barkeep.User}'s friends
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<Integer> get(int userid) throws SQLException, ClassNotFoundException {
		List<Integer> friends = new ArrayList<>();
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM FRIENDS WHERE USER1="+ userid + " OR USER2=" +userid);
		while (rs.next()){
			if (rs.getInt("USER1") != userid)
					friends.add(rs.getInt("USER1"));
			else{
				friends.add(rs.getInt("USER2"));
			}
		}
		Database.close();
		if (friends.isEmpty()){
			return null;
		}
		return friends;
	}

	/**
	 * Gets a list of IDs of friends from the given {@link barkeep.User} username.
	 * @param username the given username
	 * @return List<Integer> of IDs from the {@link barkeep.User}'s friends
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<Integer> get(String username) throws SQLException, ClassNotFoundException {
		int id;
		Database.open();
		ResultSet sr = Database.read("SELECT * FROM USERS WHERE USERNAME='" +username+ "'");
		if (sr.next())
			id = sr.getInt("id");
		else {
			Database.close();
			return null;
		}
		Database.close();
		return get(id);
	}


	/**
	 * Stores a new friendship defined by the two {@link barkeep.User}s
	 * @param user1 the first user
	 * @param user2 the second user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void store(int user1, int user2) throws SQLException, ClassNotFoundException {
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM FRIENDS WHERE (USER1="+ user1 + " AND USER2=" + user2+ ")" +
				"OR (USER1="+ user2 + " AND USER2="+user1+")");
		if (!rs.next()){
			Database.insert("FRIENDS", "NULL", Integer.toString(user1), Integer.toString(user2));
		}
		Database.close();
	}


	/**
	 * Deletes an existing friendship defined by the two {@link barkeep.User}s
	 * @param user1 the first user
	 * @param user2 the second user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void delete(int user1, int user2) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("FRIENDS", "(USER1=" + user1+ " AND USER2=" + user2+
				") OR (USER1 =" + user2 + " AND USER2 = " + user1 + ")");
		Database.close();
	}
}

