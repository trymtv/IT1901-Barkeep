package json;

import barkeep.Drink;
import barkeep.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {



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
