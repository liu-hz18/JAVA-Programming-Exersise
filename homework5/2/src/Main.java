import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.Comparator;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.net.MalformedURLException;
import java.io.IOException;


class URLCounter {
    private List<String> lines = new ArrayList<String>();
    private LinkedHashMap<String, Integer> mCounter = new LinkedHashMap<>();
    public URLCounter(final String url) {
        try {
            URL.setURLStreamHandlerFactory(TUProxy::new);
            URL mURL = new URL(url);
            URLConnection uc = mURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            String inputLine;
            while( (inputLine = in.readLine()) != null ){
                lines.add(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void countWord(final String word) {
        int count = 0;
        for (String line: lines) {
            int beginIndex = 0;
            while((beginIndex = line.indexOf(word, beginIndex)) != -1) {
                count++;
                beginIndex++;
            }
        }
        mCounter.put(word, count);
    }

    public void printWords() {
        List< Entry<String, Integer> > list = new ArrayList<>(mCounter.entrySet());
        Collections.sort(list, new Comparator< Entry<String, Integer> >() {
            public int compare(Entry<String, Integer> left, Entry<String, Integer> right) {
                return right.getValue().compareTo(left.getValue());
            }  
        });
        for (Entry<String, Integer> entry: list) {
            System.out.println(entry.getKey() + " " + Integer.toString(entry.getValue()));
        }
    }
}


public class Main {
    public static void main(String[] args) {
        final String url;
        try (final Scanner cin = new Scanner(System.in)) {
            url = cin.nextLine();
            URLCounter counter = new URLCounter(url);
            while( cin.hasNextLine() ) {
                counter.countWord(cin.nextLine());
            }
            counter.printWords();
        }
    }
}
