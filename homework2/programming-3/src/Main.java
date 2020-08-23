import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;

class Student extends NaiveStaff {
    private final String mName;
    private final String mSex;
    private final int mAge;
    private final String mSignYear;

    public Student(final int number, final String name, final String sex, final int age, final String signyear) {
        super("S", number);
        this.mName = name;
        this.mSex = sex;
        this.mAge = age;
        this.mSignYear = signyear;
    }

    public Student(final int number) {
        super("S", number);
        this.mName = "";
        this.mSex = "";
        this.mAge = 0;
        this.mSignYear = "";
    }

    @Override
    public final String toString() {
        return "Student " + getNumber() + " " + mName + " " + mSex + " " + mAge + " " + mSignYear;
    }
}

class Teacher extends NaiveStaff {
    private final String mSex;
    private final int mAge;
    private final String mMajor;

    public Teacher(final int number, final String sex, final int age, final String major) {
        super("T", number);
        this.mSex = sex;
        this.mAge = age;
        this.mMajor = major;
    }

    public Teacher(final int number) {
        super("T", number);
        this.mSex = "";
        this.mAge = 0;
        this.mMajor = "";
    }

    @Override
    public final String toString() {
        return "Teacher " + getNumber() + " " + mSex + " " + mAge + " " + mMajor;
    }
}


class StaffManager {
    private static HashMap<BaseStaff, NaiveStaff> hmap = new HashMap<BaseStaff, NaiveStaff>();

    public static void login(final String info) {
        String[] splitInfos = info.split(" ");
        if (splitInfos[0].equals("Teacher")) {
            NaiveStaff staff = new Teacher(
                Integer.parseInt(splitInfos[1]),
                splitInfos[2],
                Integer.parseInt(splitInfos[3]),
                splitInfos[4]
            );
            hmap.put(staff, staff);
        } else {
            NaiveStaff staff = new Student(
                Integer.parseInt(splitInfos[1]),
                splitInfos[2],
                splitInfos[3],
                Integer.parseInt(splitInfos[4]),
                splitInfos[5]
            );
            hmap.put(staff, staff);
        }
    }

    public static void punch(final String info) {
        String[] splitInfos = info.split(" ");
        if (splitInfos[0].equals("T")) {
            hmap.get(new Teacher(Integer.parseInt(splitInfos[1]))).punch();
        } else {
            hmap.get(new Student(Integer.parseInt(splitInfos[1]))).punch();
        }
    }

    public static final BaseStaff maxPunchStuff() {
        BaseStaff maxPunchStuff = null;
        int maxCounter = -1;
        for (Entry<BaseStaff, NaiveStaff> entry: hmap.entrySet()) {
            if (maxCounter < entry.getValue().punchCounter()) {
                maxPunchStuff = entry.getKey();
                maxCounter = entry.getValue().punchCounter();
            }
        }
        return maxPunchStuff;
    }
}

class Main {
    public static void main(String[] args) {
        try (final Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int m = cin.nextInt();
            cin.nextLine();
            for (int i = 0; i < n; i++) {
                StaffManager.login(cin.nextLine());
            }
            for (int i = 0; i < m; i++) {
                StaffManager.punch(cin.nextLine());
            }
        }
        System.out.println(StaffManager.maxPunchStuff());
    }
}
