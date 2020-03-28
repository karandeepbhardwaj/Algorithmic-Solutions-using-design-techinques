import java.util.ArrayList;
import java.util.Scanner;

public class Red {

    public static boolean isIntersect(int[][] arr, int i, int j) {
        if (Math.max(arr[i][0], arr[j][0]) <= Math.min(arr[i][1], arr[j][i])) {
            return true;
        } else {
            return false;
        }
    }

    public static int bigmod(long b, long p, long m) {
        long res = 1L;
        while (p > 0) {
            if (p % 2 == 1) {
                res = (res * b) % m;
            }
            b = (b * b) % m;
            p /= 2;
        }
        return (int) res;
    }

    public static boolean isBipartite(ArrayList<ArrayList<Integer>> graph, int currentNode, int[] vis, ArrayList<Integer> nodesInCurrentGraph) {
        nodesInCurrentGraph.add(currentNode);
        for (int i = 0; i < graph.get(currentNode).size(); i++) {
            int u = graph.get(currentNode).get(i);
            if (vis[u] == -1) {
                vis[u] = 1 - vis[currentNode];
                isBipartite(graph, u, vis, nodesInCurrentGraph);
            } else if (vis[u] == vis[currentNode]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] arr = new int[n][2];
        int[] vis = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }
        long startTime = System.currentTimeMillis();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            vis[i] = -1;
            for (int j = i + 1; j < n; j++) {
                if (!isIntersect(arr, i, j)) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        int typeA = 0;
        int typeB = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (vis[i] == -1) {
                vis[i] = 0;
                ArrayList<Integer> nodes = new ArrayList<>();
                if (isBipartite(graph, i, vis, nodes)) {
                    if (nodes.size() % 2 == 0) {
                        int edgeCount = 0;
                        int colorA = 0, colorB = 0;
                        for (int j = 0; j < nodes.size(); j++) {
                            edgeCount += graph.get(nodes.get(j)).size();
                            if (vis[nodes.get(j)] == 0) {
                                colorA++;
                            } else {
                                colorB++;
                            }
                        }
                        if (edgeCount == 2 * (colorA * colorB)) {
                            if (colorA % 2 == 0) {
                                typeA++;
                            } else {
                                typeB++;
                            }
                        }
                    }
                }
            }
        }
        int res = bigmod(2, n, 1000000007) - 1 + 2 * (typeA - typeB);
        System.out.println(res);
        long endTime = System.currentTimeMillis();
        System.out.println("KB: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Running time: " + ((System.currentTimeMillis() - startTime)) / 1000f + "seconds");
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
    }
}
