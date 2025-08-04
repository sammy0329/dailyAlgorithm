import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {

        int idx;
        int imp;

        public Node(int idx, int imp) {
            this.idx = idx;
            this.imp = imp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<Node> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int imp = Integer.parseInt(st.nextToken());
                queue.offer(new Node(i, imp));
                pq.offer(imp);
            }

            int printOrder = 0;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.imp == pq.peek()) {
                    printOrder++;
                    pq.poll();
                    if (cur.idx == m) {
                        System.out.println(printOrder);
                        break;
                    }
                } else {
                    queue.offer(cur);
                }
            }
        }
    }
}
