package barkeep;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "friends",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"User1", "User2"})}

)
public class Friendship implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "User1", referencedColumnName = "ID")
  private User user1;

  @ManyToOne
  @JoinColumn(name = "User2", referencedColumnName = "ID")
  private User user2;

  public Friendship() {
  }

  public Friendship(User user1, User user2) {
    this.user1 = user1;
    this.user2 = user2;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser1() {
    return user1;
  }

  public void setUser1(User user1) {
    this.user1 = user1;
  }

  public User getUser2() {
    return user2;
  }

  public void setUser2(User user2) {
    this.user2 = user2;
  }
}
