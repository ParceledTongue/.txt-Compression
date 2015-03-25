public class HTreeNode
{
    char character;
    int frequency;
    HTreeNode next;
    HTreeNode left;
    HTreeNode right;
    String code;
    
    public HTreeNode ()
    {
        character = '\u0000';
        frequency = 0;
        next = null;
        left = null;
        right = null;
        code = "";
    }
    
    public HTreeNode (int myFreq)
    {
        character = '\u0000';
        frequency = myFreq;
        next = null;
        left = null;
        right = null;
        code = "";
    }
    
    public HTreeNode (char myChar, int myFreq)
    {
        character = myChar;
        frequency = myFreq;
        next = null;
        left = null;
        right = null;
        code = "";
    }
    
    public HTreeNode (char myChar, int myFreq, String myCode)
    {
        character = myChar;
        frequency = myFreq;
        next = null;
        left = null;
        right = null;
        code = myCode;
    }
    
    public String toString ()
    {
        return "" + character;
    }
}
