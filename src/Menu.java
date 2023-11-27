import java.io.IOException;
import java.util.Scanner;
public class Menu {
	public static void main(String[] args) {
Scanner kb = new Scanner(System.in);
AcadimicList wael = new AcadimicList();

try {
	wael.load("data2");
}catch(IOException e) {
	e.printStackTrace();
}catch(ClassNotFoundException e) {
	e.printStackTrace();
}


int n;
do {
	try {
		wael.save("data2");
	}catch(IOException e) {
		e.printStackTrace();
	}
	System.out.println("____________________________________________________");
	System.out.println("chose one of the following option: ");
	System.out.println("1)Add a complete course ");
	System.out.println("2)Add a incomplete course");
	System.out.println("3)Remove a course ");
	System.out.println("4)Search or modify");
	System.out.println("5)Display total GPA");
	System.out.println("6)Display GPA in year");
	System.out.println("7)Display GPA in term");
	System.out.println("8)Display All complete course");
	System.out.println("9)Display All incomplete course");
	System.out.println("10)Display All");
	System.out.println("0)Exit");
	n = kb.nextInt();
	
	
	if(n == 1)
		wael.insertAtBack(wael.read(1));
	if(n == 2)
		wael.insertAtBack(wael.read(2));
	
	if(n == 3) 
		try {
			System.out.println("Enter symbol of the course you want to remove");
			wael.remove(kb.next());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	
	if(n == 4) 
		try {
			System.out.println("Enter symol of the course");
			wael.modify(kb.next()).display();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	if(n == 5)
		System.out.println(wael.allGPA());
	
	if(n == 6) {
		System.out.println("Enter number of the year");
		System.out.println(wael.GPAInYear(kb.nextInt()));
	}
	
	if(n == 7) {
		System.out.println("Enter number of the Term");
		System.out.println(wael.GPAInTerm(kb.nextInt()));
	}
	
	if(n == 8)
		wael.displayComplete();
	if(n == 9)
		wael.displayIncomplete();
	if(n == 10)
		wael.displayAll();
	
	

	
	
}while(n != 0);




try {
	wael.save("dataBackUp2");
}catch(IOException e) {
	e.printStackTrace();
}




System.out.println("--------------------| DONE |--------------------");
	}

}
