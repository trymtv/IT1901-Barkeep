package barkeep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * Class representing a User
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @NotBlank
    private String username;

    @Column
    @Size(min = 8)
    private String password;

    @Column
    @Email
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "User1", referencedColumnName = "ID")
    @JoinColumn(name = "User2", referencedColumnName = "ID")
    private List<Friendship> friendList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER", referencedColumnName = "ID")
    private List<IOweYou> iOweYouList = new ArrayList<>();

    public User() {

    }

    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User(int id, String username) {
        this.username = username;
        this.id = id;
    }


    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Friendship> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friendship> friendList) {
        this.friendList = friendList;
    }

    public List<IOweYou> getIOweYouList() {
        return iOweYouList;
    }

    public void setIOweYouList(List<IOweYou> iouList) {
        this.iOweYouList = iouList;
    }

    public void addIOweYou(IOweYou iou) {
        if (!iOweYouList.contains(iou)) {
            this.iOweYouList.add(iou);
        }
    }

    public void removeIOweYou(IOweYou iou) {
        this.iOweYouList.remove(iou);
    }

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return this.username;
    }
}
