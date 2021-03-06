import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class HbOpAdd{
public static void main(String args[]){
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s= null;
Transaction t = null;

try{
	s = sf.openSession();
	System.out.println(" connected ");
	t = s.beginTransaction();
	Console c = System.console();
	int rno = Integer.parseInt(c.readLine("Enter Students Roll No : "));
	if(rno<0)
		System.out.println("Invalid Rno.");	
	String name = c.readLine("Enter Student Name :");

	String sub1Marks = c.readLine("Enter subject 1 marks :");
	int sub1 = Integer.parseInt(sub1Marks); 
	if(sub1< 0 || sub1>100)
		System.out.println("Invalid marks for subject");

	String sub2Marks = c.readLine("Enter subject 2 marks :");
	int sub2 = Integer.parseInt(sub2Marks); 
	if(sub2< 0 || sub2>100)
		System.out.println("Invalid marks for subject");

	String sub3Marks = c.readLine("Enter subject 3 marks :");
	int sub3 = Integer.parseInt(sub3Marks); 
	if(sub3< 0 || sub3>100)
		System.out.println("Invalid marks for subject");
	Student stu = new Student(rno,name,sub1Marks,sub2Marks,sub3Marks);
	s.save(stu);
	t.commit();
	System.out.println("record added");
}

catch(Exception e)
{
System.out.println("issue e");
t.rollback();
}

finally{
	s.close();
	System.out.println(" closed ");
}


}
}