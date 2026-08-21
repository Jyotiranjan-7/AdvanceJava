import java.util.Scanner;

public class NumberProg8 
{
     public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number to find sum of digit");
        int x=sc.nextInt();
        int rev=0;
        int temp=x;
        while(x!=0)
        {
            int rem=x%10;
            x=x/10;
            rev=rev*10+rem;
        }
        if(temp==rev)
        {
        System.out.println(rev+" is a palindrome number");
        }
        else
        {
        System.out.println(rev+" is not a palindrome number");
        }
        sc.close();
    }
}
