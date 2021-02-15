class Student{
private int rno;
private String name;
private String sub1Marks;
private String sub2Marks;
private String sub3Marks;

public Student(){
}
public Student(int rno,String name,String sub1Marks,String sub2Marks,String sub3Marks){
this.rno=rno;
this.name=name;
this.sub1Marks=sub1Marks;
this.sub2Marks=sub2Marks;
this.sub3Marks=sub3Marks;
}

public void setrno(int rno)		{this.rno=rno;}
public int getrno()			{return rno;}
public void setname(String name)	{this.name=name;}
public String getname()		{return name;}
public void setsub1Marks(String sub1Marks)	{this.sub1Marks=sub1Marks;}
public String getsub1Marks()		{return sub1Marks;}
public void setsub2Marks(String sub2Marks)	{this.sub2Marks=sub2Marks;}
public String getsub2Marks()		{return sub2Marks;}
public void setsub3Marks(String sub3Marks)	{this.sub3Marks=sub3Marks;}
public String getsub3Marks()		{return sub3Marks;}



}