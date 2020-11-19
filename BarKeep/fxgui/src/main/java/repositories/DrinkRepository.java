package repositories;

import barkeep.Drink;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {

	/**
	 * Returns all the {@link Drink} rows from the database
	 * @return a list of all {@link Drink}
	 */
	public static List<Drink> getAll()  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(HttpManager.getFrom("/drink/all"), new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

	/**
	 * Returns a drink from the database defined by the {@link Drink} id
	 * @param id the row id
	 * @return the {@link Drink} with the given id, else null
	 */
	public static Drink get(int id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(HttpManager.getFrom("/drink/" + id), Drink.class);
        }catch (IOException e){
            return null;
        }
	}
}
