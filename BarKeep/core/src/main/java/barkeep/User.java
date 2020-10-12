package barkeep;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a User
 */
public class User {
    private String name;
    private int id;
    private String password;
    private String email;
    private ArrayList<User> friendList = new ArrayList<>();
    private ArrayList<IOU> iouList = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() { return name; }


    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }

    public ArrayList<IOU> getIOUList() {
        return iouList;
    }

    public void setIOUList(IOU... list) {
        this.iouList.addAll(new ArrayList<>(Arrays.asList(list)));
    }

    public void addIOU(IOU iou){
        if (!iouList.contains(iou))
            this.iouList.add(iou);
    }

    public void removeIOU(IOU iou){
        this.iouList.remove(iou);
    }

    public boolean isPassword(String password) {
            return this.password.equals(password);
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

    }
    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public User (int id, String name, String password, String email){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;

    }

    @Override
    public String toString() {
        return this.name;
    }
}
