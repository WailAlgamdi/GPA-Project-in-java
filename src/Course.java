import java.io.Serializable;
public class Course implements Grade,Serializable {

private String name;
private String symbol;
private String grade;
private int hours;
private int year;
private int term;
private boolean isComplete;

Course(String name, String symbol, String grade, int hours, int year, int term,boolean c) {
	this.name = name;
	this.symbol = symbol;
	this.grade = grade;
	this.hours = hours;
	this.year = year;
	this.term = term;
	this.isComplete = c;
}

public double totalCourseScore() {
	return hours*Ap;
}

public double getCourseScore() {
	double g=0;
	if(grade.equals("A+")) g = Ap;
	else if(grade.equals("A")) g = A;
	else if(grade.equals("B+")) g = Bp;
	else if(grade.equals("B")) g = B;
	else if(grade.equals("C+")) g = Cp;
	else if(grade.equals("C")) g = C;
	else if(grade.equals("D+")) g = Dp;
	else if(grade.equals("D")) g = D;
	
	return g*hours;
}
public void display() {
	System.out.println("------------------------");
	System.out.println("Name: "+name);
	System.out.println("Symbol: "+symbol);
	System.out.println("Grade: "+grade);
	System.out.println("Hours: "+hours);
	System.out.println("Take in year: "+year);
	System.out.println("Take in term: "+term);
	System.out.println("Total Score: "+this.totalCourseScore());
	System.out.println("Score I get: "+this.getCourseScore());
}

//Getters
public String getName() {
	return this.name;
}

public String getSymbol() {
	return this.symbol;
}

public String getGrade() {
	return this.grade;
}

public int getHours() {
	return hours;
}

public int getYear() {
	return year;
}

public int getTerm() {
	return term;
}

public boolean getIsComplete() {
	return isComplete;
}

// Setters
public void setIsComplete(boolean a) {
	this.isComplete =a;
}

public void setName(String name) {
	this.name = name;
}

public void setSymbol(String symbol) {
	this.symbol = symbol;
}

public void setGrade(String grade) {
	this.grade = grade;
}

public void setHours(int hours) {
	this.hours = hours;
}

public void setYear(int year) {
	this.year = year;
}

public void setTerm(int term) {
	this.term = term;
}

}
