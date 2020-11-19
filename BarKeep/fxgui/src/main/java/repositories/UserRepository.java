package repositories;

import barkeep.Drink;
import barkeep.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

	/**
	 * Returns a user from the database defined by the {@link User} name
	 * @param username the user username
	 * @return the {@link User} with the given username, else null
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static User get(String username) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(HttpManager.getFrom("/user/username/" + username), User.class);
        }catch (IOException e){
            return null;
        }
    }

	public static List<User> getAllExcept(String username) throws SQLException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users;
        try {
            users = mapper.readValue(HttpManager.getFrom("/user/all"), new TypeReference<>() {
            });
        } catch (IOException e) {
            return null;
        }
        return users.stream().filter(u -> !u.getUsername().equals(username)).collect(Collectors.toList());
	}

	/**
	 * Returns a user from the database defined by the {@link User} id
	 * @param id the user id
	 * @return the {@link User} with the given id, else null
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static User get(int id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(HttpManager.getFrom("/user/" + id), User.class);
        } catch (IOException e) {
            return null;
        }
	}

	/**
	 * Stores the given {@link User}
	 * @param user the {@link User} to store
	 * @throws SQLException exception in database query
	 * @throws ClassNotFoundException the database driver was not found
	 */
	public static int store(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String requestBody = mapper.writeValueAsString(user);
            return HttpManager.postJsonTo("/user/register", requestBody);
        } catch (IOException | AuthenticationException e) {
            return 400;
        }
	}
}
