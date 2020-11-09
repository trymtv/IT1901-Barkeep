package barkeep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a User
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    @Transient
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany
    @JoinColumn(name = "User1", referencedColumnName = "ID")
    @JoinColumn(name = "User2", referencedColumnName = "ID")
    private List<Friendship> friendList;

    @OneToMany
    @JoinColumn(name="OWNER", referencedColumnName = "ID")
    private List<IOweYou> iOweYouList = new ArrayList<>();

    public User() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }

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

    public void addIOweYou(IOweYou iou){
        if (!iOweYouList.contains(iou))
            this.iOweYouList.add(iou);
    }

    public void removeIOweYou(IOweYou iou){
        this.iOweYouList.remove(iou);
    }

    public boolean isPassword(String password) {
            return this.password.equals(password);
    }

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public User (int id, String name, String username, String password, String email){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public User (int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;

    }


    @Override
    public String toString() {
        return this.name;
    }
}
