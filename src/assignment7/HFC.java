package assignment7;

import java.util.PriorityQueue;
import java.util.Scanner;

class HFC {
    public static int x=0;
    public static StringBuilder save;
    public static int varLength=0;
    public static int totalfixedbytes=0;
    public static int fixedlength=0;
    static StringBuffer value =new StringBuffer("");

    private static String binaryformss(int num)
    {
        StringBuilder buf1=new StringBuilder();
        StringBuilder buf2=new StringBuilder();
        while (num != 0){
            int digit = num % 2;
            buf1.append(digit);
            num = num/2;
        }
        String binary=buf1.reverse().toString();
        int length=binary.length();
        if(length<fixedlength){
            while (fixedlength-length>0){
                buf2.append("0");
                length++;
            }
        }
        String bin=buf2.toString()+binary;
        return bin;
    }

    private static  StringBuffer binaryform(int num) {
        int remainder;
        if (num <=1) {
            String n=String.valueOf(num);
            value.append(n);
        }
        else
        {
            remainder= num %2;
            String m=String.valueOf(remainder);
            value.append(m);
            binaryform(num >>1);
        }
        return value;
    }

    public static HTree build(int[] c) {
        PriorityQueue<HTree> trees = new PriorityQueue<HTree>();
        for (int i = 0; i < c.length; i++)
            if (c[i] > 0)
                trees.offer(new HuffmanLeaf(c[i], (char)i));
        while (trees.size() > 1) {
            HTree a = trees.poll();
            HTree b = trees.poll();
            trees.offer(new HNode(a, b));
        }
        return trees.poll();
    }

    public static void print(HTree tree, StringBuffer s) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf hl = (HuffmanLeaf)tree;
            varLength+=hl.frequency*s.length();
            totalfixedbytes+=hl.frequency*fixedlength;
            System.out.println("  \t "+hl.value + "       \t  " + hl.frequency + "\t \t" + s+"\t                  \t"+binaryformss(x));
            x++;
        }
        else if (tree instanceof HNode) {
            HNode node = (HNode)tree;
            s.append('0');
            print(node.left, s);
            s.deleteCharAt(s.length()-1);
            s.append('1');
            print(node.right, s);
            s.deleteCharAt(s.length()-1);
        }
    }

    public static void main(String[] args) {
        String test;
        for(int i=0;i<3;i++){
            System.out.println("\n\t INPUT TEXTS ");
            System.out.println("\nPlease enter a text of 2-3 lines including char, comma spaces and period:\n");
            Scanner in = new Scanner(System.in);
            test=in.nextLine();
            System.out.println("\n\n\t\tHere is the encoded format.");
            System.out.println("\t\t$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("\n");
            int[] charf = new int[125];
            for (char c : test.toCharArray())
                charf[c]++;
            int count=test.length();
            fixedlength=binaryform(count).length();
            totalfixedbytes=0;
            HTree t = build(charf);
            System.out.println("\tCharacter     Frequency    Variable Coding      Fixed Coding");       System.out.println("\t$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            print(t, new StringBuffer());
            System.out.println("\n\nComparing Both Variable and Fixed Length  \n\n  ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("\nText after Decompressing using variable length coding is : "  +test +"\n");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("\n\nFor variable length the total number of characters are: "+varLength +" Characters");
            System.out.println("\nFor fixed length the total number of characters are:  "+totalfixedbytes +" Characters");
            System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("\nTotal of "+(totalfixedbytes-varLength)+" Characters saved using Huffman Coding");
            System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Text after Decompressing using fixed length coding is : "  +test +"\n");
        }
    }
}

abstract class HTree implements Comparable<HTree> {
    public final int frequency;
    public HTree(int freq)
    {
        frequency = freq;
    }
    public int compareTo(HTree tree) {
        return frequency - tree.frequency;
    }
}

class HuffmanLeaf extends HTree {
    public final char value;
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}

class HNode extends HTree {
    public final HTree left, right;

    public HNode(HTree l, HTree r)
    {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
