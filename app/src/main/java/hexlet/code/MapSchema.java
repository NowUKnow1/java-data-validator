package hexlet.code;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
    }

    public final MapSchema required() {
        addThings("required", input -> input instanceof Map);
        setRequired(true);
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<?> validationValue = input -> input instanceof Map
                && ((Map<?, ?>) input).size() == size;
        addThings("sizeof", validationValue);
        return this;
    }


    public final MapSchema shape(Map<String, BaseSchema> inputSchema) {
        addThings("shape", input -> input instanceof Map
                && inputSchema.entrySet().stream().allMatch(e ->
                e.getValue().isValid(((Map<?, ?>) input).get(e.getKey()))));
        return this;
    }
}
