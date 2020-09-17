package json;

import barkeep.Drink;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class DrinkDeserializer extends JsonDeserializer<Drink> {



    @Override
    public Drink deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Drink deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Drink drink = new Drink("", 0);
            JsonNode nameNode = objectNode.get("name");
            if (nameNode instanceof TextNode) {
                drink.setName(((TextNode) nameNode).asText());
            }
            JsonNode valueNode = objectNode.get("value");
            if (valueNode instanceof DoubleNode) {
                drink.setValue(((DoubleNode) valueNode).asDouble());
            }
            return drink;
        }
        return null;
    }
}