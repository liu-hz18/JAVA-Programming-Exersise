import java.util.Scanner;

class VirtualComputer {
    Integer[] mem = null;

    private void add(final int a, final int b, final int c) throws NullPointerException, ArrayIndexOutOfBoundsException {
        int optA = mem[a];
        int optB = mem[b];
        mem[c] = optA + optB;
    }

    private void sub(final int a, final int b, final int c) throws NullPointerException, ArrayIndexOutOfBoundsException {
        int optA = mem[a];
        int optB = mem[b];
        mem[c] = optA - optB;
    }

    private void mul(final int a, final int b, final int c) throws NullPointerException, ArrayIndexOutOfBoundsException {
        int optA = mem[a];
        int optB = mem[b];
        mem[c] = optA * optB;
    }

    private void div(final int a, final int b, final int c) throws NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException {
        int optA = mem[a];
        int optB = mem[b];
        int result = optA / optB;
        mem[c] = result;
    }

    private void set(final int a, final int b, final int num) throws NullPointerException, ArrayIndexOutOfBoundsException {
        for (int i = a; i < b; i++) {
            mem[i] = num;
        }
    }

    private void get(final int n) throws ArrayIndexOutOfBoundsException {
        System.out.println(mem[n]);
    }

    public VirtualComputer(final int n) {
        mem = new Integer[n];
    }

    public void operate(final String instruction) throws NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException {
        String[] infos = instruction.split(" ");
        switch (infos[0]) {
            case "=":
                this.set(Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                break;
            case "+":
                this.add(Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                break;
            case "-":
                this.sub(Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                break;
            case "*":
                this.mul(Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                break;
            case "/":
                this.div(Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
                break;
            default:
                this.get(Integer.parseInt(infos[1]));
                break;
        }
    }
}

class Main {
    public static void main(String[] args) {
        try (final Scanner cin = new Scanner(System.in) ) {
            int n = cin.nextInt();
            cin.nextLine();
            VirtualComputer vc = new VirtualComputer(n);
            while( cin.hasNextLine() ) {
                try {
                    vc.operate(cin.nextLine());
                } catch (NullPointerException e) {
                    System.out.println("Null Number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Illegal Address");
                } catch (ArithmeticException e) {
                    System.out.println("Divided By Zero");
                }
            }
        }
    }
}
