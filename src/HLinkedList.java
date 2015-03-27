public class HLinkedList
{
    private HTreeNode head; // contains no data
    
    public HLinkedList ()
    {
        head = new HTreeNode ();
    }
    
    public HTreeNode getHeadNode ()
    {
        return head;
    }
    
    public void insertIntoPosition (HTreeNode node, int position) throws NullPointerException // Insert node into the position specified (node directly following head is position = 0). Indexes larger than the list size will result in the node being placed at the end.
    {
        HTreeNode previousNode = head; // the node after which the new node is to be inserted
        for (int i = 0; i < position; i++) // Push previousNode forward until it has reached its proper place or the end of the list is reached.
        {
            if (previousNode.next != null)
                previousNode = previousNode.next;
            else 
                i = position;
        }
        HTreeNode nextNode = previousNode.next; // the node before which the new node is to be inserted (may be null)
        previousNode.next = node;
        node.next = nextNode;
    }
    
    public void insertSorted (HTreeNode node) throws NullPointerException // Insert a duplicate of node into the list so that its frequency is greater than all preceeding nodes and less than or equal to all proceeding nodes.
    {
        HTreeNode toInsert = new HTreeNode (node.character, node.frequency, node.code); // duplicate of node passed in
        toInsert.left = node.left;
        toInsert.right = node.right;
        HTreeNode previousNode = head;
        while (previousNode.next != null)
        {
            if (previousNode.next.frequency < toInsert.frequency)
                previousNode = previousNode.next;
            else
                break;
        }
        HTreeNode nextNode = previousNode.next;
        previousNode.next = toInsert;
        toInsert.next = nextNode;
    }
    
    public void insertSortedBackwards (HTreeNode node) throws NullPointerException // Insert a duplicate of node into the list so that its frequency is less than all preceeding nodes and greater than or equal to all proceeding nodes.
    {
        HTreeNode toInsert = new HTreeNode (node.character, node.frequency, node.code); // duplicate of node passed in
        toInsert.left = node.left;
        toInsert.right = node.right;
        HTreeNode previousNode = head;
        while (previousNode.next != null)
        {
            if (previousNode.next.frequency > toInsert.frequency)
                previousNode = previousNode.next;
            else
                break;
        }
        HTreeNode nextNode = previousNode.next;
        previousNode.next = toInsert;
        toInsert.next = nextNode;
    }
    
    public HTreeNode find (char value) // Return the first node in the list containing the character value. If the character is not found, return null.
    {
        HTreeNode tempNode = head.next;
        while (tempNode != null)
        {
            if (tempNode.character == value) // If the character is found, stop pushing tempNode forward.
                break;
            tempNode = tempNode.next;
        }
        return tempNode;
    }
    
    public String toString ()
    {
        String returnString = "";
        HTreeNode tempNode = head.next;
        while (tempNode != null)
        {
            returnString += tempNode.character;
            if (tempNode.next != null)
                returnString += ",";
            tempNode = tempNode.next;
        }
        return returnString;
    }
    
    public String printAllCodes ()
    {
        String returnString = "";
        HTreeNode tempNode = head.next;
        while (tempNode != null)
        {
            returnString += tempNode.character;
            returnString += "["+tempNode.code+"]";
            returnString += "(" + tempNode.frequency + ")";
            if (tempNode.next != null)
                returnString += "\n";
            tempNode = tempNode.next;
        }
        return returnString;
    }
}
