import java.util.*;
public class NumbarProgram9 
{
    
     public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number to find sum of digit");
        int x=sc.nextInt();
        int count=0;
        while(x!=0)
        {
            int rem=x%10;
            x=x/10;
            count++;
        }
        System.out.println(count+" digits are present");
        sc.close();
    }
}
