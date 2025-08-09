import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[][][] arr;
    static Queue<Point> q = new ArrayDeque<>();
    static int n, m, h;


    static class Point {

        int h;
        int r;
        int c;

        public Point(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new int[h][m][n];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) {
                        q.offer(new Point(i, j, k));
                    }
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);

    }


    static int bfs() {
        
        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int d = 0; d < 6; d++) {
                int nh = current.h + dh[d];
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if (nh < 0 || nh >= h || nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                if (arr[nh][nr][nc] == 0) {
                    arr[nh][nr][nc] = arr[current.h][current.r][current.c] + 1;
                    q.offer(new Point(nh, nr, nc));
                }
            }
        }
        
        int max = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (arr[i][j][k] == 0) {
                        return -1;
                    }
                    max = Math.max(max, arr[i][j][k]);
                }
            }
        }
        
        return max - 1;
    }

}
