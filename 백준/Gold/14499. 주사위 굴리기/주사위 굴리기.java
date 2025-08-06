import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, 0, -1, 1}; // 동서북남
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] dice = new int[6];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1; // 0:동 1:서 2:북 3:남
            int nx = r + dr[dir];
            int ny = c + dc[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            // 주사위 회전
            if (dir == 0) {
                int temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
            } else if (dir == 1) {
                int temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
            } else if (dir == 2) {
                int temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
            } else if (dir == 3) {
                int temp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
            }

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[5];
            } else {
                dice[5] = map[nx][ny];
                map[nx][ny] = 0;
            }

            System.out.println(dice[0]);
            r = nx;
            c = ny;
        }
    }
}
