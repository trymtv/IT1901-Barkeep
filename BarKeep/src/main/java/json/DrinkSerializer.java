package json;

import barkeep.Drink;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class DrinkSerializer extends JsonSerializer<Drink> {

    @Override
    public void serialize(Drink drink,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("drink", drink.getName());
        jGen.writeNumberField("id", drink.getId());
        jGen.writeNumberField("value", drink.getValue());
        jGen.writeEndObject();
    }
}
