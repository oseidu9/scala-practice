package com.rockthejvm

object PatternMatching extends App {

  //Scala has switch statements in the form of something called Pattern Matching.
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  //order is a switch statement applied on the anInteger val.
  //case _ is default case.
  //pattern matching is also an expression, which is why we can assign it to a value.


  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43) //same as Person.apply("Bob", 43)

  //Pattern Matching can also be used to take in an instance of a class, and create an output based on the instance's properties.
  //as long as the instance matches the structure provided in the pattern matching's case.
  val personGreeting = bob match {
    case Person(n, a) => s"Hi, i'm $n and i'm $a years old"
    case _ => "Something else"
    //n represents the name, and a represents the age.
  }

  println(personGreeting)

  //NOTE!!! Pattern Matching on class instances is only available for case classes.


  //pattern matching can also take in a tuple, and create an output based on the tuples elements.
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case(band, genre) => s"$band belongs to the genre $genre"
    case _ => "dunno what youre talking about"
  }
  //if the tuple matches the structure of having two elements (which will be refered to as band and genre)
  // perform the operation on the right side of the case.
  //else default case.

  //Pattern matching can also be take in lists and create output based on the lists elements.
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "list contains 2 at second position"
    case _ => "unknown list"
  }
  //generally speaking, _ is a placeholder that represents anything. so in first case, check if 2 is in middle of list. position 1 and 3 dont matter.

  //if Pattern Matcher doesnt match anything, a MatchError will be thrown.
  //Pattern Matching will test cases in case order.
}
