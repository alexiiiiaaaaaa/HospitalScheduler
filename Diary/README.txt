1. Project Folder structure:

Diary
|
+--Doc\
|
+--META-INF\
|   |
|   +--MANIFEST.MF
|
|+--README.txt
|+--diaryItems.txt
|+--HealthProfesionals.txt
|+--ConsoleColors.java  
|+--DiaryItemOperatonLog.java  
|+--DiaryItem.java  
|+--DiaryItemsSet.java  
|+--FromToDateTime.java 
|+--Genio.java 
|+--HealthProfesionals.java 
|+--Main.java 
|+--Professional.java 
|+--Scheduler.java 
|+--Tester.java 
|+--Worklocations.java
|+-- *.class files 
|+--Scheduler_v.jar (Program JAR file) 

2. Jar File generation
to generate jar file following commands was used:
2.1 navigate to the project directory /Scheduler_v
2.2 compile java files: javac *.java
2.3 create jar file: jar cmvf META-INF/MANIFEST.MF Sheduler_v.jar *.class
2.4 to execute program: java -jar Scheduler_v.jar

3. Java Doc generation
I have used intellij javaDoc creation tool

3.1 navigate to the project directory /Diary
3.2 execute command to generate javadoc and store it in to Java_doc folder: 
    javadoc -d Java_doc *.java 
3.3 navigate to Java_doc folder and start index.html -> java documentation will open in the browser 