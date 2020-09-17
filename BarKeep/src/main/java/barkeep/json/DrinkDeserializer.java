package barkeep.json;

import barkeep.Drink;

import java.io.IOException;

public class DrinkDeserializer extends JsonDeserializer<Drink>  {



    @Override
    public Drink deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Drink deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Drink drink = new Drink();
            JsonNode nameNode = objectNode.get("name");
            if (textNode instanceof TextNode) {
                drink.setName(((TextNode) nameNode).asText());
            }
            JsonNode idNode = objectNode.get("id");
            if (idNode instanceof IntNode) {
                drink.setId(((IntNode) idNode).asInt());
            }
            JsonNode valueNode = objectNode.get("value");
            if (valueNode instanceof DoubleNode) {
                drink.setValue(((DoubleNode) idNode).asDouble());
            }
            return drink;
        }
        return null;
    }
}

}
