import java.util.Scanner;

public class NumberProg2
{
    public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number to check positive , negative or zero");
        int x=sc.nextInt();
        if(x>0)
        {
            System.out.println(x+" is a positive number");
        }
        else if(x<0)
        {
            System.out.println(x+" is a Negative number");
        }
        else
        {
            System.out.println(x+" is a zero");
        }
        sc.close();
    }
}
