import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class AcadimicList {

private Node head;

AcadimicList(){
	head = null;
}

public Course read(int k) {
	Scanner kb = new Scanner(System.in);
	Course course ;
	if(k == 1) {
		System.out.println("Enter name of the course");
		String name = kb.next();
		System.out.println("Enter symbol of the course");
		String symbol = kb.next();
		System.out.println("Enter grade you take in the course");
		String grade = kb.next();
		System.out.println("How many hours of the course");
		int hours = kb.nextInt();
		System.out.println("In any year you took the course");
		int year = kb.nextInt();
		System.out.println("In any term you took the course");
		int term = kb.nextInt();
		
		course = new Course(name,symbol,grade,hours,year,term,true);
		
	}
	else {
		System.out.println("Enter name of the course");
		String name = kb.next();
		System.out.println("Enter symbol of the course");
		String symbol = kb.next();
		System.out.println("Enter grade you take in the course");
		String grade = kb.next();
		System.out.println("How many hours of the course");
		int hours = kb.nextInt();
		System.out.println("In any year you took the course");
		int year = kb.nextInt();
		System.out.println("In any term you took the course");
		int term = kb.nextInt();
		
		course = new Course(name,symbol,grade,hours,year,term,false);
	}
	return course;
}


public Node search(String s)throws Exception {
	Node current = head;
	while(current != null) {
		if(current.getData().getSymbol().equalsIgnoreCase(s))
			return current;
		current = current.getNext();
	}
	throw new Exception("Course not found");
}

public void insertAtFront(Course c) {
	Node newNode = new Node(c);
	newNode.setNext(head);
	head = newNode;
}


public void insertAtBack(Course c) {
	if( head == null) {
		insertAtFront(c);
		return;
	}
	Node newNode = new Node(c);
	Node current = head;
	while(current.getNext() != null) 
		current = current.getNext();
	current.setNext(newNode);
}

public Course remove(String symbol)throws Exception {
	Node current = head;
	Node target = search(symbol);
	if(target == head) {
		head = head.getNext();
		return target.getData();
	}
	else {
		while(current != null) {
			if(current.getNext() == target) 
				current.setNext(target.getNext());
			current = current.getNext();
		}
		return target.getData();
	}
}

public Course modify(String symbol) throws Exception{
	Node target = search(symbol);
	Scanner kb = new Scanner(System.in);
	
	System.out.println("1) Change the Name");
	System.out.println("2) Change the Symbol");
	System.out.println("3) Change the Grade");
	System.out.println("4) Change the Hours");
	System.out.println("5) Change the Year");
	System.out.println("6) Change the Term");
	System.out.println("7) Change the State");
	System.out.println("Other numbers) Cancel ");
	int num =kb.nextInt();
	
	
	if(num == 1) {
		System.out.println("Enter the new Name of the course");
		String newName =kb.next();
		System.out.println(" 1-->New name: "+newName);
		System.out.println("2-->Old name: "+target.getData().getName());
		if(kb.nextInt() == 1)
			target.getData().setName(newName);
	}
	else if(num == 2) {
		System.out.println("Enter the new Symbol of the course");
		String newSymbol = kb.next();
		System.out.println(" 1-->New Symbol: "+newSymbol);
		System.out.println("2-->Old Symbol: "+target.getData().getSymbol());
		if(kb.nextInt() == 1)
			target.getData().setSymbol(newSymbol);
	}
	else if(num == 3) {
		System.out.println("Enter the new Grade of the course");
		String newGrade =kb.next();
		System.out.println(" 1-->New Grade: "+newGrade);
		System.out.println("2-->Old Grade: "+target.getData().getGrade());
		if(kb.nextInt() == 1)
			target.getData().setGrade(newGrade);
	}
	else if(num == 4) {
		System.out.println("Enter the new hours of the course");
		int newHours =kb.nextInt();
		System.out.println(" 1-->New hours: "+newHours);
		System.out.println("2-->Old hours: "+target.getData().getHours());
		if(kb.nextInt() == 1)
			target.getData().setHours(newHours);
	}
	else if(num == 5) {
		System.out.println("Enter the new year of the course");
		int newYear =kb.nextInt();
		System.out.println(" 1-->New year: "+newYear);
		System.out.println("2-->Old year: "+target.getData().getYear());
		if(kb.nextInt() == 1)
			target.getData().setYear(newYear);
	}
	else if(num == 6) {
		System.out.println("Enter the new term of the course");
		int newTerm =kb.nextInt();
		System.out.println(" 1-->New term: "+newTerm);
		System.out.println("2-->Old term: "+target.getData().getTerm());
		if(kb.nextInt() == 1)
			target.getData().setTerm(newTerm);
	}
	else if(num == 7) {
		System.out.println("Enter 1 if want to opposite the state of the course ");
		if(kb.nextInt() == 1) target.getData().setIsComplete(!(target.getData().getIsComplete()));
	}
	else
		System.out.println("--------| Nothing change |--------");
	
	
	return target.getData();
}

public double allScore() {
	Node current = head;
	double score = 0;
	while(current != null) {
		score+=current.getData().totalCourseScore();
		current = current.getNext();
	}
	return score;
}

public double totalScoreGot() {
	Node current = head;
	double score = 0;
	while(current != null) {
		score+=current.getData().getCourseScore();
		current = current.getNext();
	}
	return score;
}

public int totalHours() {
	Node current = head;
	int hours = 0;
	while(current != null) {
		hours+=current.getData().getHours();
		current = current.getNext();
	}
	return hours;
}


public double allGPA() {
	double GPA = 0;
	GPA = this.totalScoreGot()/totalHours();
	return GPA;
}


public double GPAInYear(int year) {
	double score = 0;
	int hours = 0;
	Node current = head;
	while(current != null) {
		if(current.getData().getYear() == year) {
			score+=current.getData().getCourseScore();
			hours+=current.getData().getHours();
		}
		current = current.getNext();
	}
	return score/hours;
}


public double GPAInTerm(int term) {
	double score = 0;
	int hours = 0;
	Node current = head;
	while(current != null) {
		if(current.getData().getTerm() == term) {
			score+=current.getData().getCourseScore();
			hours+=current.getData().getHours();
		}
		current = current.getNext();
	}
	return score/hours;
}


public void displayComplete() {
	Node current = head;
	while(current != null) {
		if(current.getData().getIsComplete())
			current.getData().display();
		current = current.getNext();
	}
}

public void displayIncomplete() {
	Node current = head;
	while(current != null) {
		if(!(current.getData().getIsComplete()))
			current.getData().display();
		current = current.getNext();
	}
}


public void displayAll() {
	Node current = head;
	while(current != null) {
		current.getData().display();
		current = current.getNext();
	}
}

public int size() {
	int size =0;
	Node current = head;
	while(current != null) {
		if(current.getData().getIsComplete())
			size++;
		current = current.getNext();
	}
	return size;
}

//Save only the course is complete
//The file will be named 
public void save(String fileName)throws IOException {
	File f = new File(fileName);
	FileOutputStream fos = new FileOutputStream(f);
	ObjectOutputStream output = new ObjectOutputStream(fos);
	
	//By using isComplete boolean 
	Node current = head; 
	output.writeInt(size()); // size form the size method ( is only count the complete course )
	while(current != null) {
		if(current.getData().getIsComplete())
			output.writeObject(current);
		current = current.getNext();
	}
	output.close(); fos.close();
}

public void load(String fileName)throws IOException,ClassNotFoundException {
	File f = new File(fileName);
	FileInputStream fi = new FileInputStream(f);
	ObjectInputStream input = new ObjectInputStream(fi);
	
	int size = input.readInt();
	for(int i = 0;i<size;i++) {
		insertAtBack(((Node)input.readObject()).getData());
	}
	input.close(); fi.close();
}

}