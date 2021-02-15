import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class HbOpDelete {
public static void main(String args []) {
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

try {
s = sf.openSession();
System.out.println("Connected");

t = s.beginTransaction();
Console c = System.console();
int rno = Integer.parseInt(c.readLine("Enter Roll Number to be deleted:- "));
if(rno<0)
		System.out.println("Invalid Rno.");	
Student stu = (Student)s.get(Student.class, rno);
if(stu == null) {
	System.out.println(rno + " does not exists.");
} else {
	s.delete(stu);
	System.out.println(rno + " deleted");
}

t.commit();

}
catch (Exception e) {
System.out.println("Issue:- " + e);
t.rollback();
}
finally {
s.close();
System.out.println("Closed");
}
}
}