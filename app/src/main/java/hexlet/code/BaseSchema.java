package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate> validatorMap = new LinkedHashMap<>();
    private boolean required = false;

    public final void setRequired(boolean requirement) {
        this.required = requirement;
    }

    public final boolean isRequired() {
        return required;
    }

    public final void addThings(String checkValue, Predicate validationValue) {
        validatorMap.put(checkValue, validationValue);
    }

    public final boolean isValid(Object input) {
        return validatorMap.values().stream().allMatch(thing -> thing.test(input));
    }
}
