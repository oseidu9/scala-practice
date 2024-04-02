package com.rockthejvm

object ContextualAbstractions {

  //Scala3 has some contextual functionality. (functionality that changes depending on data given)
  //emphasis on the SCALA3!!!!. not every scala version has these functionalities.
  //since I cant get scala3 working in intellij, the scala3 specific code will be commented out.
  /***
   *1 - Context Parameters/Arguments:
   *  - Orderings
   *  - Combinators
   */
  val aList = List(2,1,3,4)
  val anOrderedList = aList.sorted //default sorting method is ascending.

  //scala has these things called orders, and these can be used to override order functionality.
  //E.g. in the above sorted method. default is low to high. we can create a new ordering to order in a different way.

  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // checking if given input (a,b), a > b
  //given keyword allows for the descendingOrdering to override the sorting for any Int based list. (anOrderedList)


  //Scala also has COMBINATORS.
  //These are traits (interfaces) which can be implemented in a generic manner to take in 2 or more arguments for one of its methods, and perform an operation combining both of them.
  //AS LONG AS there is an implementation of this combinator that matches the data type of the values being used

  trait Combinator[A] {
    def combine(x: A, y: A): A
  }

    def combineAll[A](list: List[A])(using combinator: Combinator:[A]): A =
    list.reduce((a,b) => combinator.combine(a,b))

  //given keyword allows for this combinator implementation to be used if combinator method is called on an int list.
  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int) = x + y
  }

  val theSum = combineAll(aList)


  //shorthanding the combineAll code: ??? replaced with the list.reduce combinator.combine line in the OG combineAll method.
  def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAll_v3[A : Combinator](list: List[A]): A = ???

  /*
  * Context args are useful in the following:
  * type classes
  * dependency injection
  * context-dependent functionality
  * type-level programming
  * */

  /*Scala also has this thing known as extension methods. it basically
   allows for you to use case classes to blueprint an entity, but then run its method using
   a data type arg from the class as a prefix to the method call?
   so basically you skip the step of instance creation to method call, and you
   can just perform the method call on the data type which would be passed to the classes
   constructor upon creation*/

  case class Person(name: String) {
    def greet(): String = s"Hi, I'm $name, I love scala!"
  }

  extension (string: String)
    def greet(): String = new Person(string).greet()

  val danielsGreeting = "Daniel".greet()


//example of extension use on the previous combinator
  extension[A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
    list.reduce(combinator.combine)

    val theSum_v2 = asList.combineAllValues
  def main(args: Array[String]): Unit = {
    println(anOrderedList)
    println(theSum)
  }
}
