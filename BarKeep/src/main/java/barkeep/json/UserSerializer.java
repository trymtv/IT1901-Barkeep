package barkeep.json;

import barkeep.Drink;
import barkeep.User;


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
