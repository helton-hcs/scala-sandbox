package generics

class Dog
class Puppy extends Dog

trait PutBox[-A] {
  def put(value: A): Unit = ???
}

trait GetBox[+A] {
  def get: A = ???
}

trait PutGetBox[A] extends PutBox[A] with GetBox[A]

object Boxes {
  def putPuppy(box: PutBox[Puppy]): Unit = box.put(new Puppy)
  def getDog(box: GetBox[Dog]): Dog = box.get
  
  val dogPutBox = new PutBox[Dog] {}
  val dogGetBox = new GetBox[Dog] {}
  val puppyPutBox = new PutBox[Puppy] {}
  val puppyGetBox = new GetBox[Puppy] {}
  
  putPuppy(puppyPutBox)
  putPuppy(dogPutBox)
  getDog(dogGetBox)
  getDog(puppyGetBox)
}