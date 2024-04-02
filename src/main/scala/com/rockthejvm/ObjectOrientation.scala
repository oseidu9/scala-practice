package com.rockthejvm

object ObjectOrientation extends App {

  //class
  class Animal {
    //define class fields
    val age: Int = 0
    //define class methods
    def eat(): Unit = println("I'm eating")
  }

  //creating instance of class
  val anAnimal = new Animal

  //Inheritance (shares properties of parent class + own unique functionality)
  class Dog(val name: String) extends Animal //dog class inheriting from animal (but no class body for now)

  //instantiating dog instance
  val aDog = new Dog("Lassie")
  aDog.name //gets dogName

  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() //the eat method will be the most child method. (E.g. if dog has an eat override method, it will run dogs ver.)

  // abstract class (not all functions are defined, and can be defined in child classes)
  abstract class WalkingAnimal {
    val hasLegs = true //NOTE all scala methods and fields are public by default.
    protected val hasNoLegs = false //access modifiers can be applied. public = accessible anywhere. private = only accessible in this class. protected = accessible in this class and any descendants (extended into)
    def walk(): Unit //can be overridden by child class
  }

  //interfaces (nothing is defined. all must be implemented in child class)
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  //Scala allows for method names of many character types.
  trait Philosopher {
    def ?!(thought: String): Unit
  }

  //Scala has a feature where a class can extend from only one other class BUT inherit multiple interfaces.
  class Crocodile extends Animal with Carnivore with Philosopher {
    //override keyword used to override carnivore eat class FROM INTERFACE Carnivore, not the defined Animal class verison.
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")
    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  //creating Crocodile instance
  val aCroc = new Crocodile

  aCroc.eat(aDog)
  //NOTE that for single argument methods in a class instance, the method can be written in infix notation
  aCroc eat aDog //just in case i see it in code and am confused. (object method argument)

  //scala having lenient method naming and infix single arg method calls makes some method calls look like operation statements.
  aCroc ?! "what if we could fly"

  //anonymous classes (implementing interface (trait) on the spot)
  //note that anonymous methods exist as well (covered in functionalProgramming.scala)
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("Im a dino so i can eat anything")
  }

  //singleton object (basically an object you implement but that implementation is the only instance of it.)
  //so its like a class, but you directly make use of the class instead of creating an instance of the class then using the instance.
  object MySingleton {
    val mySpecialValue = 53278
    def mySpecialMethod(): Int = 5327

    //apply method is a special type of method that can be called by using the instance of a class (or object)as the function.
    def apply(x: Int): Int = x + 1
  }

  MySingleton.apply(65)
  //equivalent to MySingleton.apply(65)
  MySingleton(65) //the object is being used as the function, instead of calling the apply function from the instance.

  //in Scala, you can create a singleton object with the same name as an existing class
  object Animal { //class Animal and object Animal known as Companions.
    //Companions can access each others private fields/methods.
    //BUT singleton Animal and instances of Animal class are different things.
    //ALSO, if Animal class instances exist, object Animal is not used as an object, but instead as a holder of info that are important to the animal class instances.

    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely //kind of like static fields/methods in java.

  /*case classes : lightweight data structures that will have default methods implemented into them:
  - equals and hash code method for sorting in lists
  - serialization
  - companion object that contains an apply method. (i think the companion's apply meth will take instance of the class of same name as an arg?)
  -pattern matching (will be discussed later)
   */

  case class Person(name: String, age: Int)
  //Case classes can be constructed without using new keyword
  val bob = Person("Bob", 54)

  //exceptions
  try {
    //code that runs
    val x: String = null
  } catch { //if specific error is thrown by the try block code, run specific code
    case e: Exception => "some error message"
  } finally {
    //code will execute no matter what.
  }

  //generics (allows for any data type/class to be passed as argument)
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  } //T is placeholder type. type becomes concrete upon instance creation depending on arg's data type passed

  //creating lists using default list implementation in scala.
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head // int
  val last = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head //string

  //Scala uses immutable objects/values, so original object cant be changed. if operation is done on an object, output must be stored in a new object
  val reversedList = aList.reverse //returns new list
  //if we just did aList.reverse, aList will stay the same, and the operation output is not stored anywhere

}
