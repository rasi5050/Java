dependencies to create jar
/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar
/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar
/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar
/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar



create class files
javac -cp .:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar -d . *.java

create jar
jar cfm ContactManager.jar MANIFEST.MF com/rasi/contactmanagerv3/*.class


run jar along with dependencies
#below command doesnt work because when -cp and -jar is used, -jar overrides the -cp
# java -cp .:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar -jar ContactManager.jar view


## run program

sample commands: view
java -cp .:ContactManager.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar com.rasi.contactmanagerv3.Main view

sample commands: insert
java -cp .:ContactManager.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar com.rasi.contactmanagerv3.Main insert "{\"name\":\"John Doe\",\"emails\":[{\"email_type\":\"personal\",\"email\":\"john@example.com\"}],\"phones\":[{\"phone_type\":\"mobile\",\"phone\":\"1234567890\"}],\"addresses\":[{\"address_type\":\"home\",\"address_line1\":\"123 Main St\",\"address_line2\":\"\",\"city\":\"New York\",\"state\":\"NY\",\"zip\":10001,\"country\":\"USA\"}]}"


sample commands: update
java -cp .:ContactManager.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar com.rasi.contactmanagerv3.Main update 0 "{\"name\":\"John Doe\",\"emails\":[{\"email_type\":\"personal\",\"email\":\"john@example.com\"}],\"phones\":[{\"phone_type\":\"mobile\",\"phone\":\"1234567890\"}],\"addresses\":[{\"address_type\":\"home\",\"address_line1\":\"123 Main St\",\"address_line2\":\"\",\"city\":\"New York\",\"state\":\"NY\",\"zip\":12345,\"country\":\"USA\"}]}"

sample commands: delete
java -cp .:ContactManager.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/rasi/.m2/repository/org/xerial/sqlite-jdbc/3.41.2.2/sqlite-jdbc-3.41.2.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/rasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar com.rasi.contactmanagerv3.Main delete 1


