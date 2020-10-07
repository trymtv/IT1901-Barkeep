package barkeep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOU {
	private int id;
	private final User owner;
	private final User user;
	private final Drink drink;
	private LocalDateTime time;


	/**
	 * Creates the IOU for a user containing
	 * the user and drink that is owed.
	 * A LocalDatetime object is initialized
	 * to now on constructor call.
	 *
	 * @param user - User that owes
	 * @param drink - The drink that is owed
	 */
	public IOU(User owner, User user, Drink drink){
		this.owner = owner;
		this.user = user;
		this.drink = drink;
		this.time = LocalDateTime.now();
	}

	public User getOwner() {
		return owner;
	}

	public Drink getDrink() {
		return drink;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time){
		this.time = time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return "IOU{" +
				"user=" + user +
				", drink=" + drink +
				", time=" + time.format(formatter) +
				'}';
	}

}
