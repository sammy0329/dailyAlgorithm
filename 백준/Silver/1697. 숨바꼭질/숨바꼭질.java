import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] d = {-1, 1, 2};
    static boolean[] visited = new boolean[100001];
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        int depth = 0;

        q.offer(n);

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int current = q.poll();

                if (current == k) {
                    return depth;
                }

                if (current - 1 >= 0 && !visited[current - 1]) {
                    q.offer(current - 1);
                    visited[current - 1] = true;
                }
                if (current + 1 < 100001 && !visited[current + 1]) {
                    q.offer(current + 1);
                    visited[current + 1] = true;
                }
                if (current * 2 < 100001 && !visited[current * 2]) {
                    q.offer(current * 2);
                    visited[current * 2] = true;
                }
            }
            depth++;
        }

        return depth;
    }


}
