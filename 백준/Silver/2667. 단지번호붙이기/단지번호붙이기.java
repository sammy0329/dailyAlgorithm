import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static List<Integer> list = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            String s = br.readLine();
            for (int c = 0; c < n; c++) {
                arr[r][c] = s.charAt(c) - '0';
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (arr[r][c] == 0) {
                    continue;
                }
                if (!visited[r][c]) {
                    bfs(new Point(r, c));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");

        Collections.sort(list);
        for (Integer i : list) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(Point p) {
        int cnt = 1;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(p);
        visited[p.r][p.c] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || arr[nr][nc] == 0
                    || visited[nr][nc]) {
                    continue;
                }

                q.offer(new Point(nr, nc));
                visited[nr][nc] = true;
                cnt++;
            }
        }
        list.add(cnt);
    }
}
