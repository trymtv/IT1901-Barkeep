package barkeep;

import java.time.LocalDateTime;

public class IOweYouDTO {
    private int id;
    private UserDTO owner;
    private UserDTO user;
    private Drink drink;
    private LocalDateTime time;

    public IOweYouDTO() {
    }
    public IOweYouDTO(IOweYou iou){
        id = iou.getId();
        owner = new UserDTO(iou.getOwner());
        user = new UserDTO(iou.getUser());
        drink = iou.getDrink();
        time = iou.getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
