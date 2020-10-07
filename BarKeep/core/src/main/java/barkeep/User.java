package barkeep;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a User
 */
public class User {
    private String name;
    private int id;
    private ArrayList<User> friendList = new ArrayList<>();
    private ArrayList<IOU> iouList = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public User(String name) {
        this.name = name;
    }

    public User (int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
