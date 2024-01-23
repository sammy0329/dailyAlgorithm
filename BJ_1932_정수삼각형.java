package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * n(1 ≤ n ≤ 500), 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어짐.
 *
 * 출력
 * 합이 최대가 되는 경로에 수 합
 *
 * 문제해결 프로세스
 * arr 배열에 값을 넣어두고 아래쪽에서부터 arr[i][j] += Math.max(arr[i+1][j],arr[i+1][j+1]) 로직으로 쌓아 올라가자
 */

// https://www.acmicpc.net/problem/1932
public class BJ_1932_정수삼각형 {
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr =new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=i;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=N-1;i>0;i--){
            for(int j=1;j<=i;j++){
                arr[i][j] += Math.max(arr[i+1][j],arr[i+1][j+1]);
            }
        }

        System.out.println(arr[1][1]);
    }
}
