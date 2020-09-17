package json;

import barkeep.Drink;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class DrinkSerializer extends JsonSerializer<Drink> {

    @Override
    public void serialize(Drink drink,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) {
        jGen.writeStartObject();
        jGen.writeStringField("drink", drink.getName());
        jGen.writeStringField("id", drink.getId().);
        jGen.writeStringField("value", drink.getValue());
        jGen.writeEndObject();
    }
}
