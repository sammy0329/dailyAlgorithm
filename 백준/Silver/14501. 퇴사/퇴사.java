import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제
 * 걸리는 시간 Ti, 상담시 받을 수 있는 금액 Pi
 * N+1일째 퇴사하는데 가장 많은 이익을 받을 경우 찾기
 *
 * 입력
 * N (1 ≤ N ≤ 15)
 * Ti Pi (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)
 *
 * 출력
 * 최대 이익은?
 *
 * 문제해결 프로세스
 * dp 점화식 : DP[현재 날의 상담 기간을 계산했을 때 끝나는 날] = max(DP[현재 날의 상담 기간을 계산했을 때 끝나는 날], DP[현재 날까지
 * 계산된 값] + pay[현재 날 상담을 통해 얻는 이익])
 */

public class Main {

    static int N;
    static Node[] arr;
    static int[] dp;

    static class Node {

        int t, p;

        public Node(int t, int p) {
            this.t = t;
            this.p = p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            arr[i] = new Node(t, p);
        }

        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            if (i + arr[i].t <= N) {
                dp[i + arr[i].t] = Math.max(dp[i + arr[i].t], dp[i] + arr[i].p);
            }
            
        }

        System.out.println(dp[N]);
    }
}