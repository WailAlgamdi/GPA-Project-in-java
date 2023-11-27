import java.io.Serializable;
public class Node implements Serializable{

private Course data;
private Node next;

Node(Course data){
	this.data = data;
	next = null;
}

public Course getData() {
	return data;
}
public Node getNext() {
	return next;
}

public void setData(Course data) {
	this.data = data;
}

public void setNext(Node next) {
	this.next = next;
}

}
