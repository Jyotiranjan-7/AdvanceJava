import java.util.Scanner;

public class NumberProg6 
{
     public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number to find sum of digit");
        int x=sc.nextInt();
        int sum=0;
        while(x!=0)
        {
            int rem=x%10;
            x=x/10;
            sum=sum+rem;
        }
        System.out.println("sum of digits :"+sum);
        sc.close();
    }
}
