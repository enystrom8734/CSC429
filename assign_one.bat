javac -d classes -classpath classes;. model\*.java
javac -classpath classes;. TestAssign1.java

echo "Classes for assignment one compiled"
pause

java -cp mysql-connector-java-5.1.7-bin.jar;classes;. TestAssign1