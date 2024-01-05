package dailyProblems;

// https://www.acmicpc.net/problem/10844

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 입력
 * N(100>=N>=1):  N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 출력
 * 1,000,000,000으로 나눈 나머지를 출력.
 *
 * 문제해결 프로세스
 * N 자리 수의 각각의 자리 값(0~9)을 표현하는 dp 배열 작성
 * 1에서 부터 8은 하나 작거나 큰 수가 올 수 있음. - dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])
 * 0과 9의 경우 1~8과는 달리 0 옆에는 오직 1, 9 옆에는 오직 8 하나씩의 숫자만 올 수 있음.
 * 0은 dp[i][j] = dp[i-1][j+1] , 9는 dp[i][j] = dp[i-1][j-1]
 *
 */
public class BJ_10844_쉬운계단수 {
    static int NUMBER = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long result = 0;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10]; // N자리 수의 각 자리 값을 표현하는 dp 배열 생성

        for(int i=1; i<10; i++){ // 한자리 수 일 때 가능한 (1~9)
            dp[1][i] = 1;
        }

        for(int i=2; i<=N; i++){
            for(int j=0;j<10;j++){
                if(j==0) dp[i][j] = dp[i-1][j+1]%NUMBER;
                else if(j==9) dp[i][j] = dp[i-1][j-1]%NUMBER;
                else dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]%NUMBER;
            }
        }

        for(int i=0; i<10; i++){
            result += dp[N][i];
            result%=NUMBER;
        }
        System.out.println(result%NUMBER);
    }
}