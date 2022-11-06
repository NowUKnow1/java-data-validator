package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
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
}
