import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//@formatter:off

/**
 * [문제]
 * 로봇은 좌,우,하 로만 이동 가능
 * 한 번 탐사한 지역은 탐사하지 않는다.
 * 로봇을 배열 (1,1)에서 출발시켜 (N,M)으로 보내려할 때, 탐사한 지역들의 가치의 합이 최대가 되도록 하려면?
 *
 * [입력]
 * N, M
 * 배열의 수는 절댓값이 100을 넘지 않는 정수
 *
 * [출력]
 * 최대 가치의 합
 *
 * [문제해결 프로세스]
 * 좌,우로 가는 경우를 고려해 dp에 추가로 넣어 계산한다.
 * dp[r][c][0] -> 가장 최댓값, dp[r][c][1] -> 왼쪽에서 오른쪽으로 이동할 때 최댓값, dp[r][c][2] -> 오른쪽에서 왼쪽으로 이동할 때 최댓값
 * 왼쪽에서 오른쪽으로 이동할 때 점화식 : dp[r][c][1] = Math.max(dp[r - 1][c][0], dp[r][c - 1][1]) + map[r][c];
 * 오른쪽에서 왼쪽으로 이동할 때 점화식 : dp[r][c][2] = Math.max(dp[r - 1][c][0], dp[r][c + 1][2]) + map[r][c];
 * 왼쪽에서 오른쪽, 오른쪽에서 왼쪽의 계산을 마치면 둘 중 최댓값을 dp[r][c][0]에 최신화 시켜준다. : dp[r][c][0] = Math.max(dp[r][c][1], dp[r][c][2]);
 * 
 */

//@formatter:on
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][M + 1];
        int[][][] dp = new int[N + 1][M + 1][3];

        for (int r = 1; r < N + 1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < M + 1; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1][0] = map[1][1];

        // 1번째 행은 무조건 오른쪽으로 이동할 수 밖에 없음
        for (int c = 1; c < M; c++) {
            dp[1][c + 1][0] = dp[1][c][0] + map[1][c + 1];
        }

        for (int r = 2; r <= N; r++) {
            // 왼쪽->오른쪽 이동 이때, 최댓값을 1번째 인덱스에 저장
            dp[r][1][1] = dp[r - 1][1][0] + map[r][1]; // 첫번째 열은 위에서 내려오는 방법 밖에 없음

            for (int c = 2; c <= M; c++) {
                // 위에서 오는거, 왼쪽에서 오는거중에 큰거
                dp[r][c][1] = Math.max(dp[r - 1][c][0], dp[r][c - 1][1]) + map[r][c];
            }

            // 오른쪽->왼쪽 이동 이때, 최댓값을 2번째 인덱스에 저장
            dp[r][M][2] = dp[r - 1][M][0] + map[r][M]; // 첫번째 열은 위에서 내려오는 방법 밖에 없음

            for (int c = M - 1; c > 0; c--) {
                // 위에서 오는거, 오른쪽에서 오는거중에 큰거
                dp[r][c][2] = Math.max(dp[r - 1][c][0], dp[r][c + 1][2]) + map[r][c];
            }

            // 왼쪽으로 이동한것과 오른쪽으로 이동한 것중 최댓값을 0번째 인덱스에 저장
            for (int c = 1; c <= M; c++) {
                dp[r][c][0] = Math.max(dp[r][c][1], dp[r][c][2]);
            }
        }

        System.out.print(dp[N][M][0]);

    }
}