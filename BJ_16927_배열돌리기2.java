package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * 배열의 크기 NXM
 * 회전 수 R
 *
 * 출력
 * 회전된 배열
 *
 * 문제해결 프로세스
 * NXM 배열이 처음 상태로 돌아오는 횟수
 * (2*n) + (2*m) - 4
 * 따라서, R % 처음으로 돌아오는 회전 횟수 번 회전 진행
 *
 *
 */

// https://www.acmicpc.net/problem/16927

public class BJ_16927_배열돌리기2 {
    static int N, M, R;
    static int [][] arr;

    public static void rotate(int loc, int cnt){
        int mod = (N - 2 * loc) + 2 * (M - 2 * loc - 2);
        cnt %= mod;

        for(int i=0; i<cnt; i++){
            int start = arr[loc][loc];
            for(int j=loc; j<M-loc-1; j++) arr[loc][j] = arr[loc][j+1];
            for(int j=loc; j<N-loc-1; i++) arr[j][M-loc-1] = arr[j+1][M-loc-1];
            for(int j=M-loc-2; j>=loc; j--) arr[N-loc-1][j+1] = arr[N-loc-1][j];
            for(int j=N-loc-2; j>=loc; j--) arr[j+1][loc] = arr[j][loc];
            arr[loc+1][loc]=start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 회전 수
        arr = new int[N+1][M+1];
        for(int r=1;r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=M; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Math.min(N, M) / 2; i++){
            rotate(i,R);
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
