/**
 * @author jacob.bettenhausen 11/18/2024
 */
public class BitShiftDemo
{
    public static void main(String[] args)
    {
        //Bitwise operations example
        int a = 5;
        int b = 10;
        System.out.println("A: 0" + Integer.toBinaryString(a));
        System.out.println("B: " + Integer.toBinaryString(b));
        System.out.println("A | B: " + Integer.toBinaryString(a | b));
        System.out.println("A & B: " + Integer.toBinaryString(a & b));
        System.out.println("A << 1: " + Integer.toBinaryString(a << 1));
        System.out.println("B >> 1: 0" + Integer.toBinaryString(b >> 1));
        System.out.println();

        //store 3 numbers in 24 bits
        getRGBFromBinary(getBinaryFromRGB(255, 255, 255));

        //store 4 numbers in 24 bits
        getABCDFromBinary(getBinaryFromABCD(63, 63, 63, 63));

        //uneven bit storage example
        get6_18SplitFromBinary(getBinaryFrom6_18Split(1,1));

    }

    /**
     * Retrieve 3 8-bit integers from a 24-bit integer.
     */
    private static void getRGBFromBinary(int input)
    {
        int r = input >> 16 & 255;
        int g = input >> 8 & 255;
        int b = input & 255;

        System.out.println("R: " + r + ", G: " + g + ", B: " + b);
        System.out.println();
    }

    /**
     * Back-calculate the 24-bit integer input from 3 8-bit integers.
     */
    private static int getBinaryFromRGB(int r, int g, int b)
    {
        System.out.println(addFloatingZeros((r << 16), 24, 8) + " (" + (r << 16) + ")");
        System.out.println(addFloatingZeros((g << 8), 24, 8) + " (" + (g << 8) + ")");
        System.out.println(addFloatingZeros((b), 24, 8) + " (" + (b) + ")");
        System.out.println("bitwise OR operation result:");
        int result = r << 16 | g << 8 | b;
        System.out.println(addFloatingZeros(result, 24, 8) + " (" + result + ")");
        return result;
    }

    /**
     * Fill in empty space and format for easy comparison.
     * @param shiftedInt integer to format
     * @param lim maximum bit length of the set
     * @param bitSize bit length of each subset
     * @return formatted binary number String
     */
    private static String addFloatingZeros(int shiftedInt, int lim, int bitSize)
    {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(shiftedInt));
        if (sb.length() <= lim)
        {
            while (sb.length() < lim)
            {
                sb.insert(0, "#");
            }
            sb.insert(0, "[");
            int n;
            String delim = "][";
            for (int i = bitSize; i < lim; i+= bitSize)
            {
                n = i / bitSize;
                sb.insert(n * bitSize + 1 + delim.length() * (n - 1), delim);
            }
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * Retrieve 4 6-bit integers from a 24-bit integer.
     */
    private static void getABCDFromBinary(int input)
    {
        int a = input >> 18 & 63;
        int b = input >> 12 & 63;
        int c = input >> 6 & 63;
        int d = input & 63;

        System.out.println("A: " + a + ", B: " + b + ", C: " + c + ", D: " + d);
        System.out.println();
    }

    /**
     * Back-calculate the 24-bit integer input from 4 6-bit integers.
     */
    private static int getBinaryFromABCD(int a, int b, int c, int d)
    {
        System.out.println(addFloatingZeros((a << 18), 24, 6) + " (" + (a << 18) + ")");
        System.out.println(addFloatingZeros((b << 12), 24, 6) + " (" + (b << 12) + ")");
        System.out.println(addFloatingZeros((c << 6), 24, 6) + " (" + (c << 6) + ")");
        System.out.println(addFloatingZeros((d), 24, 6) + " (" + (b) + ")");
        System.out.println("bitwise OR operation result:");
        int result = a << 18 | b << 12 | c << 6 | d;
        System.out.println(addFloatingZeros(result, 24, 6) + " (" + result + ")");

        return result;
    }

    /**
     * Back-calculate the 24-bit integer input from a 6 and 18 bit integer.
     */
    private static int getBinaryFrom6_18Split(int a, int b)
    {
        System.out.println(formatABUnevenSplit(a << 18, 18, 24) + " (" + (a << 18) + ")");
        System.out.println(formatABUnevenSplit(b, 18, 24) + " (" + (b) + ")");
        System.out.println("bitwise OR operation result:");
        int result = a << 18 | b;
        System.out.println(formatABUnevenSplit(result, 18, 24) + " (" + result + ")");

        return result;
    }

    /**
     * Split a 24-bit integer input into a 6 and 18 bit integer.
     */
    private static void get6_18SplitFromBinary(int input)
    {
        int x = input >> 18 & 63;
        int y = input & 262143; // 2^18-1

        System.out.println("X: " + x + ", Y: " + y);
        System.out.println();
    }

    /**
     * Custom formatting for 6-18 split.
     */
    private static String formatABUnevenSplit(int shiftedInt, int aSize, int lim)
    {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(shiftedInt));
        if (sb.length() <= lim)
        {
            while (sb.length() < lim)
            {
                sb.insert(0, "#");
            }
            sb.insert(0, "[");
            sb.insert(lim - aSize + 1, "][");
            sb.append("]");
        }
        return sb.toString();
    }

}
