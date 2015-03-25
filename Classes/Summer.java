import java.math.*;

public class Summer
{
    public String findSum (String LL, String NN)
    {
        BigDecimal L = new BigDecimal (LL);
        BigDecimal N = new BigDecimal (NN);
        N = N.setScale (0);
        if (N.compareTo (new BigDecimal ("2")) < 0)
        {
            System.out.println ("N TOO LOW");
            return null;
        }
        
        BigDecimal returnVal = new BigDecimal ("0");
        for (BigDecimal i = new BigDecimal ("2"); i.compareTo (N) <= 0; i = i.add (BigDecimal.ONE))
        {
            returnVal = returnVal.add (BigDecimal.ONE.divide (i.subtract (BigDecimal.ONE),MathContext.DECIMAL128));
        }
        returnVal = returnVal.multiply (L.divide(BigDecimal.ONE.add(BigDecimal.ONE)));
        return returnVal.toString ();
    }
}