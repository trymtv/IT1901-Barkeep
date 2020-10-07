package database;

import barkeep.Drink;
import barkeep.IOU;
import barkeep.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseIOU {

	public static List<IOU> getByOwner(User owner) throws SQLException, ClassNotFoundException {
		List<IOU> tmp = new ArrayList<>();
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE OWNER=" + owner.getId());
		while (rs.next()){
			User friend = DatabaseUser.get(rs.getInt("FRIEND"));
			Drink drink = DatabaseDrink.get(rs.getInt("DRINK"));
			IOU iou = new IOU(owner, friend, drink);
			Timestamp ts = rs.getTimestamp("DATE");
			iou.setTime(ts.toLocalDateTime());
			tmp.add(iou);
		}
		Database.close();
		return tmp;
	}

	public static List<IOU> getByFriend(User friend) throws SQLException, ClassNotFoundException {
		List<IOU> tmp = new ArrayList<>();
		Database.open();
		ResultSet rs = Database.read("SELECT * FROM IOUS WHERE FRIEND=" + friend.getId());
		while (rs.next()){
			User owner = DatabaseUser.get(rs.getInt("OWNER"));
			System.out.println(owner);
			Drink drink = DatabaseDrink.get(rs.getInt("DRINK"));
			IOU iou = new IOU(owner, friend, drink);
			Timestamp ts = rs.getTimestamp("DATE");
			iou.setTime(ts.toLocalDateTime());
			tmp.add(iou);
		}
		Database.close();
		return tmp;
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
