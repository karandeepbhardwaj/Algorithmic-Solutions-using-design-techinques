import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SocratesCow {
    public static int getId(char c) {
        if (Character.isLowerCase(c)) {
            return 26 + (c - 'a');
        } else {
            return c - 'A';
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int numberOfTestCase = scan.nextInt();
        List<Integer> source = new ArrayList<>();
        HashMap<Integer, Integer> sourceAdded = new HashMap<>();
        int[][] dis = new int[52][52];

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                dis[i][j] = 100000000;
            }
            dis[i][i] = 0;
        }

        for (int i = 0; i < numberOfTestCase; i++) {
            String from = scan.next();
            String to = scan.next();
            int time = scan.nextInt();
            int fromId = getId(from.charAt(0));
            int toId = getId(to.charAt(0));
            if (Character.isUpperCase(from.charAt(0)) && sourceAdded.getOrDefault(fromId, 0) == 0) {
                sourceAdded.put(fromId, 1);
                source.add(fromId);
            }
            if (Character.isUpperCase(to.charAt(0)) && sourceAdded.getOrDefault(toId, 0) == 0) {
                sourceAdded.put(toId, 1);
                source.add(toId);
            }
            dis[fromId][toId] = Math.min(dis[fromId][toId], time);
            dis[toId][fromId] = Math.min(dis[toId][fromId], time);
        }
        long startTime = System.currentTimeMillis();
        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        int destinationId = getId('z');
        int first = -1, shortestTime = 100000000;
        for (int i = 0; i < source.size(); i++) {
            int sourceId = source.get(i);
            if (dis[sourceId][destinationId] < shortestTime) {
                shortestTime = dis[sourceId][destinationId];
                first = sourceId;
            }
        }
        System.out.println((char) (first + 'A') + " " + shortestTime);
        long endTime = System.currentTimeMillis();
        System.out.println("KB: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Running time: " + ((System.currentTimeMillis() - startTime)) / 1000f + "seconds");
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
    }
}
