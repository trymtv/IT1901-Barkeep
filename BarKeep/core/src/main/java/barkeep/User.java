package barkeep;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a User
 */
public class User {
    private String name;
    private int id;
    private String username;
    private String password;
    private String email;
    private ArrayList<User> friendList = new ArrayList<>();
    private ArrayList<IOweYou> IOweYouList = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }

    public ArrayList<IOweYou> getIOweYouList() {
        return IOweYouList;
    }

    public void setIOweYouList(IOweYou... list) {
        this.IOweYouList.addAll(new ArrayList<>(Arrays.asList(list)));
    }

    public void addIOweYou(IOweYou IOweYou){
        if (!IOweYouList.contains(IOweYou))
            this.IOweYouList.add(IOweYou);
    }

    public void removeIOU(IOweYou IOweYou){
        this.IOweYouList.remove(IOweYou);
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

    @Override
    public String toString() {
        return this.name;
    }
}
