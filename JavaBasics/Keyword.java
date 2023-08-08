public class Keyword{
 public static void main(String args[]){
  System.out.println("without public keyword--> Main method not found in class Example please define the main method as:public");
  System.out.println("without static keyword--> Main method is not static in class Keyword");
  System.out.println("without void keyword--> invalid method declaration; return type required");
  System.out.println("without main keyword-->  error: <identifier> expected");
  System.out.println("without main keyword-->  Main method must return a value of type void in class Keyword");
  System.out.println("different class and file name-->  error: file not found: Keyword.java");
 }
}