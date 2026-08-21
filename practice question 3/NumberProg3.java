import java.util.Scanner;

public class NumberProg3 
{
    public static void main(String [] arg)
    {
         Scanner sc=new Scanner(System.in);
        System.out.println("Enter three number to find out largest number");
        int x=sc.nextInt();
        int y=sc.nextInt();
        int z=sc.nextInt();
        if(x>y)
        {
            if(x>y)
            {
               System.out.println(x+" is greatest number");
            }
        }
        else if(y>x)
        {
            if(y>z)
            {
              System.out.println(y+" is greatest number");  
            }
        }
        else
        {
            System.out.println(z+" is greatest number");
        }
        sc.close();
    }
}
