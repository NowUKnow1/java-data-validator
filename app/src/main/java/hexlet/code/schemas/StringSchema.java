package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema() {
    }

    public final StringSchema required() {
        addThings("required", input -> input instanceof String
                && !((String) input).isEmpty());
        setRequired(true);
        return this;
    }

    public final StringSchema contains(String substring) {
        Predicate<?> validationValue = input -> input instanceof String
                && ((String) input).contains(substring);
        addThings("contains", validationValue);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<?> validationValue = input ->  input instanceof String
                && ((String) input).length() >= minLength;
        addThings("minLength", validationValue);
        return this;
    }
}
