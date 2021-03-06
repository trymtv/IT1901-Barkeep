package barkeep;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ious")
public class IOweYou implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OWNER", referencedColumnName = "ID")
  private User owner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Friend", referencedColumnName = "ID")
  private User user;

  @ManyToOne
  @JoinColumn(name = "drink", referencedColumnName = "ID")
  private Drink drink;

  @Column(name = "date")
  private LocalDateTime time;


  /**
   * Creates the IOweYou for a user containing
   * the user and drink that is owed.
   * A LocalDatetime object is initialized
   * to now on constructor call.
   *
   * @param user  - User that owes
   * @param drink - The drink that is owed
   */
  public IOweYou(User owner, User user, Drink drink) {
    this.owner = owner;
    this.user = user;
    this.drink = drink;
    this.time = LocalDateTime.now();
  }

  public IOweYou() {
    this.time = LocalDateTime.now();
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Drink getDrink() {
    return drink;
  }

  public void setDrink(Drink drink) {
    this.drink = drink;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return "IOweYou{"
        + "owner=" + owner
        + ", user=" + user
        + ", drink=" + drink
        + ", time=" + time.format(formatter)
        + '}';
  }

}
