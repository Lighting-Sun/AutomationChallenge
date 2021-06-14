# AutomationChallenge

## Tutorial to run the project

### Prerequisites

Have a code editor or IDE installed such as Visual Studio Code or IntelliJ

### Java instalation & Configuration

#### Download & install java

Download java 8 JDK, you can find it here [Java 8 JDK](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html)

#### Configure Java

1.Go to Windows settings

2.Go to system and click on about

3.Click on Advanced system settings

4.Click on Environment Variables

5.In the System variables section click on New
  * Fill the Variable name field with JAVA_HOME
  * Fill the Variable value with the folder where java is installed this should be something similar to: C:\Program Files\Java\jdk1.8.0_281
  * Click on ok
  
6.In the System variables section look for a varibale called Path, click it and click on Edit
  * Click on new and write %JAVA_HOME%\bin
  * Click on OK

### Maven Configuration

1.Download the Maven binary zip archive from here [Maven](https://maven.apache.org/download.cgi)

2.Extract the file and paste it in C:\Program Files

4.Go to Windows settings

5.Go to system and click on about

6.Click on Advanced system settings

7.Click on Environment Variables

8.In the System variables section click on New
  * Fill the Variable name field with MVN_HOME
  * Fill the Variable value with the folder where java is installed this shoul be something simmilar to: C:\Program Files\apache-maven-3.6.3-bin\apache-maven-3.6.3
  * Click on OK
  
9.In the System variables section look for a varibale called Path, click it and click on Edit
  * Click on new and write %MVN_HOME%\bin
  * Click on OK 
 
### Downloading and Running the project

1.Click on the code button in this repository

2.Select the Download Zip option

3.Extract the .zip file with the Extract here option

4.Place the project folder on the desired location

5.Open it as an IntelliJ Project or with visual studio code

6.Once the project has loaded, go to the terminal and type: mvn test
  * With this all the seven tests should run
