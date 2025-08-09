import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] arr;
    static int n, m;

    public static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int r = 0; r < n; r++) {
            String s = br.readLine();
            for (int c = 0; c < m; c++) {
                arr[r][c] = s.charAt(c) - '0';
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visisted = new boolean[n][m];
        q.offer(new Point(0, 0));
        visisted[0][0] = true;
        int depth = 1;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point current = q.poll();

                if (current.r == n - 1 && current.c == m - 1) {
                    return depth;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || visisted[nr][nc]
                        || arr[nr][nc] == 0) {
                        continue;
                    }
                    q.offer(new Point(nr, nc));
                    visisted[nr][nc] = true;
                }
            }

            depth++;

        }
        return depth;

    }
}
