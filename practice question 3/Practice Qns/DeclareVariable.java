import java.util.*;
class DeclareVariable
{
    public static void main(String [] arg)
{
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter an integer number");
    int x=sc.nextInt();
    System.out.println("Enter an float number");
    float f=sc.nextFloat();
    System.out.println("Enter an double number");
    double d=sc.nextDouble();
    boolean b=true;
    System.out.println("value of integer number: "+x);
    System.out.println("value of floating number: "+f);
    System.out.println("value of double number: "+d);
    System.out.println("value of boolean number: "+b);
    sc.close();
}
}