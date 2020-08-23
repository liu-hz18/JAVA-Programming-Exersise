import java.lang.Integer;

import java.util.Collections;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Comparator;

import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.PrintStream;
import java.io.FileOutputStream;


class FileCharStatic {
    private static TreeMap<Character, Integer> wordmap = new TreeMap<Character, Integer>();
    private static ArrayList< Entry<Character, Integer> > mList = null;
    private static int mTotal = 0;

    public static void sortByValue() {
        mList = new ArrayList< Entry<Character, Integer> >(wordmap.entrySet());
        Collections.sort(mList, new Comparator< Entry<Character, Integer> > () {
            public int compare(Entry<Character, Integer> left, Entry<Character, Integer> right) {
                return right.getValue().compareTo(left.getValue());
            }
        });
    }

    public static void readFile(final String filename, final String encoding) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
        String line = null;
        while( (line = br.readLine()) != null ) {
            for (int i = 0; i < line.length(); i++) {
                char word = line.charAt(i);
                mTotal += 1;
                if (wordmap.containsKey(word)) {
                    wordmap.put(word, wordmap.get(word) + 1); 
                } else {
                    wordmap.put(word, 1);
                }
            }
        }
        br.close();
    }

    public static void writeStaticToFile(final String filename, final String encoding) throws Exception {
        PrintStream ps = new PrintStream(filename, encoding);
        ps.println(mTotal);
        for (Entry<Character, Integer> e: mList) {
            ps.println(e.getKey() + " " + e.getValue());
        }
        ps.close();
    }
}


class Main {
    public static void main(final String... arg) {
        try (final Scanner cin = new Scanner(System.in)) {
            while( cin.hasNextLine() ) {
                String[] info = cin.nextLine().split(" ");
                FileCharStatic.readFile(info[0], info[1]); 
            }
            FileCharStatic.sortByValue();
                FileCharStatic.writeStaticToFile("statistics.txt", "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
        }
    }
}
