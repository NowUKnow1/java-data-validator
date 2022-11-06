package hexlet.code;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
    }

    public final MapSchema required() {
        addThingsToValidate("required", input -> input instanceof Map);
        setRequired(true);
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<?> validation = input -> input instanceof Map
                && ((Map<?, ?>) input).size() == size;
        addThingsToValidate("sizeof", validation);
        return this;
    }
}
