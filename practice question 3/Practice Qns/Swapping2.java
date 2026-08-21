public class Swapping2 
{
     public static void main(String [] arg)  
 {
    int a=10;
    int b= 20;
    System.out.println("Before Swapping");
    System.out.println("value of a :"+a);
    System.out.println("value of b :"+b);
    a=a+b;
    b=a-b;
    a=a-b;
    System.out.println("After Swapping");
    System.out.println("value of a :"+a);
    System.out.println("value of b :"+b);
 } 
}
