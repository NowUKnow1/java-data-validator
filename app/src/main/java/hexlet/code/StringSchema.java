package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    public StringSchema() {
    }

    private final Map<String, Predicate> thingsToValidate = new LinkedHashMap<>();
    private boolean required = false;

    public final void setRequired(boolean requirement) {
        this.required = requirement;
    }

    public final boolean isRequired() {
        return required;
    }

    public final void addThingsToValidate(String valueForCheck, Predicate valueWhereCheck) {
        thingsToValidate.put(valueForCheck, valueWhereCheck);
    }

    public final boolean isValid(Object input) {
        return thingsToValidate.values().stream().allMatch(thing -> thing.test(input));
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
