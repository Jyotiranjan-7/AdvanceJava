import java.util.*;
public class Arrayprog1 
{
    public static void main(String [] arg)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size=sc.nextInt();
        char[] arr=new char[size];
        System.out.println("Enter the character to the array");
        for(int i=0;i<size;i++)
        {
            arr[i]=sc.next().charAt(0);
        }
        System.out.println("Reverse order array is: ");
        for(int i=size-1;i>=0;i--)
        {
            System.out.print(arr[i]+" ");
        }
        sc.close();
    }
}
