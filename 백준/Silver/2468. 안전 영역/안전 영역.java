import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제
 * 물에 잠기지 않는 안전한 영역의 개수 찾기
 *
 * 입력
 * 2차원 배열의 행,열 N (2<=N<=100)
 * N번째 행까지 한 행씩 높이 정보 입력
 *
 * 출력
 * 안전한 영역의 최대 개수
 *
 * 문제해결 프로세스
 * 아이스크림 얼려먹기랑 비슷한 문제라 생각
 * 1. 입력 받은 값 중 최솟값, 최댓값을 구한다.
 * 2. 0~max까지 물 잠기기를 진행하고 dfs를 진행시켜, 방문 배열을 갱신하고, 갯수를 구한다.
 * 3. 한번 돌 때, result max 값으로 갱싢한다.
 *
 */
public class Main {

    static int[][] arr;
    static int N, MAX_NUMBER, result;
    static boolean[][] isVisited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        MAX_NUMBER = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                MAX_NUMBER = Math.max(MAX_NUMBER, arr[i][j]);
            }
        }

        for (int h = 0; h <= MAX_NUMBER; h++) {
            isVisited = new boolean[N][N];
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j] || arr[i][j] <= h) continue;
                    dfs(i, j, h);
                    cnt += 1;
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    static void dfs(int x, int y, int h) {
        isVisited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= N || nx < 0 || ny >= N || ny < 0 || isVisited[nx][ny] || arr[nx][ny] <= h) continue;
            dfs(nx, ny, h);
        }
    }
}