A guide to how to use the code on your computer:

step 1:
Download the project to your computer ( including pom.xml file )

step 2:
Install MySQL ( tutorial - https://www.youtube.com/watch?v=OM4aZJW_Ojs&t=335s&ab_channel=AmitThinks )

step 3: 
Add JDBC driver, If you installed MySQL, open the MySQL folder and find "Connector J" folder.
![image](https://user-images.githubusercontent.com/102803654/193763619-66d0482e-ea90-4fa5-93f8-86d41b51505a.png)
Inside this folder you will find "mysql-connector-java.jar" file. You will need this file.
![image](https://user-images.githubusercontent.com/102803654/193763748-abf6242d-25f2-4746-9bf7-6aeeb79cc0d3.png)

step 4:
Open intellij, open "file > project structure " 
![image](https://user-images.githubusercontent.com/102803654/193764142-789da68f-71f7-4e05-a076-4313a4e8d997.png)
select "Modules" and Dependency and press on "+" as shown on the image below.
![Untitled](https://user-images.githubusercontent.com/102803654/193765013-af2580f1-06f6-43b0-a630-46e80c620151.png)
After you press "+", select "Jars or Directoris"
![image](https://user-images.githubusercontent.com/102803654/193765284-e1b8ff66-3906-4245-9adc-7d31d60739b6.png)
Find your Connector jar file , select it and press add button
![image](https://user-images.githubusercontent.com/102803654/193765487-6da74616-0e06-42c5-8847-c13c28c8ba72.png)

step 5:
open "datasources.properties" file that is inside resources folder and replace my username password and url with 
your database username password and url.
