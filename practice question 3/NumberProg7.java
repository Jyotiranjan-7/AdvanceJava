import java.util.Scanner;

public class NumberProg7
{
    public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number to find sum of digit");
        int x=sc.nextInt();
        System.out.println("before reverse number :"+x);
        int rev=0;
        while(x!=0)
        {
            int rem=x%10;
            x=x/10;
            rev=rev*10+rem;
        }
        System.out.println("After reverse number :"+rev);
        sc.close();
    }
}
