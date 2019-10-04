# Code Review / Refactoring exercise
Please review the following code snippet. Assume that all referenced assemblies have been properly included. 

The code is used to log different messages throughout an application. We want the ability to be able to log to a text file, the console and/or the database. Messages can be marked as message, warning or error. We also want the ability to selectively be able to choose what gets logged, such as to be able to log only errors or only errors and warnings. 
1. If you were to review the following code, what feedback would you give? Please be specific and indicate any errors that would occur as well as other best practices and code refactoring that should be done. 
2. Rewrite the code based on the feedback you provided in question 1. Please include unit tests on your code.

## Feedback 

1. There are a lot of static variables and those variables are getting initializing by constructor and it is not a good practice if we want to use OOP and encapsulation, the static variables are used to share a long the application and we should keep the internal variables as hidden to use only by the application and no share them as in this case, outside of application those could be manipulated and changed the application will behavior inconsistent.

2. Names of static variables are confused and those don't show the intention what  all of them have been created, it's not clear, for example logToFile should be something like "isEnabletoLogToFile" because if intention is to log message to file is better that variable express the intention correctly.

3. There are some variables not initialized, it's not clear what it's its purpose on the application.

4. There is a method call LogMessage and the name is bad it shouldn't start with UpperCase.

5. The validations should be made by the message itself.

6. Method is receiving several parameters and some of them are Boolean that means that the application will have more responsibility because Boolean has two possible values true or false and there are 3 parameters as boolean type that means the method needs to make 6 validations or checking and the good practice is that the method only should have one responsibility no more than one.

7  Method is not following the single responsibility principle because it's running several tasks that belongs to  logging on file, logging on console and save on database that means that method is doing more than one responsibility and getting tight coupled.

8. Flags are used by the application in several times and that's hard to maintain the application or make some changes without break it. It's better avoid the flags.

9. The method shouldn't received more than 2 params in that case we can create a Request Class and wrapper the values to avoid pass more than 2.

10. There are logic repeated and that is a common problem for boolean type flags.

11. There's bad initializing in line "String l = null" avoid to use String to concat some message instead it's better to use StringBuilder to keep the same instance and appending the message.

12. Database configurations is placed in the same method, database configurations must be in other class as utility or source, same for files utils.


## Refactoring

1. The code was changed because the application intention is to log messages given a severity selected as WARNING or ERROR and logging in several systems as database, files, console etc.

2. This problem was resolved using a chain of responsibility because it has several advantages and it's suit for the exercise.

3. We can create separate components working apart of each other in this case we remove the dependency between them and we can create more components
with affect the others increasing the scope, decouple the components.

4. Message request can be passed along the chain and this one could be modified by the chain or simple used as is required.

5. We can join all together components and use them in chain of executions. 

6. It is easy to test each component by separated way.

7. Using chain of responsability we can remove if-else approach and repeated logic.







