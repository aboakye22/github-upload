import java.util.*;

/**
*This program will add a dummy node to the list.
*@author Angel Boakye
*@version 10/17/2021
*/

public class LList1<T>
{
   private Node firstNode; //References to first node of chain
   private int numberOfEntries;
   
   public LList1() //constructor
   {
       initializeDataFields();
   }
   
   public void clear()
   {
       initializeDataFields();
   }
   
   /**
   *This method will add an entry to the end of the list if the list is not empty, if it is then will add a dummy node
   *to the beginniing of the list.
   *@param a generic element data type
   */
   public void add(T newEntry)
   {
       Node newNode = new Node(newEntry);
       
       if (isEmpty())
       {
           firstNode = new Node(null, newNode);
       }
       
       else
       {
       
           Node lastNode = getNodeAt(numberOfEntries);
           lastNode.setNextNode(newNode); // Make last node reference new node
       }
       
       numberOfEntries++;
       }
   
   /**
   *This method will add a node to any position within bounds of the list. If not within
   *bounds, an exception is thrown.
   *@param New position of node and generic data type
   */    
   public void add(int newPosition, T newEntry)
   {
       if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
       {
           Node newNode = new Node(newEntry);
               
           if (newPosition == 1) // Case 1
           {
               newNode.setNextNode(firstNode);
               firstNode = newNode;
           }
               
           else // Case 2: list is not empty
           {
               Node nodeBefore = getNodeAt(newPosition - 1);
               Node nodeAfter = nodeBefore.getNextNode();
               newNode.setNextNode(nodeAfter);
               nodeBefore.setNextNode(newNode);
           }
               
           numberOfEntries++;
       }
           
       else
       {
           throw new IndexOutOfBoundsException("Illegal position given to add operation.");
       }    
   }
   
   /**
   *This method will return and remove the selected node.
   *@param position needed to be removed
   *@return data point that is being removed/deleted.
   */    
   public T remove(int givenPosition)
   {
       T result = null; //Return value
           
       if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
       {
           assert !isEmpty();
               
           if (givenPosition == 1) //If Remove is the first entry
           {
               result = firstNode.getData(); //This saves the entry to be removed
               firstNode = firstNode.getNextNode(); //Removes the entry
           }
               
           else //If it is not first entry
           {
               Node nodeBefore = getNodeAt(givenPosition - 1);
               Node nodeToRemove = nodeBefore.getNextNode();
               result = nodeToRemove.getData(); // This saves the entry to be removed
               Node nodeAfter = nodeToRemove.getNextNode();
               nodeBefore.setNextNode(nodeAfter);
           }
               
           numberOfEntries--;
           return result; 
       }
           
       else
       {
           throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
       }
   }
   
   /**
   *This method will replace a given node with a new entry.
   *@param given position and generic data type.
   */
   public T replace(int givenPosition, T newEntry)
   {
       if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
       {
           assert !isEmpty();
           Node desiredNode = getNodeAt(givenPosition);
           T originalEntry = desiredNode.getData();
           desiredNode.setData(newEntry);
           return originalEntry;
       }
       
       else
       {
           throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
       }
   }
   
   /**
   *This method will act as a peek and return the generic data value contained in that position.
   *@param position of wanted node.
   */
   public T getEntry(int givenPosition)
   {
       if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
       {
           assert !isEmpty();
           return getNodeAt(givenPosition).getData();
       }
       
       else
       {
           throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
       }
   }
   
   /**
   *This method will return the data type contained in the current node and increase the size.
   *@return generic data type at specific node
   */
   public T[] toArray()
   {
       @SuppressWarnings("unchecked")
       T[] result = (T[])new Object[numberOfEntries];
       int index = 0;
       Node currentNode = firstNode;
       while ((index < numberOfEntries) && (currentNode != null))
       {
           result[index] = currentNode.getData();
           currentNode = currentNode.getNextNode();
           index++;
       }
       
       return result;
   }

  
   /**
   *This method will check to see each node to see if it contains an entry.
   *@param a generic data type
   *@return true/false of if the entry that is in the node what the program is looking for
   */
   public boolean contains(T anEntry)
   {
       boolean found = false;
       Node currentNode = firstNode;
       while (!found && (currentNode != null))
       {
           if (anEntry.equals(currentNode.getData()))
           {
               found = true;
           }

           else
           {
               currentNode = currentNode.getNextNode();
           }
       }

       return found;
   }
   
   /**
   This method will return the length of the list
   @return how long the list is.
   */
   public int getLength()
   {
       return numberOfEntries;
   }
   
   /**
   This method will check to see if the list is empty.
   @return True or false of if the list is empty.
   */
   public boolean isEmpty()
   {
       boolean result;
       if (numberOfEntries == 0) 
       {
           assert firstNode == null;
           result = true;
       }
       
       else
       {
           assert firstNode != null;
           result = false;
       }
       
       return result;
   }

   
   /**
   This method will intialize the data field to indicate an empty list.
   */
   private void initializeDataFields()
   {
       firstNode = null;
       numberOfEntries = 0;
   }

   
   /**
   This method will return the data field of a node at a position
   @param given position of wanted node
   @return the data in specified node
   */
   private Node getNodeAt(int givenPosition)
   {
       assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
       Node currentNode = firstNode;
       for (int counter = 1; counter < givenPosition; counter++)
       {
           currentNode = currentNode.getNextNode();
       }

       assert currentNode != null;
       return currentNode;
   }
   
   /**
   This method sets up the nodes to be used for the list. 
   */
   private class Node
   {
       private T data;
       private Node next;
       
       private Node(T dataPortion)//constructor
       {
           data = dataPortion;
           next = null;
       }
       
       /**
       This method will set up the node so it will have the data and reference number
       *to the next node.
       *@param generic data type and reference to next node.
       */
       private Node(T dataPortion, Node nextNode)
       {
           data = dataPortion;
           next = nextNode;
       }
       
       /**
       This method will retrieve the generic data type.
       @return generic data type
       */
       private T getData()
       {
           return data;
       } 
       
       /**
       This method will set a new generic data type for a node.
       @param new generic data type
       */
       private void setData(T newData)
       {
           data = newData;
       }
       
       /**
       This method will retrieve the next node
       @return next node
       */
       private Node getNextNode()
       {
           return next;
       }
       
       /**
       This method will set the next node to be in the list
       @param next node in the list.
       */
       private void setNextNode(Node nextNode)
       {
           next = nextNode;
       }
   }
   
   public static void main(String[] args)
   {
        LList1<String> myList = new LList1<String>();
        
        System.out.println("List should be empty; isEmpty returns " + myList.isEmpty() + ".");
        myList.add(1,"12");
        System.out.println("List should be empty; isEmpty returns " + myList.isEmpty() + ".");
   }

} 

/**Output
List should be empty; isEmpty returns true.
 List should be empty; isEmpty returns false.
*/