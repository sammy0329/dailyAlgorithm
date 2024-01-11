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
    // 시계 반대방향으로 전진 우하좌상
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    static void rotate(int startPoint, int cnt) {
        int rr = R % cnt;
        for (int i = 0; i < rr; i++) {

            int start = arr[startPoint][startPoint];
            int r = startPoint;
            int c = startPoint;
            int dir = 0;

            while (dir < 4) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr == startPoint && nc == startPoint) break;
                if (nr < N - startPoint && nc < M - startPoint && nr >= startPoint && nc >= startPoint) {
                    arr[r][c] = arr[nr][nc];
                    r = nr;
                    c = nc;
                } else {
                    dir++;
                }
            }
            // 빈자리에 초기값 넣어줌
            arr[startPoint + 1][startPoint] = start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 회전 수
        arr = new int[N][M];
        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 박스 테두리 개수
        int cnt = Math.min(N,M)/2;
        int n=N, m=M;

        for (int i = 0; i < cnt ; i++){
            rotate(i,2*n + 2*m -4);
            n-=2;
            m-=2;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
