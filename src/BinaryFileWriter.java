import java.io.File;
import java.io.FileOutputStream;
public class BinaryFileWriter
{
    private static void writeBinaryFile(String ﬁleName,byte[] b) throws Exception // Write the byte sequence b to a new raw file fileName.
    {
        FileOutputStream f = new FileOutputStream (new File(ﬁleName));
        f.write(b);
        f.close();
    }

    public static void createBinaryFile(String seq, String ﬁleName) throws Exception // Save the binary string seq as a new raw binary file fileName.
    {
        writeBinaryFile(ﬁleName,toByteSequence(seq));
    }

    private static byte[] toByteSequence(String data) throws Exception // Return a byte sequence corresponding to the binary string data.
    {
        int j=-1; // the index of the byte currently being modified
        int byteArrSize = data.length()/8; // Set the array size. Each character in the string represents a bit, or 1/8 of a byte.
        if (data.length()%8 != 0) // If there are leftover bits, accomodate them with an extra byte in the sequence.
            byteArrSize++;
        byte[] byteSeq = new byte[byteArrSize];
        for(int i=0;i< data.length();i++) // Iterate through the characters in the string.
        {
            if (i%8 == 0) // If a byte has been filled up, move on to the next byte.
            {
                j++;
                byteSeq[j] = 0x00;
            }
            byte tmp; // a temporary byte to store the bit representation of the character
            if (data.charAt(i) == '1')
                tmp = 0x01;
            else if (data.charAt(i) == '0')
                tmp = 0x00;
            else 
                throw new Exception ("error in format");
            byteSeq[j] = (byte) (tmp | ( byteSeq[j] << 1)); // Add the byte tmp to the current bit.
        }
        return byteSeq;
    }
}