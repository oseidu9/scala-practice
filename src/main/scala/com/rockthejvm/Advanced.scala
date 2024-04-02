package com.rockthejvm

import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App{

  //scala contains lazy vals (dont have their values evaluated until they are used)
  lazy val lazyValWithSideEffect = {
    println("Im so lazy")
    43
  } //if this value was not "lazy", it will be called upon creation, so printing and assigning the 43

  //run code above with nothing else, nothing is printed.

  val usingLazyVal = lazyValWithSideEffect + 1 //lazy val is now used, so statement will print + 43 number assign
//lazy vals useful for large collections

    //Scala also has "pseudo collections". Option and Try, each used differently.
    //Pseudocollections can also have pattern matching applied to them to check each case applicable to these pseudo collections

  //OPTION: collection which contains at most one element (used to check if method returns null)
  def methodWhichCanReturnNull(): String = "hello, Scala"
  val anOption = Option(methodWhichCanReturnNull())

  val stringOptionProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
    //anOption can either have case Some(expected) or None, meaning null was returned.
  }

  //TRY: collection which will hold a value if the code went well, or an exception if the code went bad and threw one
  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())

  val stringTryProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }

  /***
   * Scala allows for asynchronous programming (different code blocks to run at same time)
   *
   * Code blocks can be separated from the main code and run independently using somethiing called Futures.
   *
   * they begin running at the line they are created on
   */

  val aFuture = Future { //future begins to run on this line, and main code continues running beyond this code block simultaneously.
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }
  //if the main block of code ends here. the code after Thread.sleep in the future block WONT RUN
  // this is because the main block of code will have finished running before the sleep is over, and application will close.

  Thread.sleep(2000) //main block of code sleeps, giving future enough time to run its whole code block.

  /***
   * Scala also has a thing called Implicits.
   * implicits allow for scala code to infer what to do based on the "implicit" pieces of data at hand.
   */

  //1# implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs) //aMethodWithImplicitArgs(myImplicitInt)
  //because there is only one implicit integer in the code, scala knows we want to use myImplicitInt when calling aMethodWithImplicitArgs

  //2# implicit class method calls
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  println(23.isEven()) //new myRichInteger(23).isEven()
  //implicit class allows for you to take a value of the same type as the implicit type's init argument, and call a method on that value
  //essentially it skips the steps of class creation using 23 as an arg to call isEven() on 23, and instead allows you to call isEven on 23 directly.

  //be careful with implicits as there may be no values to imply to be used. E.g. no implicit Int value when calling a method with an implicit int arg.
}
