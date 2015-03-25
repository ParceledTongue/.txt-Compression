public class HTree
{
    HTreeNode root;
    
    public HTree ()
    {
        root = null;
    }
    
    public HTree (HTreeNode myRoot)
    {
        root = myRoot;
    }
    
    public HTree (HLinkedList myList)
    {
        listToTree (myList);
        root = myList.getHeadNode ().next;
    }
    
    public HTreeNode getRootNode ()
    {
        return root;
    }
    
    private void listToTree (HLinkedList list) // Transform the passed in list to a tree. The original list is not preserved. 
    {
        while (list.getHeadNode ().next != null && list.getHeadNode ().next.next != null) // Repeat until there are two or fewer nodes on the list.
        {
            HTreeNode newParent = new HTreeNode (list.getHeadNode ().next.frequency + list.getHeadNode ().next.next.frequency); // Combine the first two elements in the list into a single
            newParent.left = list.getHeadNode ().next;                                                                          // node and sort the new node into the list with respect
            newParent.right = list.getHeadNode ().next.next;                                                                    // to its cumulative frequency. To generate an ideal Huffman
            list.getHeadNode ().next = list.getHeadNode ().next.next.next;                                                      // tree, the list passed in must already be sorted from 
            newParent.left.next = null;                                                                                         // lowest to highest.
            newParent.right.next = null;
            list.insertSorted (newParent);
        }
    }
    
    public HLinkedList createList () throws InvalidTreeException // Create a singly linked list from the leaves of this tree, sorted with respect to frequency (highest first).
    {
        HLinkedList list = new HLinkedList ();
        listRecurse (root, list);
        return list;
    }
    
    private void listRecurse (HTreeNode node, HLinkedList list) throws InvalidTreeException // Iterate through the tree, and when a leaf node is found, add it to the list in the appropriate position.
    {
        if (node.left == null)
            list.insertSortedBackwards (new HTreeNode (node.character, node.frequency, node.code));
        else
        {
            if (node.right == null) // If a node has only one child, the tree passed in is not a valid Huffman tree.
                throw new InvalidTreeException ();
            listRecurse (node.left, list);
            listRecurse (node.right, list);
        }
    }
}