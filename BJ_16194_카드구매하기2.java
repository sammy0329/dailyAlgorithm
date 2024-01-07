package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 카드가 i개 포함된 카드팩의 가격은 Pi
 * 입력
 * 카드 개수 N (1 <= N <= 1,000)
 * Pi가 P1부터 PN까지 순서대로 입력 (1 ≤ Pi ≤ 10,000)
 *
 * 출력
 * N개를 갖기 위해 지불해야 하는 금액의 최솟값
 *
 * 문제해결 프로세스
 * dp 배열에 모두 최댓값으로 채워두고, 카드를 i개 뽑을 떄, i개 이하의 카드팩을 활용해서 만들 수 있는 최솟값을 계속 갱신
 */

// https://www.acmicpc.net/problem/16194

public class BJ_16194_카드구매하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], cards[j] + dp[i - j]);
            }
        }

        System.out.println(dp[n]);

    }

}
