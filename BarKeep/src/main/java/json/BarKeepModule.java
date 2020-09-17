package json;

import barkeep.Drink;
import barkeep.User;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class BarKeepModule extends SimpleModule {
    private static final String NAME = "BarKeepModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    public BarKeepModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Drink.class, new DrinkSerializer());
        addSerializer(User.class, new UserSerializer());
        addDeserializer(Drink.class, new DrinkDeserializer());
        addDeserializer(User.class, new UserDeserializer());
    }
}
