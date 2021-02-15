import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.title.Title;
import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class ChartsFrame extends JFrame{
JButton backBtn;
JButton downloadBtn;
JPanel btnPanel;
ChartPanel p;
ChartsFrame(){
btnPanel = new JPanel();
backBtn = new JButton("BACK");
downloadBtn = new JButton("DOWNLOAD");

//step 1: create dataset
DefaultCategoryDataset ds = new DefaultCategoryDataset();
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","abc123");
String sql = "select * from student";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(sql);

while(rs.next()){

	String name=rs.getString(2);
	int sub1 = Integer.parseInt(rs.getString(3));
	int sub2 = Integer.parseInt(rs.getString(4));
	int sub3 = Integer.parseInt(rs.getString(5));
	
	ds.addValue(sub1,name,"Subject 1");
	ds.addValue(sub2,name,"Subject 2");
	ds.addValue(sub3,name,"Subject 3");
}
rs.close();
}
catch(SQLException e){
System.out.println("issue " + e);
}

//step 2 : design chart

JFreeChart chart = ChartFactory.createBarChart("Students Performance","Subjects","Marks",ds,
PlotOrientation.VERTICAL,true,false,false);

//step 3: chart display
p =new ChartPanel(chart);
//setContentPane(p);
p.setVisible(true);
layoutControl();



Dimension btnSize = downloadBtn.getPreferredSize();
backBtn.setPreferredSize(btnSize);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
backBtn.addActionListener(a1);

ActionListener a2 = (ae) -> {
try{
	File img = new File("perform.jpeg");
	ChartUtilities.saveChartAsJPEG(img,chart,700,300);
	JOptionPane.showMessageDialog(ChartsFrame.this,"Download Successful!");
}
catch(IOException e){
		JOptionPane.showMessageDialog(ChartsFrame.this,"Download Unsuccessful!");
}
};
downloadBtn.addActionListener(a2);




//step 4 : save chart as jpeg
/*try{
	File img = new File("perform.jpeg");
	ChartUtilities.saveChartAsJPEG(img,chart,700,300);

}catch(IOException e){
System.out.println("issue "+ e);
}*/

setTitle("Students Performance");
setSize(600,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public void layoutControl() {
		setLayout(new BorderLayout());
		add(p,BorderLayout.CENTER);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(backBtn);
		btnPanel.add(downloadBtn);
		add(btnPanel,BorderLayout.SOUTH);

	
	}
}
