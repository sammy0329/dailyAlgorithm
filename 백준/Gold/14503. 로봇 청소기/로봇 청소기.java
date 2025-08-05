import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0}; // 북동남서
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        st = new StringTokenizer(bf.readLine());

        int curR = Integer.parseInt(st.nextToken());
        int curC = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(bf.readLine());
            for (int c = 0; c < m; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;

        while (true) {
            if (arr[curR][curC] == 0) {
                arr[curR][curC] = 2;
                answer++;
            }

            boolean cleaned = false;

            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int nr = curR + dr[d];
                int nc = curC + dc[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && arr[nr][nc] == 0) {
                    curR = nr;
                    curC = nc;
                    cleaned = true;
                    break;
                }
            }

            if (cleaned) continue;

            int bd = (d + 2) % 4;
            int br = curR + dr[bd];
            int bc = curC + dc[bd];
            if (br < 0 || br >= n || bc < 0 || bc >= m || arr[br][bc] == 1) {
                break;
            }
            
            curR = br;
            curC = bc;
        }
        System.out.println(answer);
    }
}
