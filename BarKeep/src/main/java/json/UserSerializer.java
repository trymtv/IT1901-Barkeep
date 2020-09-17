package json;

import barkeep.Drink;
import barkeep.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) {
        jGen.writeStartObject();
        jGen.writeStringField("drink", user.getName());
        jGen.writeStringField("id", user.getId());
        jGen.writeEndObject();
    }
}
