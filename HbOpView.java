import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class HbOpView{
public static void main(String args[]){
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s= null;



try{
	s = sf.openSession();
	System.out.println(" connected ");
	List<Student> data = new ArrayList<>();
	data = s.createQuery("from Student").list();
	for(Student stu : data)
		System.out.println(stu.getrno() +" "+stu.getname()+" "+stu.getsub1Marks()+" "+stu.getsub2Marks()+" " +stu.getsub3Marks());
}

catch(Exception e)
{
System.out.println("issue e");
}

finally{
	s.close();
	System.out.println(" closed ");
}


}
}