1. Project Folder structure:

BinaryTrees2616332 v0.3latest  
|
+--Doc\
|
+--META-INF\
|   |
|   +--MANIFEST.MF
|
|+--README.txt
|+--diaryItems.txt
|+--healthProfesionals.txt
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
|+--BinaryTree.jar (Program JAR file) 

2. Jar File generation
to generate jar file following commands was used:
2.1 navigate to the project directory /BinaryTrees2616332 v0.3latest
2.2 compile java files: javac *.java
2.3 create jar file: jar cmvf META-INF/MANIFEST.MF BinaryTree.jar *.class
2.4 to execute program: java -jar BinaryTree.jar

3. Java Doc generation
3.1 navigate to the project directory /BinaryTrees2616332 v0.3latest
3.2 execute command to generate javadoc and store it in to Doc folder: 
    javadoc -d Doc BinaryTree.java Main.java ShopItem.java TreeNode.java Tester.java
3.3 navigate to /Doc folder and start index.html -> java documentation will open in the browser 