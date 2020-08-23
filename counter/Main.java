import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;


class MutableInteger {
    private Integer mValue;
    MutableInteger(int value) { mValue = value; }
    public final int get() { return this.mValue; }
    public final void set (int value) { mValue = value; }
    @Override
    public String toString() { return Integer.toString(mValue); }
    public int compareTo (MutableInteger other) { return mValue.compareTo(other.mValue); }
}


class Counter<T extends Object> {
    private Map<T, MutableInteger> countMap;

    public Counter(boolean keepInsertOrder, boolean keepKeyOrder) {
        assert (keepInsertOrder == true && keepKeyOrder == true);
        if (keepInsertOrder) {
            countMap = new LinkedHashMap<T, MutableInteger>();
        } else if (keepKeyOrder) {
            countMap = new TreeMap<T, MutableInteger>();
        } else {
            countMap = new HashMap<T, MutableInteger>();
        }
    }

    public void add (T t) { //O(logn)
        MutableInteger defaultInteger = new MutableInteger(1);
        MutableInteger hasInteger = countMap.put(t, defaultInteger);
        if (hasInteger != null) {
            defaultInteger.set(hasInteger.get() + 1);
        }
    }

    public void addAll (List<T> aList) {
        aList.forEach(this::add);
    }

    public int get (T t) {
        return countMap.get(t).get();
    }

    public List< Entry<T, MutableInteger> > sortByValue(boolean increase) {
        List< Entry<T, MutableInteger> > list = new ArrayList<>(countMap.entrySet());
        //use Collections.sort() to keep stable
        if (increase) {
            Collections.sort(list, new Comparator< Entry<T, MutableInteger> >() {
                public int compare(Entry<T, MutableInteger> left, Entry<T, MutableInteger> right) {
                    return left.getValue().compareTo(right.getValue());
                }  
            });
        } else {
            Collections.sort(list, new Comparator< Entry<T, MutableInteger> >() {
                public int compare(Entry<T, MutableInteger> left, Entry<T, MutableInteger> right) {
                    return right.getValue().compareTo(left.getValue());
                }  
            });
        }
        return list;
    }
}


public class Main {
    public static void main(String[] args) {
        //String[] arr = {"program", "creek", "program", "creek", "java", "web", "program"};
        //Stream<String> stream = Stream.of(arr).parallel();
        ArrayList<String> arr = new ArrayList<String>(
            Arrays.asList("program", "creek", "program", "creek", "java", "web", "program"));
        Stream<String> stream = arr.parallelStream();
        Map<String, Long> counter = stream.collect(
            Collectors.groupingBy(String::toString, Collectors.counting()));
        System.out.println(counter.get("creek"));

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream()
                                       .filter(string -> !string.isEmpty())
                                       .collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream()
                                           .map( i -> i*i)
                                           .distinct()
                                           .collect(Collectors.toList());

        List<String> strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = strings1.parallelStream()
                             .filter(string -> string.isEmpty())
                             .count();

        String s = "one two three two three three";
        String[] sArr = s.split(" ");
        HashMap<String, MutableInteger> efficientCounter = new HashMap<String, MutableInteger>();
        for (String a: sArr) {
            MutableInteger initValue = new MutableInteger(1);
            MutableInteger oldValue = efficientCounter.put(a, initValue);
            if(oldValue != null){
                initValue.set(oldValue.get() + 1);
            }
        }

    }
}
