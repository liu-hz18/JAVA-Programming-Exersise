import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Scanner;

class Operation {
    private TreeMap<Integer, Integer> tmap = new TreeMap<>();
    private long total = 0;
    
    private void update(Entry<Integer, Integer> entry) {
        int value = entry.getKey();
        int count = entry.getValue();
        total -= value;
        if (count > 0) {
            tmap.put(value, count-1);
        } else {
            tmap.remove(value);
        }
    }

    private void addMoney(int value) {
        if (tmap.containsKey(value)){
            tmap.put(value, tmap.get(value)+1);
        } else {
            tmap.put(value, 1);
        }
        total += value;
    }

    private void buyInAdvantage(int value) throws NullPointerException {
        Entry<Integer, Integer> e = tmap.floorEntry(value);
        if (e == null) {
            throw new NullPointerException();
        }
        update(e);
    }

    private void buyInDisAdvantage(int value) throws NullPointerException {
        Entry<Integer, Integer> e = tmap.ceilingEntry(value);
        if (e == null) {
            throw new NullPointerException();
        }
        update(e);
    }

    public long totalMoney() {
        return total;
    }

    public void action (final String line) throws NullPointerException {
        String[] info = line.split(" ");
        switch (info[0]) {
            case "1":
                addMoney(Integer.parseInt(info[1]));
                break;
            case "2":
                buyInAdvantage(Integer.parseInt(info[1]));
                break;
            default:
                buyInDisAdvantage(Integer.parseInt(info[1]));
                break;
        }
    }
}


class Main {
    public static void main(String[] args) {
        Operation op = new Operation();
        try (final Scanner cin = new Scanner(System.in)) {
            int n = Integer.parseInt(cin.nextLine());
            int count = 0;
            while( count < n && cin.hasNextLine() ) {
                op.action(cin.nextLine());
                count ++;
            }
            System.out.println(op.totalMoney());
        } catch (NullPointerException e) {
            System.out.println("-1");
        }
    }
}
