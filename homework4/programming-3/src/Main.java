import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

class Node {
    public int state;
    public ArrayList<Integer> neighbors = new ArrayList<Integer>();
    public Node () {
        state = 0;
    }
    public void addNeighbor(int n) {
        neighbors.add(n);
    }
}


class Graph {
    private static final int UNDISCOVERED = 0;
    private static final int DISCOVERED = 1;
    private static final int VISITED = 2;

    private int n;
    private Node[] nodes = null;

    private boolean bfs (int source) {
        int visited = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        nodes[source].state = DISCOVERED;
        queue.add(source);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u: nodes[v].neighbors) {
                if (nodes[u].state == UNDISCOVERED) {
                    nodes[u].state = DISCOVERED;
                    queue.add(u);
                }
            }
            nodes[v].state = VISITED;
            visited++;
        }
        if (visited >= this.n) {
            return true;
        } else {
            return false;
        }
    }

    public Graph(final int n) {
        this.n = n;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }
    }

    public void addEdge (int from, int to) {
        if (from != to) {
            from --;
            to --;
            nodes[from].addNeighbor(to);
            nodes[to].addNeighbor(from);
        }
    }

    public String isConnected () {
        return bfs(0) ? "YES" : "NO";
    } 
}


class Main {
    public static void main(String[] args) {
        try (final Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            if (n == 1) {
                System.out.println("YES");
                return;
            }
            cin.nextLine();
            int count = 1;
            Graph g = new Graph(n);
            while( count < n && cin.hasNextLine() ) {
                String[] node = cin.nextLine().split(" ");
                g.addEdge(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
                count ++;
            }
            System.out.println(g.isConnected());
        }
    }
}
