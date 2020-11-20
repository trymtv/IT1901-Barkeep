package repositories;

import barkeep.FriendshipDto;
import barkeep.User;
import barkeep.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.auth.AuthenticationException;

public class FriendRepository {

  /**
   * Gets a list of IDs of friends from the given {@link barkeep.User}-id.
   *
   * @param userid the given userid
   * @return List of IDs from the {@link barkeep.User}'s friends
   * @throws SQLException           exception in database query
   * @throws ClassNotFoundException the database driver was not found
   */
  public static List<User> get(int userid) {
    ObjectMapper mapper = new ObjectMapper();
    List<User> users = null;
    try {
      users = mapper.readValue(HttpManager.getFrom("/friendship/" + userid), new TypeReference<>() {
      });
    } catch (IOException ignored) { 
      return null;
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
   * @throws SQLException           exception in database query
   * @throws ClassNotFoundException the database driver was not found
   */
  public static List<User> get(String username) {
    return get(UserRepository.get(username).getId());
  }


  /**
   * Stores a new friendship defined by the two {@link barkeep.User}s.
   *
   * @param user1 the first user
   * @param user2 the second user
   * @throws SQLException           exception in database query
   * @throws ClassNotFoundException the database driver was not found
   */
  public static boolean store(int user1, int user2) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      UserDto user1dto = new UserDto(UserRepository.get(user1));
      UserDto user2dto = new UserDto(UserRepository.get(user2));
      FriendshipDto fdto = new FriendshipDto();
      fdto.setUser1(user1dto);
      fdto.setUser2(user2dto);
      String requestBody = mapper.writeValueAsString(fdto);
      return HttpManager.postJsonTo("/friendship/", requestBody) == 200;
    } catch (IOException | AuthenticationException e) {
      return false;
    }
  }


  /**
   * Deletes an existing friendship defined by the two {@link barkeep.User}s.
   *
   * @param user1 the user in friendship
   * @throws SQLException           exception in database query
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

