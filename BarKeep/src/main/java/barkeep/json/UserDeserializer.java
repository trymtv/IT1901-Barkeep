package barkeep.json;

import barkeep.Drink;
import barkeep.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User>  {



    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    User deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            User user = new User();
            JsonNode nameNode = objectNode.get("name");
            if (textNode instanceof TextNode) {
                user.setName(((TextNode) nameNode).asText());
            }
            JsonNode idNode = objectNode.get("id");
            if (idNode instanceof IntNode) {
                user.setId(((IntNode) idNode).asInt());
            }
            return drink;
        }
        return null;
    }
}

}
