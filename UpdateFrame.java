import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Box;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
//S1 : import
import java.sql.*;

class UpdateFrame extends JFrame{
Container c,p;
JLabel lblrno,lblname,lblsub1,lblsub2,lblsub3;
JTextField txtrno,txtname,txtsub1,txtsub2,txtsub3;
JButton btnSave,btnBack;
JPanel panel;

UpdateFrame(){
c=getContentPane();
p=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
p.setLayout(new FlowLayout());

panel = new JPanel();
lblrno = new JLabel("Enter Roll No: ");
txtrno = new JTextField(25);
lblname = new JLabel("Enter Student Name ");
txtname = new JTextField(25);
lblsub1 = new JLabel("Enter Subject 1 Marks ");
txtsub1 = new JTextField(25);
lblsub2 = new JLabel("Enter Subject 2 Marks ");
txtsub2 = new JTextField(25);
lblsub3 = new JLabel("Enter Subject 3 Marks ");
txtsub3 = new JTextField(25);

btnSave = new JButton("Save");
btnBack = new JButton("Back");

c.add(lblrno);	 

c.add(txtrno);


c.add(lblname);  c.add(txtname);


c.add(lblsub1);  c.add(txtsub1);
;

c.add(lblsub2); c.add(txtsub2);


c.add(lblsub3); c.add(txtsub3);

p.add(panel.add(btnSave));	 p.add(panel.add(btnBack));

panel.setAlignmentX(Component.CENTER_ALIGNMENT);

txtrno.setMaximumSize(txtrno.getPreferredSize());

txtname.setMaximumSize(txtrno.getPreferredSize());


txtsub1.setMaximumSize(txtrno.getPreferredSize());

txtsub2.setMaximumSize(txtrno.getPreferredSize());

txtsub3.setMaximumSize(txtrno.getPreferredSize());

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
int rno;
String name;
int sub1Marks;
int sub2Marks;
int sub3Marks;
int flag=0;
//rno validate
try{
rno = Integer.parseInt(txtrno.getText());
if(rno < 0) {
JOptionPane.showMessageDialog(c,"Please Enter correct Roll Number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtrno.setText("");
txtrno.requestFocus();
flag=1;
}
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Please Enter correct roll number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtrno.setText("");
txtrno.requestFocus();
flag=1;
}

//name validate
name = txtname.getText().toLowerCase().trim();
boolean allLetters = name.chars().allMatch(Character::isLetter);
if(name==null || name=="" || !allLetters)
	{
	JOptionPane.showMessageDialog(c,"Enter correct name.","Wrong Input",JOptionPane.ERROR_MESSAGE);
	txtname.setText("");
	txtname.requestFocus();
	flag=1;
	}
if(name.length()<2)
	{
	JOptionPane.showMessageDialog(c,"Name too short.","Wrong Input",JOptionPane.ERROR_MESSAGE);
	txtname.setText("");
	txtname.requestFocus();
	flag=1;
	}

//sub1 validate
try{
sub1Marks = Integer.parseInt(txtsub1.getText());
if(sub1Marks < 0 || sub1Marks>100) {
JOptionPane.showMessageDialog(c,"Subject 1 Marks Out Of Range!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub1.setText("");
txtsub1.requestFocus();
flag=1;
}
}catch(NumberFormatException ne){
JOptionPane.showMessageDialog(c,"Enter correct subject 1 marks","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub1.setText("");
txtsub1.requestFocus();
flag=1;
}

//sub2 validate
try{
sub2Marks = Integer.parseInt(txtsub2.getText());
if(sub2Marks < 0 || sub2Marks>100) {
JOptionPane.showMessageDialog(c,"Subject 2 Marks Out Of Range!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub2.setText("");
txtsub2.requestFocus();
flag=1;
}
}catch(NumberFormatException ne){
JOptionPane.showMessageDialog(c,"Enter correct subject 2 marks","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub2.setText("");
txtsub2.requestFocus();
flag=1;
}

//sub3 validate
try{
sub3Marks = Integer.parseInt(txtsub3.getText());
if(sub3Marks < 0 || sub3Marks>100) {
JOptionPane.showMessageDialog(c,"Subject 3 Marks Out Of Range!","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub3.setText("");
txtsub3.requestFocus();
flag=1;
}
}catch(NumberFormatException ne){
JOptionPane.showMessageDialog(c,"Enter correct subject 3 marks","Wrong Input",JOptionPane.ERROR_MESSAGE);
txtsub3.setText("");
txtsub3.requestFocus();
flag=1;
}

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s= null;
Transaction t = null;


try{
	s = sf.openSession();
	System.out.println(" connected ");
	t = s.beginTransaction();

	rno = Integer.parseInt(txtrno.getText());	
	Student stu = (Student)s.get(Student.class, rno);

	if(stu == null)
	{
		System.out.println("Roll no does not exist");
	}else if(flag==0){
	String sub1 = txtsub1.getText();
	String sub2 = txtsub2.getText();
	String sub3 = txtsub3.getText();
	
	stu.setname(name);
	stu.setsub1Marks(sub1);
	stu.setsub2Marks(sub2);	
	stu.setsub3Marks(sub3);
	s.save(stu);
	t.commit();
	JOptionPane.showMessageDialog(c,"Record updated successfully");
	txtrno.setText("");
	txtname.setText("");
	txtsub1.setText("");
	txtsub2.setText("");
	txtsub3.setText("");
	}
	else{}
}

catch(Exception e)
{
//JOptionPane.showMessageDialog(c,"An error has occurred. Please restart the App.","Error",JOptionPane.ERROR_MESSAGE);
t.rollback();
}

finally{
	s.close();
	System.out.println(" closed ");
}


};
btnSave.addActionListener(a2);

setTitle("Update Student");
setSize(350,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);





}
}