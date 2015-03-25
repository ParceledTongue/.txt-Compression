import java.io.*;

public class HuffmanLibrary
{
    public static String readFileAsString (String fileName) throws IOException, EmptyFileException // Create a string from the text content of the file. An exception is thrown if the file is not found.
    {
        BufferedReader br = new BufferedReader (new FileReader (fileName));
        try
        {
            StringBuilder sb = new StringBuilder ();
            String line = br.readLine ();
            if (line == null)
                throw new EmptyFileException ();
            while (line != null) // Create the string line-by-line, adding \n to denote line breaks.
            {
                sb.append (line);
                sb.append ("\n");
                line = br.readLine ();
            }
            return sb.toString ();
        }
        finally
        {
            br.close ();
        }
    }
    
    public static HLinkedList createHList (String fileContents) // Create a linked list from the string. Each unique character is allocated 1 node, and each node stores the frequency of the occurrence of that character in the string.
    {
        HLinkedList list = new HLinkedList ();
        HTreeNode tempNode = null;
        for (int i = 0; i < fileContents.length (); i++) // Iterate through the characters in the string.
        {
            tempNode = list.getHeadNode ();
            while (tempNode.next != null) // Iterate through the nodes already in the list.
            {
                if (fileContents.charAt (i) == tempNode.character) // If the character is found in the list, increase the frequency of the node representing the character.
                {
                    tempNode.frequency ++;
                    break; // There is no need to check the rest of the list.
                }
                tempNode = tempNode.next;
            }
            if (tempNode.next == null) // If the end of the list has been reached...
            {
                if (fileContents.charAt (i) == tempNode.character) // One final check, as the loop breaks before checking the last character
                    tempNode.frequency ++;
                else // Otherwise, the character has not been found in the list, so add its node.
                {
                    tempNode.next = new HTreeNode ();
                    tempNode.next.character = fileContents.charAt (i);
                    tempNode.next.frequency ++;
                }
            }
        }
        return list;
    }
    
    public static HLinkedList getSortedLinkedList (HLinkedList oldList) // Return a list containing the elements of oldList sorted with respect to frequency.
    {
        HLinkedList sortedList = new HLinkedList ();
        HTreeNode tempNodeOld = oldList.getHeadNode ().next; // Start with the first non-head node (may be null).
        while (tempNodeOld != null) // Iterate through the old list, adding each element in its proper position to the sorted list.
        {
            sortedList.insertSorted (tempNodeOld);
            tempNodeOld = tempNodeOld.next;
        }
        return sortedList;
    }
    
    public static HTree createHuffmanTree (HLinkedList list) // Return the Huffman tree created from the list.
    {
        HLinkedList sortedList = getSortedLinkedList (list);
        return new HTree (sortedList);
    }
    
    public static void updateCodeValues (HTree tree) // Update the binary codes of all leaf nodes.
    {
        try 
        {
            codeRecursor (tree, "");
        }
        catch (InvalidTreeException e)
        {
            System.out.println ("The tree passed in was not a valid Huffman tree.");
        }
    }
    
    private static void codeRecursor (HTree tree, String code) throws InvalidTreeException, NullPointerException // Iterate through the tree, track the path via the code variable, and when a leaf node is found, assign it the appropriate code.
    {
        if (tree.root.left != null)
        {
            if (tree.root.right == null) // If a node has only one child, the tree passed in is not a valid Huffman tree.
                throw new InvalidTreeException ();
            codeRecursor (new HTree (tree.root.left), code + "0");
            codeRecursor (new HTree (tree.root.right), code + "1");
        }
        else
            tree.root.code = code;
    }
    
    private static String buildBinaryString (String rawString, HLinkedList codes) throws NullPointerException // Return a binary representation of rawString, replacing characters witht their binary equivalents as denoted by the code parameter.
    {
        String binaryString = "";
        for (int i = 0; i < rawString.length (); i++)
            binaryString += codes.find (rawString.charAt (i)).code;
        return binaryString;
    }
    
    public static void Huffman_coder (String input_file, String output_file) // Master method. Uses Huffman encoding to compress input_file (a plaintext file) into output_file (a binary file).
    {
        try
        {
            String characterString = readFileAsString (input_file);
            HLinkedList charList = createHList (characterString);
            HTree huffmanTree = createHuffmanTree (charList);
            updateCodeValues (huffmanTree);
            HLinkedList codeList = huffmanTree.createList ();
            String binaryString = buildBinaryString (characterString, codeList);
            BinaryFileWriter writer = new BinaryFileWriter ();
            writer.createBinaryFile (binaryString, output_file);
        }
        catch (IOException e)
        {
            System.out.println ("Error: The file could not be found.");
        }
        catch (EmptyFileException e)
        {
            System.out.println ("Error: The file contains no data.");
        }
        catch (Exception e) // This catch should never be reached. It is for debugging purposes only.
        {
            System.out.println ("An unexplained error occurred.");
        }
    }
}
