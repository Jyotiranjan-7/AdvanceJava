import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Driver {
   static EntityManagerFactory emf =Persistence.createEntityManagerFactory("HND");
   static EntityManager em=emf.createEntityManager();
   static EntityTransaction et= em.getTransaction();
   static Scanner sc=new Scanner(System.in);
    public static void main(String [] args)
    {

        int choice=0;

        do{
            System.out.println("1.Add student");
            System.out.println("2.Update student");
            System.out.println("3.Find student by id");
            System.out.println("4.Delete student");
            System.out.println("5.exit");
            choice=sc.nextInt();
            switch (choice){
                case 1:boolean resultAdd=add();
                if(resultAdd){
                    System.out.println("Student add successful..");
                }
                break;
                case 2:boolean resultUpdate=update();
                    if(resultUpdate) {
                        System.out.println("update successful..");
                    }
                break;
                case 3:Student student=find();
                System.out.println(student);
                break;
                case 4:boolean resultRemove =remove();
                    if(resultRemove){
                        System.out.println("Data delete successful..");
                    }
                break;
                case 5:System.out.println("Exit from the program..");
                break;
                default:
                    System.out.println("Not match..");
            }
        }
        while(choice !=5);

    }

    private static boolean add(){
        System.out.println("Enter the id of the student");
        int id= sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the name of the student");
        String name=sc.nextLine();
        System.out.println("Enter the course");
        String course=sc.nextLine();
        Student s1=new Student(id,name,course);
        et.begin();
        em.persist(s1);
        et.commit();
        return true;
    }
    private static boolean update() {
        et.begin();
        System.out.println("Enter the id you want to update");
        int id= sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the name you want to update");
        String name=sc.nextLine();
        System.out.println("Enter the course tou want to update");
        String course=sc.nextLine();
        Student s1=new Student(id,name,course);
        em.merge(s1);
        et.commit();
        return true;
    }
    private static Student find() {
        et.begin();
        System.out.println("Enter the id you want to find");
        int id=sc.nextInt();
        Student student=em.find(Student.class,id);
        et.commit();
        return student;
    }
    private static boolean remove(){
        et.begin();
        System.out.println("Enter the id you want to delete");
        int id=sc.nextInt();
        Student student=em.find(Student.class,id);
        em.remove(student);
        et.commit();
        return true;
    }

}
