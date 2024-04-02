package com.rockthejvm

object FunctionalProgramming extends App{
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  //same as
  bob(43) //INVOKING bob as a function. same as bob.apply(43)

  /*
  Scala can be used as a Functional Programming Language. Meaning we work with functions like we do any other value
  - compose functions
  - pass functions as args
  - return functions as results.

  This is acheived using the FunctionX feature [Function1, Function2, ... Function22]
  the number at the end basically means that is the number of arguments passed to the function
  */
                                       //[arg1, returnType]
  val simpleIncrementer = new Function1[Int, Int] {
    //function that takes an int and returns an int.
    //i think the structure is Function = interface [trait] with an apply method in it, and we override the apply method, effectively creating a class invokable function
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // return
  //same as
  simpleIncrementer(23)
  //we have basically defined a function and assigned it to a variable

                                      //[arg1, arg2, returnType]
val stringConcatenator = new Function2[String, String, String] {
  override def apply(arg1: String, arg2: String): String = arg1 + arg2
}

  stringConcatenator("I love", "Scala")

  //defining a function can be written in short hand (in case i see it in code and im confused)
  val doubler: Function1[Int, Int] = (x: Int) => 2 * x

  doubler(4) //returns 8

  //you can also short hand the Function1 arg and return type
  val doublerShorter: Int => Int = (x: Int) => 2 * x

  //you can make it even shorter since scala can infer arg type and return type using function definition.
  val doublerEvenShorter = (x: Int) => 2 * x


  //Functional Progamming aims to take functions as args.
  //Higher-order functions (HOF): Functions that take other functions as args/Returns functions as results.

  val aMappedList = List(1,2,3).map(x => x + 1) //map is a special function in List class, takes function as an arg. (Example of an HOF)
  println(aMappedList) //the function in the map argument is applied to every element in the list.

  //another example of higher order function is flat map.
  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2*x)) //for each element x, create a list containting element x, and 2*x
  //flatmap combines each element of the function (the individual lists) into one big list, NOT a list of lists though, each element is just an individual value.
  //E.g. output will be (1,2,2,4,3,6)

  val alternateFlatMappedList = List(1,2,3).flatMap { x => //same as the aFlatMappedList, but curly brace instead of normal
    List(x, 2 * x)
  }

  //another HOF is aFilteredList, filtering items out of list using some condition.
  val aFilteredList = List(1,2,3,4,5).filter(x => x <= 3)  //filter list for elements <= 3

  val alternateFilteredList = List(1,2,3,4,5).filter(_ <= 3) //same as line above.

  //implementing the function on the spot to be applied to the list is an anonymous method, since the method is being applied on the spot.

  //you can nest list based functions
  //below is an example of this. we will return all pairs between the numbers 1,2,3 and the letters a,b,c

  val allPairs = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  //for each element in the list 1, 2, 3, go through each element of the list 'a', 'b', 'c', and concatenate the number and letter
  //and the flatmap takes each individual element and concatenates them into one long list.
  //without flatmap, there would be 3 individual lists, one for each number being applied to the 'a', 'b', 'c' list.

  //the allPairs val can be acheived using this thing called for comprehensions (NOT for loops, this is something else)
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  //for each number in the list 1, 2, 3, and for each letter in the list 'a', 'b', 'c' (basically for every combination) return number letter concat string

  /**
   * Scala has different kinds of Collections
   */

  //LISTS
  val aList = List(1,2,3,4,5) //lists have head (first element) and tail (rest of the list without head)
  val first = aList.head
  val rest = aList.tail

  //elements can be added to start of list
  val aPrependedList = 0 :: aList //adding 0 to beginning of the aList
  //note that :: can be replaced with +:

  //elements can be added to end of list as well
  val anAppendedList = aList :+ 6 //adds 6 to end of list.

  //you can append and prepend at the same time
  val anExtendedList = 0 +: aList :+ 6

  //SEQUENCES
  val aSequence: Seq[Int] = Seq(1,2,3)

  //sequences allow you to access elements at given index.
  val accessedElement = aSequence(1) //returns the number at this index 1. (so will return 2)

  //VECTORS (a type of sequence, but it has very fast access time. has the same methods as lists and sequences.)
  val aVector = Vector(1,2,3,4,5)

  //SETS (no duplicates), mainly used to check if specific element is within the set
  val aSet = Set(1,2,3,4,1,2,3) //will output 1,2,3,4
  val setHas5 = aSet.contains(5) //boolean. returns false if not, true if it does.

  //you can add elements to set in different way to adding element to lists
  val setAddition = aSet + 5 //1,2,3,4,5
  //you can also remove elements from a set
  val setSubtraction = aSet - 5 //1,2,3,4 (the 5 is removed)

  //RANGES (a collection that holds all ints? between two given ints)
  val aRange = 1 to 1000

  //operations can be applied to each element in the range, and the output can be converted to a specific colleciton type.
  val rangeTimesTwo = aRange.map(x => 2*x).toList // List(2, 4, 6, 8 ... 2000)

  //TUPLES (collection that can hold multiple data types at once)
  val aTuple = ("Bon Jovi", "Rock", 1982)

  //maps (key value pairs)
  val aMap: Map[String, Int] = Map(
    ("Daniel", 450934),
    "Jane" -> 327285 //both of these are valid ways of adding key value pairs to a map.
  )

}
