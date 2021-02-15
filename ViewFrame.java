import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;


class ViewFrame extends JFrame{
Container c;
TextArea tadata;
JButton btnBack;

ViewFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

tadata = new TextArea(10,50);
btnBack = new JButton("Back");


c.add(tadata);	 
c.add(btnBack);	 

//tadata.setText();

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s= null;


String dataop="";
try{
	s = sf.openSession();
	System.out.println(" connected ");
	java.util.List<Student> data = new ArrayList<>();
	data = s.createQuery("from Student").list();
	for(Student stu : data)
		dataop=dataop+stu.getrno() +" "+stu.getname()+" "+stu.getsub1Marks()+" "+stu.getsub2Marks()+" " +stu.getsub3Marks()+"\n";
		//System.out.println(stu.getrno() +" "+stu.getname()+" "+stu.getsub1Marks()+" "+stu.getsub2Marks()+" " +stu.getsub3Marks());
	
}

catch(Exception e)
{
JOptionPane.showMessageDialog(c,"An error has occurred. Please restart the App.","Error",JOptionPane.ERROR_MESSAGE);
}

finally{
	tadata.setText(dataop);
	s.close();
	System.out.println(" closed ");
}













ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};

btnBack.addActionListener(a1);


setTitle("View Student");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);





}
}