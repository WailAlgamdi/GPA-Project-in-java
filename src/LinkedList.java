public class LinkedList<T>  {

private Node head;
private Node current;


LinkedList(){
	head = current = null;
	
}

public void findFirst() {
	current = head;
}

public void findNext() {
	current = current.getNext();
}

public T retrieve() {
	return (T)current.getData();
}

public void update(T e) {
	current.setData(e);
}

public void insert(T e) {
	Node<T> newNode = new Node(e);
	if(empty())
		head = current = newNode;
	else {
	newNode.setNext(current.getNext());
	current.setNext(newNode); 
	}
}
public void remove() {
	if(current == head)
		head = current.getNext();
	else {
		Node<T> temp = head;
		while(temp.getNext() != current)
			temp = temp.getNext();
		temp.setNext(current.getNext());
	}
	if(current.getNext() == null)
		current = head;
	else
		current = current.getNext();
}

public boolean full() {
	return false;
}
public boolean empty() {
	return head == null;
}
public boolean last() {
	return current.getNext() == null;
}

public int length() {
	int size =0;
	Node current = head;
	while(current != null) {
		size++;
		current = current.getNext();
	}
	return size;
}

	
}
