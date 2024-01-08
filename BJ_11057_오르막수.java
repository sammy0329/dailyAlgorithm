package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 정리
 * 수는 0으로 시작할 수 있음
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말함.
 * 인접한 수가 같아도 오름차순으로 침.
 *
 * 입력
 * N (1 <= N <= 1,000) 수의 길이
 *
 * 출력
 * 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지 출력
 *
 * 문제해결 프로세스
 * dp[][] - 배열의 행은 오르막 수의 길이, 열은 오르막 수의 마지막 수의 값.
 * dp[i][j] = dp[i-1][0~j] 합
 */

// https://www.acmicpc.net/problem/11057

public class BJ_11057_오르막수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        int[][] dp = new int[N+1][10];

        for(int i=0; i<10; i++){
            dp[1][i] = 1;
        }

        for(int i=2;i<=N;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<=j;k++){
                    dp[i][j] += dp[i-1][k];
                }
                dp[i][j]%=10007;
            }
        }
        for(int i=0;i<10;i++){
            result += dp[N][i];
        }
        System.out.println(result%10007);

    }
}
