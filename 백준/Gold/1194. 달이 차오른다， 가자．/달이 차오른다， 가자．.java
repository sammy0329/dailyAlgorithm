import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] arr;
    static Point startPoint;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    static int bfs() {
        boolean[][][] visited = new boolean[n][m][64];
        Queue<Point> q = new ArrayDeque<>();

        q.offer(startPoint);
        visited[startPoint.r][startPoint.c][0] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point current = q.poll();

                if (arr[current.r][current.c] == '1') {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                        continue;
                    }
                    if (arr[nr][nc] == '#') {
                        continue;
                    }

                    int keys = current.keys;
                    char cell = arr[nr][nc];

                    // 문인 경우 (대문자)
                    if (Character.isUpperCase(cell)) {
                        int doorBit = 1 << (cell - 'A');
                        if ((keys & doorBit) == 0) {
                            continue;
                        }
                    }

                    // 열쇠인 경우 (소문자)
                    if (Character.isLowerCase(cell)) {
                        int keyBit = 1 << (cell - 'a');
                        keys |= keyBit;
                    }

                    if (!visited[nr][nc][keys]) {
                        visited[nr][nc][keys] = true;
                        q.offer(new Point(nr, nc, keys));
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    static class Point {

        int r, c, keys;

        public Point(int r, int c, int keys) {
            this.r = r;
            this.c = c;
            this.keys = keys;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];

        for (int r = 0; r < n; r++) {
            String s = br.readLine();
            for (int c = 0; c < m; c++) {
                arr[r][c] = s.charAt(c);
                if (arr[r][c] == '0') {
                    startPoint = new Point(r, c, 0);
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

}
