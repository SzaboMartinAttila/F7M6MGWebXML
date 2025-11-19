StudentsQuery - how to run

This is a tiny Java helper to run XPath queries against the provided `studentF7M6MG.xml`.

Prerequisites
- Java 8+ installed and on PATH.

Compile
- Open a terminal in `c:\Users\marto\Desktop\XML\11.12`

```
javac StudentsQuery.java
```

Run examples

```
# list all students
java StudentsQuery studentF7M6MG.xml listAll

# get student by id (01)
java StudentsQuery studentF7M6MG.xml getById 01

# students older than 21
java StudentsQuery studentF7M6MG.xml olderThan 21

# count students
java StudentsQuery studentF7M6MG.xml count

# list nicknames
java StudentsQuery studentF7M6MG.xml nicknames

# average age
java StudentsQuery studentF7M6MG.xml avgAge
```

If you want me to implement specific queries from exercise 1c, paste the text of 1c or give permission to infer the required queries and I'll adjust the Java program accordingly.