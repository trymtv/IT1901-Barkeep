package database;

import barkeep.Drink;
import barkeep.IOU;
import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IOURepository {


	/**
	 * Returnes the list of {@link IOU}s defined by {@link User} owning it.
	 * @param owner the user that owns the IOU
	 * @return List<IOU> defined by the given user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<IOU> getByOwner(User owner) throws SQLException, ClassNotFoundException {
		List<IOU> tmp;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE OWNER=" + owner.getId());
		tmp = parseResultSet(rs,owner,null);
		Database.close();
		return tmp;
	}

	/**
	 * Returns a list of {@link IOU}s defined by the {@link User} that owes as a friend
	 * @param friend the user in debt
	 * @return List<IOU> defined by the user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<IOU> getByFriend(User friend) throws SQLException, ClassNotFoundException {
		List<IOU> tmp;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE FRIEND=" + friend.getId());
		tmp = parseResultSet(rs,null,friend);
		Database.close();
		return tmp;
	}

	/**
	 * Parses a given {@link ResultSet} to a list of {@link IOU}s
	 * @see ResultSet
	 * @param rs the given resultset
	 * @return the parsed list of {@link IOU}s
	 */
	public static List<IOU> parseResultSet(ResultSet rs, User owner, User friend){
		List<IOU> ious = new ArrayList<>();
		try {
			while (rs.next()){
				if(owner == null){
					owner = UserRepository.get(rs.getInt("OWNER"));
				}
				if(friend == null){
					friend = UserRepository.get(rs.getInt("FRIEND"));
				}
				Drink drink = DrinkRepository.get(rs.getInt("DRINK"));
				IOU iou = new IOU(owner, friend, drink);
				Timestamp ts = rs.getTimestamp("DATE");
				iou.setTime(ts.toLocalDateTime());
				ious.add(iou);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return  ious;
	}

	/**
	 * Stores the given {@link IOU}
	 * @param iou the {@link IOU} to be stored
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void store(IOU iou) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.insert("IOUS", "NULL",
				Integer.toString(iou.getOwner().getId()),
				Integer.toString(iou.getUser().getId()),
				Integer.toString(iou.getDrink().getId()),
				"1",
				iou.getTime().toString());
		Database.close();
	}


	/**
	 * Deletes the given {@link IOU}
	 * @param iou the {@link IOU} to be deleted
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static void delete(IOU iou) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("IOUS", "ID=" + iou.getId());
		Database.close();
	}

}
