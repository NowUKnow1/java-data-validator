### Hexlet tests and linter status:
[![Actions Status](https://github.com/NowUKnow1/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/NowUKnow1/java-project-78/actions)
[![Java CI](https://github.com/NowUKnow1/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/NowUKnow1/java-project-78/actions/workflows/main.yml)
<a href="https://codeclimate.com/github/NowUKnow1/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/7aaf6fde980ce2e047ee/maintainability" /></a>
<a href="https://codeclimate.com/github/NowUKnow1/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/7aaf6fde980ce2e047ee/test_coverage" /></a>

# The program validates data according to criteria

**Valid data types: Integer, String, Map**

**String example:**
```
Validator v = new Validator();

StringSchema schema = v.string();
schema.isValid(""); // true
schema.isValid(null); // true
(schema.isValid(5); // false

schema.required();
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.minLength(2).isValid("hexlet"); //true
schema.minLength(10).isValid("Harry"); //false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

or complex:

Validator v = new Validator();

StringSchema userID = v.string();

userID.required().minLength(5).contains("id=");
userID.isValid("id="); //false
userID.isValid("123456"); //false
userID.isValid(null); //false
userID.isValid("id=123"); //true
```

**Integer example:**

```
Validator v = new Validator();

NumberSchema schema = v.number();
schema.isValid(null); //true

schema.positive();
schema.isValid(null);
schema.isValid(5);
schema.isValid(-5);
schema.isValid("5"); // false

schema.required();
schema.isValid(null); // false
schema.isValid(10); // true
schema.isValid("5"); // false
schema.isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);
schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
schema.isValid(null); //false

or complex:

Validator v1 = new Validator();
NumberSchema workingAge = v1.number();

workingAge.required().positive().range(18, 65);
workingAge.isValid(null); //false
workingAge.isValid(4); //false
workingAge.isValid(18); //true
workingAge.isValid(45); //woman is berry again
workingAge.isValid(65); //true
```

**Map example:**

```
Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();
schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true
Map data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);
schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

or complex:

// shape - allows you to describe validation for Map object values by keys.
Validator v = new Validator();
MapSchema schema = v.map(); 
Map checkSchema = Map.of(
    "name", v.string().required().minLength(4).contains(" "),
    "age", v.number().required().positive().range(18, 55),
    "id", v.number().positive().range(11111111, 888888888));


Map user1 = Map.of(
    "name", "Harry Potter",
    "age", 22,
    "id", 88888888);
schema.required().sizeof(3).shape(checkSchema).isValid(user1); /true

Map user2 = Map.of(
    "name", "Dumbledore",
    "age", 55,
    "id", 11111111);
schema.isValid(user2); /false

Map user3 = Map.of( 
    "name", "Dobby", 
    "age", 116,
    "id", 88888889);
schema.isValid(user3); /false

Map user4 = Map.of(
    "name", "Hermione O.P. Power",
    "age", 21,
    "id", 12345678);
schema.isValid(user4); /true
```
