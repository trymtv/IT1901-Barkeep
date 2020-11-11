package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

	@BeforeAll
	public static void testDbSetup(){
		Database.setDbUrl("jdbc:h2:./src/test/resources/testdb");
	}
	@BeforeEach
	public void openDb() throws SQLException, ClassNotFoundException {
		Database.open();
	}
	@AfterEach
	public void closeDb() throws SQLException {
		Database.close();
	}
	@Test
	public void testDatabaseRead() throws SQLException {
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE ID=0");
		while(rs.next()){
			assertEquals(0, rs.getInt("id"));
			assertEquals("per", rs.getString("username"));
			assertEquals("passord123", rs.getString("password"));
			assertEquals("per@gmail.com", rs.getString("email"));
		}
	}

	@Test
	public void testDatabaseInsert() throws SQLException {
		Database.delete("USERS", "username='mrInsert'");
		Database.insert("USERS", "NULL", "mrInsert", "_xXSecretXx_", "olav@insert.com");
		ResultSet rs = Database.read("SELECT * FROM USERS WHERE username='mrInsert'");
		assertTrue(rs.next());
	}

	@Test
	public void testDatabaseInsertNumberParser() throws SQLException {
		Database.delete("NUMBERS", "NUMBER=20.21");
		Database.insert("NUMBERS", "NULL", "20.21");
	}
}
