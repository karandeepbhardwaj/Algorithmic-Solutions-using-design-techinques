import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MrMeeseeksBakery {
    static class QueueInfo implements Comparable<QueueInfo> {
        public int val;
        public int queueId;
        public int index;

        public QueueInfo(int val, int queueId, int index) {
            this.val = val;
            this.queueId = queueId;
            this.index = index;
        }

        @Override
        public int compareTo(QueueInfo o) {
            if (this.val == o.val) {
                if (this.index >= o.index) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.val < o.val) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("QueueInfo{");
            sb.append("val=").append(val);
            sb.append(", queueId=").append(queueId);
            sb.append(", index=").append(index);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        ArrayList<QueueInfo> info = new ArrayList<>();
        ArrayList<int[]> visited = new ArrayList<>();
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        int[] served = new int[n];
        Arrays.fill(served, -1);
        for (int i = 0; i < n; i++) {
            data.add(new ArrayList<>());
            int m = scan.nextInt();
            int[] vis = new int[m];
            for (int j = 0; j < m; j++) {
                int v = scan.nextInt();
                info.add(new QueueInfo(v, i, j));
                data.get(i).add(v);
                vis[j] = 0;
            }
            visited.add(vis);
        }
        long startTime = System.currentTimeMillis();
        Collections.sort(info);
        int res = 0;
        for (QueueInfo queueInfo : info) {
            if (visited.get(queueInfo.queueId)[queueInfo.index] == 1) continue;
            ;
            boolean done = false;
            for (int j = served[queueInfo.queueId] + 1; j <= queueInfo.index; j++) {
                if (res < queueInfo.val) {
                    res++;
                    visited.get(queueInfo.queueId)[j] = 1;
                } else {
                    done = true;
                }
            }
            if (done == true) {
                break;
            }
            served[queueInfo.queueId] = queueInfo.index;
        }
        System.out.println(res);
        long endTime = System.currentTimeMillis();
        System.out.println("KB: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Running time: " + ((System.currentTimeMillis() - startTime)) / 1000f + "seconds");
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
    }
}