package database;

import barkeep.Drink;
import barkeep.IOweYou;
import barkeep.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IOweYouRepository {


    /**
     * Returnes the list of {@link IOweYou}s defined by {@link User} owning it.
     *
     * @param owner the user that owns the IOweYou
     * @return List defined by the given user
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static List<IOweYou> getByOwner(User owner) throws SQLException, ClassNotFoundException {
        List<IOweYou> tmp;
        Database.open();
        ResultSet rs = Database.read("SELECT * FROM IOUS WHERE OWNER=" + owner.getId());
        tmp = parseResultSet(rs);
        Database.close();
        return tmp;
    }

    /**
     * Returns a list of {@link IOweYou}s defined by the {@link User} that owes as a friend.
     *
     * @param friend the user in debt
     * @return A list of IOweYous defined by the user
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static List<IOweYou> getByFriend(User friend)
            throws SQLException, ClassNotFoundException {
        List<IOweYou> tmp;
        Database.open();
        ResultSet rs = Database.read("SELECT * FROM IOUS WHERE FRIEND=" + friend.getId());
        tmp = parseResultSet(rs);
        Database.close();
        return tmp;
    }

    /**
     * Parses a given {@link ResultSet} to a list of {@link IOweYou}s.
     *
     * @see ResultSet
     * @param rs the given resultset
     * @return the parsed list of {@link IOweYou}s
     */
    public static List<IOweYou> parseResultSet(ResultSet rs) {
        List<IOweYou> ious = new ArrayList<>();
        User owner;
        User friend;
        try {
            while (rs.next()) {
                owner = UserRepository.get(rs.getInt("OWNER"));
                friend = UserRepository.get(rs.getInt("FRIEND"));
                Drink drink = DrinkRepository.get(rs.getInt("DRINK"));
                IOweYou newIOweYou = new IOweYou(owner, friend, drink);
                newIOweYou.setId(rs.getInt("ID"));
                Timestamp ts = rs.getTimestamp("DATE");
                newIOweYou.setTime(ts.toLocalDateTime());
                ious.add(newIOweYou);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ious;
    }

    /**
     * Stores the given {@link IOweYou}.
     *
     * @param iou the {@link IOweYou} to be stored
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static void store(IOweYou iou) throws SQLException, ClassNotFoundException {
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
     * Deletes the given {@link IOweYou}.
     *
     * @param iou the {@link IOweYou} to be deleted
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static void delete(IOweYou iou) throws SQLException, ClassNotFoundException {
        Database.open();
        Database.delete("IOUS", "ID=" + iou.getId());
        Database.close();
    }

}
