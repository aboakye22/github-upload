import java.util.Scanner;
/**
*This class will ask the user to input names into a bag and ask the user
*to delete a name and whether or not the name is in the bag.
*@author Angel Boakye
*@version September 6, 2021
*/

public class Name
{
   Node head; //start of Bag
   
   //Class for each Node.
   // This inner class is made static so that the main method can access it.
   static class Node 
   {
       String name;
       Node next;
       // Constructor
       Node(String d) 
       {
           name = d;
           next = null;
       }
   }

   /**
   *This method inserts a new node
   *@param Names
   *@return bag of names
   */
   public static Name insert(Name bag, String name) 
   {
       //Creates a new node with given name
       Node node = new Node(name);
       node.next = null;

       // If the Bag is empty, then make the new node as head
       if (bag.head == null) 
       {
           bag.head = node;
       } 
       else 
       {
           //Else traverse till the last node and insert the new_node there
           Node last = bag.head;
           while (last.next != null) 
           {
               last = last.next;
           }

           // Insert the new_node at last node
           last.next = node;
       }

       return bag;
   }

   /**
   *This method prints the Bag.
   *@param The Name bag.
   */
   public static void printList(Name bag)
   {
       Node currNode = bag.head;

       System.out.print("Bag: ");

       // Traverse through the Bag
       while (currNode != null) 
       {
           // Print the name at current node
           System.out.print(currNode.name + " ");

           // Go to next node
           currNode = currNode.next;
       }
       
       System.out.println();
   }

   /**
   *This method finds a node.
   *@param bag of names
   *@return name entered if found
   */
   public static boolean findName(Name bag, String name)
   {
       Node currNode = bag.head;
   
       // Traverse through the Bag
       while (currNode != null) 
       { 
           if(name.equals(currNode.name)) return true;
           // Go to next node
           currNode = currNode.next;
       }
      
      return false;
   }

   /**
   *This method finds a node
   *@param bag of names
   *@return bag of names without the now deleted name
   */
   public static Name delete(Name bag, String name)
   {
       //if name wants to remove is head
       Node currNode = bag.head;
       if(name.equals(currNode.name))
       {
           bag.head = currNode.next;
           return bag;
       }

       // Traverse through the Bag
       while (currNode.next != null) 
       {
           if(name.equals(currNode.next.name))
           { 
               currNode.next = currNode.next.next;
               return bag;
           }
           
           // Go to next node
           currNode = currNode.next;
       }
       
       return bag;
   }
   
   
   public static void main(String[] args)
   {
       //scanner for input
       Scanner scanner = new Scanner(System.in);
       /* Start with the empty bag. */
       Name bag = new Name();
       System.out.print("Enter how many names want to insert : ");
       int size = scanner.nextInt();

       // ******INSERTION******
       System.out.println("Enter names");
       while(size >= 0) 
       {
           String name = scanner.nextLine();
           insert(bag, name);
           size--;
       }
       
       // Print the Bag
       printList(bag);

       //find name
       System.out.print("Enter a name to find : ");
       String name = scanner.nextLine();
       if(findName(bag, name)) System.out.println("Found");
       else System.out.println("Not Found");

       //delete name
       System.out.print("Enter a name to delete : ");
       name = scanner.nextLine();
       delete(bag, name);

       printList(bag);

   }
}