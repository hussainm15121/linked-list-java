/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 04/02/2021                                        *
*   Purpose: Implementation of Linked list                  *
*************************************************************/
import java.util.*;
import java.io.Serializable;

public class orderLinkedList implements Serializable
{
   // private static int counter;
    private Node head;
    private Node tail;

    //Default Constructor
    public orderLinkedList()
    {
        Node head, tail = null;
    }
    
      public void insert(Object data)
    {
        Node newNode = new Node(data);
        Node currentData = head;
        int exit=0;
        if(head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            if(currentData != null)
            {
                while (currentData.getNext() != null)
                {
                  currentData = currentData.getNext();
                }
                  currentData.setNext(newNode);
            }
       }
    }
	public void route(orderLinkedList o1)
	{
		Node a = this.head, b = o1.head;
		
        boolean b1 = false;
   	
          Node backup = a;			//a is inventory
       System.out.print("Home");
        while((b != null))			//b is order 
        {
        	if(b.getData().equals(a.getData()))
        	{
        		b1 = true;
        		b=b.getNext();
        		a=a.getNext();
        		System.out.print(" -> "+a.getData());
        	}	
  		if(a.getNext() == null)
  		{
  			a = backup;
  			b=b.getNext();
  		}
  		a=a.getNext();
        }
	if(b1 == false)
        	{
        		System.out.println("No Data Found");
        	}
	System.out.println("");
	}
	public void compare(orderLinkedList o1) 
	{
		Node a = this.head, b = o1.head;
		
        boolean b1 = false;
   	
          Node backup = a;			//a is inventory
        while((b != null))			//b is order 
        {
        	if(b.getData().equals(a.getData()))
        	{
        		System.out.println("Product: "+b.getData());
        		b1 = true;
        		b=b.getNext();
        		a=a.getNext();
        		System.out.println("At Location: "+a.getData());
        	}
        	
  		if(a.getNext() == null)
  		{
  			a = backup;
  			b=b.getNext();
  		}
  		a=a.getNext();
        }
	if(b1 == false)
        	{
        		System.out.println("No Data Found");
        	}
       }
    public void searchInv()
    {
        Node temp;
        temp = head;
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Please enter location to view inventory: ");
        input = sc.nextLine();
        boolean b1 = false;
        
        while(temp != null)
        {
        	if(temp.getData().equals(input))
        	{
       		System.out.println("Store: "+temp.getData());
       		temp = temp.getNext();
            		System.out.println("Product: "+temp.getData());
                    	temp = temp.getNext();
            		System.out.println("Stock: "+temp.getData());
            		System.out.println("\n");
        		b1 = true;
         	}
            		temp = temp.getNext();
        }
        	if(b1 == false)
        	{
        		System.out.println("No Data Found");
        	}
    }
    public void display()
    {
        if(head == null)
        {
            System.out.println("Linked List Not Found");
        }
        Node temp = head;
        while(temp != null)
        {
           	System.out.println(temp.getData());
			temp = temp.getNext();
        }
    }
   // Node Class 
    private class Node
    {
        Node after;  //reference to next node
        Object data;    // data of any type can be loaded
        public Node(Object dataValue)
        {
        	after = null;
            	data = dataValue;
        }
        public Node getNext()
        {
            return after;
        }
        public Object getData()
        {
            return data;
        }
        // Setters
        public void setData(Object dataValue)
        {
            data = dataValue;
        }
        public void setNext(Node nextValue)
        {
            after = nextValue;
        }
    }
}
