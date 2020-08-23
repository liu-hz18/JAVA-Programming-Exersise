import java.util.LinkedHashSet;
import java.util.Scanner;

class FileStatistic {
    private LinkedHashSet<String> mLineSet = new LinkedHashSet<>();
    public FileStatistic() {}
    public void addLine(final String line) {
        mLineSet.add(line);
    }
    public final void printInfo() {
        System.out.println(mLineSet.size());
        for(String line: mLineSet) {
            System.out.println(line);
        }
    }
}


class Main {
    public static void main(String[] args) {
        try (final Scanner cin = new Scanner(System.in)) {
            FileStatistic fs = new FileStatistic();
            while( cin.hasNextLine() ) {
                fs.addLine(cin.nextLine()); 
            }
            fs.printInfo();
        }
    }
}
