package repositories;

import barkeep.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IOweYouRepository {


	/**
	 * Returnes the list of {@link IOweYou}s defined by {@link User} owning it.
     *
	 * @param owner the user that owns the IOweYou
	 * @return IOWeYou list defined by the given user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<IOweYou> getByOwner(User owner) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(HttpManager.getFrom("/ioweyou/" + owner.getId()
                    + "/owesuser"), new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
	}

	/**
	 * Returns a list of {@link IOweYou}s defined by the {@link User} that owes as a friend.
     *
	 * @param friend the user in debt
	 * @return IOweYou list defined by the user
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static List<IOweYou> getByFriend(User friend) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(HttpManager.getFrom("/ioweyou/" + friend.getId()
                    + "/userowes"), new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
	}


	/**
	 * Stores the given {@link IOweYou}.
     *
	 * @param iou the {@link IOweYou} to be stored.
     * @return true if the store is successful
	 */
	public static boolean store(IOweYou iou) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            iou.setTime(LocalDateTime.now());
            IOweYouDTO iouDto  = new IOweYouDTO(iou);
            String requestBody = mapper.writeValueAsString(iouDto);
            return HttpManager.postJsonTo("/ioweyou/", requestBody) == 200;
        } catch (IOException | AuthenticationException e) {
            return false;
        }
	}


	/**
	 * Deletes the given {@link IOweYou}.
     *
	 * @param iou the {@link IOweYou} to be deleted
     * @return true if the store is successful
	 */
	public static boolean delete(IOweYou iou) {
        try {
            return HttpManager.deleteFrom("/ioweyou/" + iou.getId()) == 200;
        } catch (IOException | AuthenticationException ignored) {
            return false;
        }
	}
}
