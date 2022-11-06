package hexlet.code;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema() {
    }

    public final StringSchema required() {
        addThingsToValidate("required", input -> input instanceof String
                && !((String) input).isEmpty());
        setRequired(true);
        return this;
    }

    public final StringSchema contains(String sbstr) {
        Predicate<?> validation = input -> input instanceof String
                && ((String) input).contains(sbstr);
        addThingsToValidate("contains", validation);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<?> validation = input ->  input instanceof String
                && ((String) input).length() >= minLength;
        addThingsToValidate("minLength", validation);
        return this;
    }
}
