import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Transcript implements Serializable {
	
	private LinkedList<Course> list;
	
	Transcript(){
		list = new LinkedList();
	}
	Transcript(LinkedList<Course> list){
		this.list = list;
	}
	
	
	public void insert(String name, String symbol,String grade,String hours,String year,String level,String c) {
		int h = Integer.parseInt(hours);
		int y = Integer.parseInt(year);
		int l = Integer.parseInt(level);
		boolean isC;
		if(c.equalsIgnoreCase("Yes"))
			isC = true;
		else
			isC = false;
		Course course = new Course(name,symbol,grade,h,y,l,isC);
		list.insert(course);
	}
	
	public Course delete(String s)throws Exception {
		Course c = search(s);
		list.remove();
		return c;
	}
	
	public Course search(String s)throws Exception {
		if(list.empty())
			throw new Exception("Is empty");
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getSymbol().equalsIgnoreCase(s))
				return list.retrieve();
			list.findNext();
		}
		
		if(list.retrieve().getSymbol().equalsIgnoreCase(s))
			return list.retrieve();
		
		throw new Exception("Course not found");
	}
	
	public LinkedList<Course> nameSearch(String s){
		if(list.empty())
			return null;
		LinkedList<Course> newList = new LinkedList();
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getName().equalsIgnoreCase(s))
				newList.insert(list.retrieve());
			list.findNext();
		}
		if(list.retrieve().getName().equalsIgnoreCase(s))
			newList.insert(list.retrieve());
		
		return newList;
	}
	
	public LinkedList<Course> gradeSearch(String s){
		if(list.empty())
			return null;
		LinkedList<Course> newList = new LinkedList();
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getGrade().equalsIgnoreCase(s))
				newList.insert(list.retrieve());
			list.findNext();
		}
		if(list.retrieve().getGrade().equalsIgnoreCase(s))
			newList.insert(list.retrieve());
		
		return newList;
	}
	
	public LinkedList<Course> yearSearch(String s){
		if(list.empty())
			return null;
		int year = Integer.parseInt(s);
		LinkedList<Course> newList = new LinkedList();
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getYear() == year)
				newList.insert(list.retrieve());
			list.findNext();
		}
		if(list.retrieve().getYear() == year)
			newList.insert(list.retrieve());
		
		return newList;
	}
	
	public LinkedList<Course> levelSearch(String s){
		if(list.empty())
			return null;
		int level = Integer.parseInt(s);
		LinkedList<Course> newList = new LinkedList();
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getLevel() == level)
				newList.insert(list.retrieve());
			list.findNext();
		}
		if(list.retrieve().getLevel() == level)
			newList.insert(list.retrieve());
		
		return newList;
	}
	
	public LinkedList<Course> hoursSearch(String s){
		if(list.empty())
			return null;
		int hours = Integer.parseInt(s);
		LinkedList<Course> newList = new LinkedList();
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getHours() == hours)
				newList.insert(list.retrieve());
			list.findNext();
		}
		if(list.retrieve().getHours() == hours)
			newList.insert(list.retrieve());
		
		return newList;
	}
	
	public LinkedList<Course> takeAll(){
		return list;
	}
		

	public double AllGPA() {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
		    score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
			list.findNext();
		}
	    score+=list.retrieve().getCourseScore();
		hours+=list.retrieve().getHours();
		
		if(hours == 0)
			return 0;
		return score/hours;
	}
	
	public double allCompleteGPA() {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getIsComplete()) {
				score+=list.retrieve().getCourseScore();
				hours+=list.retrieve().getHours();
			}
			list.findNext();
		}
		if(list.retrieve().getIsComplete()) {
			score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
		}
		if(hours == 0)
			return 0;
		return score/hours;
	} 
	
	public double GPAInYear(int year) {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getYear() == year) {
				score+=list.retrieve().getCourseScore();
				hours+=list.retrieve().getHours();
			}
			list.findNext();
		}
		if(list.retrieve().getYear() == year) {
			score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
		}
		if(hours == 0)
			return 0;
		return score/hours;
	}
	
	public double GPACompleteInYear(int year) {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getYear() == year && list.retrieve().getIsComplete()) {
				score+=list.retrieve().getCourseScore();
				hours+=list.retrieve().getHours();
			}
			list.findNext();
		}
		if(list.retrieve().getYear() == year && list.retrieve().getIsComplete()) {
			score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
		}
		if(hours == 0)
			return 0;
		return score/hours;
	}
	
	public double GPAInLevel(int semester) {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getLevel() == semester) {
				score+=list.retrieve().getCourseScore();
				hours+=list.retrieve().getHours();
			}
			list.findNext();
		}
		if(list.retrieve().getLevel() == semester) {
			score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
		}
		if(hours == 0)
			return 0;
		return score/hours;
	}
	
	public double GPACompleteInLevel(int semester) {
		if(list.empty())
			 return 0;
		double score = 0;
		int hours = 0;
		list.findFirst();
		while(!(list.last())) {
			if(list.retrieve().getLevel() == semester && list.retrieve().getIsComplete()) {
				score+=list.retrieve().getCourseScore();
				hours+=list.retrieve().getHours();
			}
			list.findNext();
		}
		if(list.retrieve().getLevel() == semester && list.retrieve().getIsComplete()) {
			score+=list.retrieve().getCourseScore();
			hours+=list.retrieve().getHours();
		}
		if(hours == 0)
			return 0;
		return score/hours;
	}
	
	public void save(String fileName) throws IOException {
	    if (list.empty())
	        return;

	    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
	        list.findFirst();
	        output.writeInt(list.length());

	        while (!(list.last())) {
	            output.writeObject(list.retrieve());
	            list.findNext();
	        }

	        output.writeObject(list.retrieve());
	    }
	}


	public void load(String fileName) throws IOException, ClassNotFoundException {
	    File f = new File(fileName);

	    if (!f.exists()) {
	        // Handle the case where the file doesn't exist
	        return;
	    }

	    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
	        int size = input.readInt();
	        
	        for (int i = 0; i < size; i++) {
	            list.insert((Course) input.readObject());
	        }
	    }
	}

	
}
