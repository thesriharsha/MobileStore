# MobileStore

# Exceptions
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
   * We create a new package called "advice"(recommeded) and in the we create a class which extends _ResponseEntityExceptionHandler_ and anootate this class with _@ControllerAdvice_.
   * In this class we create a new method to handle custom exceptions and pass the instance of custom exep to this method as argument.
   * We annotate this method with _@ExceptionalHandler_ and give CustomException.class as argument.
   * Now what happens is that since we used @ControllerAdvice and @ExceptionalHandler, whenever the program encounters the custom exception before throwing it in the console, the @ExceptionalHandler 
     handles it on behalf of us and gives the message that we specify instead of returning 500 Internal Server Error.


