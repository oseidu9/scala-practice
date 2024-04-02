package com.rockthejvm

//extends App in scala = object is independently runnable
//extends App basically means execute the object's body.
object Basics extends App {

  //defining value
  //val valName: valType = valValue
  val meaningOfLife: Int = 42 //note vals in scala are Constant, cant be changed.

  //dont need to always define type, scala will figure it out
  val aBoolean = false

  //typical types are Int, Boolean, Char, Double, Float, String
  val aString = "I love Scala"
  val aComposedString = "I" +  " " + "love" + " " + "Scala"

  //s"" allows for another string to be injected into some tring
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  //expressions (structures that can be reduced to a single value. Scala works in expressions)
  //E.g. in OOP, only some of the components are expressions. (e.g. mathematical calculation for a variable) but something like an if statement, its an instruction, not expression.
  //Scala works in expressions. E.g. in scala, if statements are written as expressions.
  val anExpression = 2 + 3

  //if-expression (reduces to 56 or 999 depending on value of meaningOfLife)
  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression = {
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

    //another expression type in scala is code blocks. just a block of code that reduces to a single value based on the code in the code block.
    //you can define functions, classes, inner code blocks etc. in code blocks.
    val aCodeBlock = {
      //val expression definitions.
      val aLocalValue = 67

      //value returned by code block = value of the last expression, so line below.
      aLocalValue + 3
    }

    //defining functions in scala
    //x is arg 1 and an int, y is arg 2 and a String.
    // return type is a string. and the function body is what comes after the =. this is what is done with the args.
    def myFunction(x: Int, y: String): String = {
      y + " " + x
    }

    //factorial functions in scala
    def factorial (n: Int): Int =
      if (n <= 1) 1
      else n * factorial(n-1)

    //in scala, loops and iteration are NOT used. instead, functional recursion is used.

    //also, "void" return type in scala is known as Unit. typically used when a function just needs to do stuff BUT not return anything
    println("I love Scala") //returns unit type becuase it only needs to print something, no return is needed.

  def myUnitReturn(): Unit = {
    println("I don't love returning Unit")
  }

    //note that the unit type is basically unit = ()
  }
}
