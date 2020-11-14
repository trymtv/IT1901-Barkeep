package database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendRepositoryTest {

    @BeforeAll
    public static void setupDb(){
        Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
    }

    @Test
    public void testGetFriendList() throws SQLException, ClassNotFoundException {
        List<Integer> testList = new ArrayList<>(Arrays.asList(4, 3, 2));
        List<Integer> getIdList = FriendRepository.get(1);
        assertNotNull(getIdList);
        List<Integer> getUsernameList = FriendRepository.get("per");
        assertNotNull(getUsernameList);
        assertEquals(new HashSet<>(testList), new HashSet<>(getIdList));
        assertEquals(new HashSet<>(testList), new HashSet<>(getUsernameList));

        assertNull(FriendRepository.get("noesomikkefinnes"));
        assertNull(FriendRepository.get(-1));
    }

    @Test
    public void testDeleteAndInsert() throws SQLException, ClassNotFoundException {
        FriendRepository.delete(5, 2);
        FriendRepository.store(5, 2);
        List<Integer> testList = new ArrayList<>();
        testList.add(5);
        testList.add(1);
        List<Integer> getList = FriendRepository.get(2);
        assertNotNull(getList);
        assertEquals(new HashSet<>(testList), new HashSet<>(getList));
    }

}
