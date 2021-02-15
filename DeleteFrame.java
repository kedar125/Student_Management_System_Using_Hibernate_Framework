import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Box;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

//S1 : import
import java.sql.*;

class DeleteFrame extends JFrame{
Container c,p;
JLabel lblrno,lblname,lblsub1,lblsub2,lblsub3;
JTextField txtrno,txtname,txtsub1,txtsub2,txtsub3;
JButton btnSave,btnBack;
JPanel panel;
DeleteFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
p=getContentPane();
p.setLayout(new FlowLayout());

panel = new JPanel();
lblrno = new JLabel("Enter Roll No: ");
txtrno = new JTextField(20);


btnSave = new JButton("Delete");
btnBack = new JButton("Back");


Dimension dim = btnSave.getPreferredSize();
btnBack.setPreferredSize(dim);

c.add(panel.add(lblrno));	 
c.add(Box.createRigidArea(new Dimension(0, 30)));
c.add(panel.add(txtrno));
c.add(Box.createRigidArea(new Dimension(0, 30)));
p.add(btnSave);	 
//c.add(Box.createRigidArea(new Dimension(0, 10)));
p.add(btnBack);

txtrno.setMaximumSize( txtrno.getPreferredSize() );
panel.setAlignmentX(Component.CENTER_ALIGNMENT);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
int rno;
int flag=0;
//rno validate
try{
rno = Integer.parseInt(txtrno.getText());
if(rno < 0) {
JOptionPane.showMessageDialog(c,"Please Enter correct Roll Number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtrno.setText("");
txtrno.requestFocus();
flag=0;
}
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Please Enter correct roll number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtrno.setText("");
txtrno.requestFocus();
}

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
rno = Integer.parseInt(txtrno.getText());
Student stu = (Student)s.get(Student.class, rno);
if(stu == null) {
	JOptionPane.showMessageDialog(DeleteFrame.this, txtrno.getText() +" does not exist","Error",JOptionPane.ERROR_MESSAGE);
} else if(flag==0){
	s.delete(stu);
	JOptionPane.showMessageDialog(DeleteFrame.this,"Record deleted successfully");
	txtrno.setText("");
}

t.commit();


}
catch (Exception e) {
//JOptionPane.showMessageDialog(DeleteFrame.this,"An error has occurred. Please restart the App.","Error",JOptionPane.ERROR_MESSAGE);
t.rollback();
}
finally {
s.close();
System.out.println("Closed");
}

};
btnSave.addActionListener(a2);

setTitle("Delete Student");
setSize(350,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}