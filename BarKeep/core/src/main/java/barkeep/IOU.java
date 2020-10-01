package barkeep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOU {
	private final User user;
	private final Drink drink;
	private final LocalDateTime time;


	/**
	 * Creates the IOU for a user containing
	 * the user and drink that is owed.
	 * A LocalDatetime object is initialized
	 * to now on constructor call.
	 *
	 * @param user - User that owes
	 * @param drink - The drink that is owed
	 */
	public IOU(User user, Drink drink){
		this.user = user;
		this.drink = drink;
		this.time = LocalDateTime.now();
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

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "IOU{" +
				"user=" + user +
				", drink=" + drink +
				", time=" + time.format(formatter) +
				'}';
	}

}
