import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
Container c;
JButton  btnAdd,btnView,btnUpdate,btnDelete,btnCharts;

MainFrame(){
c = getContentPane();
c.setLayout(new FlowLayout(1,40,20));

btnAdd = new JButton("Add");
btnView=new JButton("View");
btnUpdate= new JButton("Update");
btnDelete= new JButton("Delete");
btnCharts= new JButton("Charts");

Dimension dim = btnUpdate.getPreferredSize();
btnAdd.setPreferredSize(dim);
btnView.setPreferredSize(dim);
btnDelete.setPreferredSize(dim);
btnCharts.setPreferredSize(dim);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnCharts);

//add
ActionListener a1 = (ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

//view
ActionListener a2 = (ae) -> {
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

//update
ActionListener a3 = (ae) -> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

//delete
ActionListener a4 = (ae) -> {
DeleteFrame a = new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

//charts
ActionListener a5 = (ae) -> {
ChartsFrame a = new ChartsFrame();
dispose();
};
btnCharts.addActionListener(a5);

setTitle("S. M. S");
setSize(300,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


}
}