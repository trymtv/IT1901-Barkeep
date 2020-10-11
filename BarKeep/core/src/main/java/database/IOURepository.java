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

	public static List<IOU> getByOwner(User owner) throws SQLException, ClassNotFoundException {
		List<IOU> tmp;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE OWNER=" + owner.getId());
		tmp = parseResultSet(rs,owner,null);
		Database.close();
		return tmp;
	}

	public static List<IOU> getByFriend(User friend) throws SQLException, ClassNotFoundException {
		List<IOU> tmp;
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE FRIEND=" + friend.getId());
		tmp = parseResultSet(rs,null,friend);
		Database.close();
		return tmp;
	}
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
			System.out.println(e);
		}
		return  ious;
	}

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

	public static void delete(IOU iou) throws SQLException, ClassNotFoundException {
		Database.open();
		Database.delete("IOUS", "ID=" + iou.getId());
		Database.close();
	}

}
