package com.example.STA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class App {
    public static void main(String [] args)
    {
        ApplicationContext context=new AnnotationConfigApplicationContext(ReplaceXML.class);
        Employee emp=(Employee) context.getBean(Employee.class);
        emp.display();
    }
}
