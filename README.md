# MobileStore
-> Rather than explaining about this simple project I chose to elaborate the things that I learned and implemented in this project so that it might be helpful for me or others to refer and understand these well! 
   However I might write about this project also after I complete it on the go...

# Liquibase
* To work with Liquibase, we need to add a dependency in the pom.xml file
* In the resources folder create another folder to store all the liquibase changelogs.
* We create a file changelog-master.xml in this new folder which basically acts as a log file which includes all change sets to define the database schema.
* We create induvidual change set files and perform various actions on the database like Creating a Table, Inserting new Rows, Adding costraints/columns etc.,
* Simply with the help of Liquibase, we don't need to use the Database application anymore. We can perform all the operations from our Spring Boot application with the help of Change sets.
* Liquibase is useful especially when working as a _team_ on a single project. It helps to avoid the corruption of data in the Database.

* The flow here is simple :- We write each operation in a change set file and include all these changesets in Changelog file.
                             Now we add this Changelog file in the _application.properties_ file.
                             When we run the Spring Application, it checks the application.properties -> changelog-master.xml -> [changesets.xml].
  
 **Basic points to consider while working with Liquibase :-**
* Once a changeset is exceuted and change is made in the Database, even if we run the Spring Boot Application again, that Changeset won't be executed.
* Once we create and execute a Changeset file, liquibase won't allow us to make any changes in that _Changeset_ because it creates a unique hashed value and after we execute a _Changeset_ and that value shouldn't be changed at all.

  **Rollback with Liquibase**
  * With the help of liquibase we can even rollback some operations performed.
  * To perform a rollback we first need to add a <rollback>--Code--</rollback> tag in the changeset and we can also assign an **tag** to these rollbacks which can be used further.
  * Another step is to add _liquibase.properties_ file in resources folder.
  * And we're almost done... Open the terminal and run few **mvn** commands and we can specify the rollbacks to be performed using **Count** or **Date** or **Tag** that we specified during creation.
  
**Example Commands** :
-> mvn liquibase:update
-> mvn liquibase:rollback -Dliquibase.rollbackTag=1.0 (Using tag)
-> mvn liquibase:rollback -Dliquibase.rollbackCount=1 (Using count)
-> mvn liquibase:rollback "-Dliquibase.rollbackDate=Jun 03, 2017" (Using date) 
  
#
# Exception Handling
-**Custom Excpetions**
We create a custom Excpetion class in a separate package(recommended) and extend it with RunTimeException because we need to handle only unchecked exceptions anyways checked excpetion will be prompted during compilation. All unchecked exceptions fall under Runtime Exceptions.
   * We create 2 variables errorCode and errorMessage for this Exception Class and call it in the Service files in which we perform basic CRUD operations.
   * In the methods in service layer, we use try and catch to handle the scenarios and throw the appropriate error code and message using the Custom Exception class.
   * We cannot use this Custom Exception class directly in the controller to throw. Bcoz we call the methods from service into the Controller but when it encounters an exception, it will return 500 Internal Server Error. To solve this ...
   * We then create a Controller Exception class and use try catch block to handle the custom exception using Controller exception. Now we get the output directly from the Controller Exception and the message, code will also come from the          Controller Exception class.
-But the porblem with custom exceptions is that we should handle first handle scenarios using try & catch in the main methods i.e. in Service layer, and then we should handle that Custom Exception with try & catch using the Controller Exception in the Controller/ Rest Apis file.
-To avoid this, we can use something called Global Exceptions in which we only need to create the Custom Exception and handle it more simply.

**Global Exceptions**
When we use Global Excpetion we don't need the Controller Exception anymore to handle the Custom Exceptions, we can make use of the @ControllerAdvice and @ExceptionalHandler.
   * We create a new package called "advice"(recommeded) and in the we create a class which extends _ResponseEntityExceptionHandler_ and annotate this class with _@ControllerAdvice_.
   * In this class we create a new method to handle custom exceptions and pass the instance of custom exep to this method as argument.
   * We annotate this method with _@ExceptionalHandler_ and give CustomException.class as argument.
   * Now what happens is that since we used @ControllerAdvice and @ExceptionalHandler, whenever the program encounters the custom exception before throwing it in the console, the @ExceptionalHandler 
     handles it on behalf of us and gives the message that we specify instead of returning 500 Internal Server Error.

