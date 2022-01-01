import java.util.*;
public class MyLinkedListQueue
{
   class Node 
   {public Object data; public Node next;}
       private Node first;
       public MyLinkedListQueue()
       {   first = null;}
       public Object peek()
       {
           if(first == null) {throw new NoSuchElementException();}
           return first.data;
       }
       
       public void enQueue(Object element)
       {
           Node newNode = new Node();
           newNode.data = element;
           newNode.next = first;
           first = newNode;
       }
       
       public boolean isEmpty()
       {
           return(first == null);
       }
       
       public Object deQueue()
       {
       if (first == null) 
       {
           throw new NoSuchElementException(); 
       }
       if (first.next == null) //list has 1 node
       {
           first = null; //Mark list as "empty"
       }
       else
       {
           Node ptr, ptr1; //ptr1 always points to the Node before the one pointed to by ptr
           
           ptr1 = first; //ptr1 points to the first node
           ptr = first.next; //ptr points to node following ptr1
           
           while(ptr.next != null)
           {
               ptr1 = ptr;  //Move to next pair of node
               ptr = ptr.next;
           }
           ptr.next = null;
       }
       Object element = first.data;
       first = first.next;
       return element;
       }
       
       public static void main (String[] arg)
       {
           MyLinkedListQueue test = new MyLinkedListQueue();
           test.enQueue("John");
           test.enQueue("Tim");
           test.enQueue("Leondre");
           
           System.out.println(test.deQueue());
           System.out.println(test.peek());
           
       }
       
}