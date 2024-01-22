package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이해
 * 테트로미노 하나를 적절히 놓아서 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램
 * 회전, 대칭 가능
 *
 * 입력
 * 종이 세로 N, 가로 M (4 <= N,M <= 500)
 * N개의 줄에 종이에 쓰여 있는 수가 주어짐
 *
 * 출력
 * 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값 출력
 *
 * 문제해결 프로세스
 * ㅜ 모양을 제외하면 깊이가 4인 dfs 탐색
 * dfs 한 후, 방문처리를 false 해주기
 *
 * ㅜ 모양은 4방 중 3칸 선택하는 조합
 *
 * 맵 최대 500*500 = 25만
 * 완전탐색
 *
 */

// https://www.acmicpc.net/problem/14500
public class BJ_14500_테트로미노 {
    static int N,M;
    static int result = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    // ㅗ ㅜ ㅏ ㅓ 선택을 위한 메서드 4C3
    private static void combi(int cnt, int start, int r, int c, int sum) {
        // 기저조건
        if(cnt == 3) {
            result = Math.max(result, sum);
            return;
        }

        for (int d = start; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr<0 || nr>=N || nc<0 || nc>=M) continue;

            combi(cnt+1, d+1, r, c, sum+map[nr][nc]);
        }
    }

    public static void dfs(int r, int c, int cnt, int sum){
        // 기저조건
        if(cnt==4){
            result = Math.max(result,sum);
            return;
        }

        for (int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(nr<0 || nr>=N || nc<0 || nc>=M || isVisited[nr][nc]) continue;

            isVisited[nr][nc] = true; // 선택 처리
            dfs(nr,nc,cnt+1,sum+map[nr][nc]);
            isVisited[nr][nc] = false; // 선택 해제
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];

        // map 배열 입력받기
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로직 실행
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                isVisited[i][j] = true; // 선택
                dfs(i,j,1,map[i][j]);
                isVisited[i][j] = false; // 방문해제

                combi(0,0,i,j,map[i][j]);
            }
        }
        System.out.println(result);
    }
}
