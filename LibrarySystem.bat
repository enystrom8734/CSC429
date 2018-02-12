javac -d classes -cp classes -classpath classes;. src\main\common\*.java
javac -d classes -cp classes -classpath classes;. src\main\database\*.java
javac -d classes -cp classes -classpath classes;. src\main\event\*.java
javac -d classes -cp classes -classpath classes;. src\main\exception\*.java
javac -d classes -cp classes -classpath classes;. src\main\impresario\*.java
javac -d classes -cp classes -classpath classes;. src\main\model\*.java
javac -d classes -cp classes -classpath classes;. src\main\userinterface\*.java
javac -d classes -cp classes -classpath classes;. src\main\LibrarySystem.java
REM javac -d classes -classpath classes;. src\main\TestAssign1.java
rem javac -d classes -classpath classes;. src\main\LibrarySystem.java

echo "Classes for assignment one compiled"
pause

java -cp mysql-connector-java-5.1.7-bin.jar;classes;. LibrarySystem


