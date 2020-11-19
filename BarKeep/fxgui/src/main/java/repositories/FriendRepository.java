package repositories;

import barkeep.FriendshipDTO;
import barkeep.User;
import barkeep.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository {

    /**
     * Gets a list of IDs of friends from the given {@link barkeep.User}-id.
     *
     * @param userid the given userid
     * @return List of IDs from the {@link barkeep.User}'s friends
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static List<User> get(int userid) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            users = mapper.readValue(HttpManager.getFrom("/friendship/" + userid), new TypeReference<>() {
            });
        } catch (IOException e) {
        }
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    /**
     * Gets a list of IDs of friends from the given {@link barkeep.User} username.
     *
     * @param username the given username
     * @return List of IDs from the {@link barkeep.User}'s friends
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static List<User> get(String username) throws SQLException, ClassNotFoundException {
        return get(UserRepository.get(username).getId());
    }


    /**
     * Stores a new friendship defined by the two {@link barkeep.User}s.
     *
     * @param user1 the first user
     * @param user2 the second user
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static void store(int user1, int user2) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserDTO user1Dto = new UserDTO(UserRepository.get(user1));
            UserDTO user2Dto = new UserDTO(UserRepository.get(user2));
            FriendshipDTO fDTO = new FriendshipDTO();
            fDTO.setUser1(user1Dto);
            fDTO.setUser2(user2Dto);
            String requestBody = mapper.writeValueAsString(fDTO);
            System.out.println(requestBody);
            HttpManager.postJsonTo("/friendship/", requestBody);
        } catch (IOException | AuthenticationException e) {

        }
    }


    /**
     * Deletes an existing friendship defined by the two {@link barkeep.User}s.
     *
     * @param user1 the user in friendship
     * @throws SQLException exception in database query
     * @throws ClassNotFoundException the database driver was not found
     */
    public static boolean delete(int user1) {
        try {
            return HttpManager.deleteFrom("/friendship/removefriend/" + user1) == 200;
        } catch (IOException | AuthenticationException ignored) {
            return false;
        }
    }
}

