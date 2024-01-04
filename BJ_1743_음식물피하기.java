
// https://www.acmicpc.net/problem/1743

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 입력 :
 * N: 세로길이, M: 가로길이, 음식물 쓰레기 개수: K(1<=K<=NxM), (r,c) : 음식물 쓰레기 개수
 *
 * 출력:
 * 음식물 중 가장 큰 음식물의 크기
 *
 * 문제해결 프로세스:
 * 상하좌우 4방 탐색을 통해 BFS를 돌려 방문처리를 하고, 방문하지 않은 지점을 시작점으로 다시 BFS 실행.
 */

public class BJ_1743_음식물피하기 {
    static int N,M,K,answer;
    static int[][] arr; // NxM
    static boolean[][] isVisited; // 방문배열
    static int[] dr = {-1,1,0,0}; // 상하좌우
    static int[] dc = {0,0,-1,1};

    public static int bfs(int r, int c) {
        int cnt = 1;
        Queue<Point> q = new ArrayDeque<>();

        q.offer( new Point(r,c)); // 시작 정점 큐에 넣기
        isVisited[r][c]=true; // 방문 처리

        while(!q.isEmpty()) {
            Point current = q.poll(); // 큐에서 뽑기

            for(int d=0;d<4;d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr<=0 || nc<=0 || nr>N || nc>M) continue;

                if(arr[nr][nc]==1 && !isVisited[nr][nc]) {
                    q.offer(new Point(nr,nc));
                    isVisited[nr][nc] =true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        answer = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        isVisited = new boolean[N+1][M+1];

        // 쓰레기 위치 저장
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[r][c] = 1; // 쓰레기 있는 부분 1로 체크
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if(!isVisited[i][j] && arr[i][j]==1) {
                    answer = Math.max(answer, bfs(i,j));
                }
            }
        }
        System.out.println(answer);
    }

    static class Point{
        int r;
        int c;
        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

}