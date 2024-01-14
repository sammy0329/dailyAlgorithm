package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이해
 * 창고에 보관되는 토마토들 중에 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있음.
 * 보관 후 하루 지나면 -> 익은 토마토 들의 인접한 곳에 있는 익지 않은 토마토들이 익게됨. -> 4방 탐색
 * 며칠이 지나야 다 익게 되는가? 최소 일수는?
 *
 * 입력
 * M x N 상자 (2 <= M,N <= 1,000)
 * 토마토 정보가 주어짐 (1은 익은 토마토, 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸)
 * 토마토가 하나 이상 있는 경우만 입력으로 주어짐.
 *
 * 출력
 * 토마토가 모두 익을 때까지의 최소 날짜
 * 만약, 저장될 떄 부터 모든 토마토가 익어있는 상태이면 0을 출력하고, 토마토가 모두 익지 못하는 상황이면 -1 출력
 *
 * 문제해결 프로세스
 * 레벨별 BFS 탐색으로 최소시간을 구해보기
 */

// https://www.acmicpc.net/problem/7576
public class BJ_7576_토마토 {
    static int M,N,total;
    static int[][] arr;
    static boolean[][] isVisited;

    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static Queue<Point> q = new ArrayDeque<>();


    public static int bfs(){
        int depth = 0;
        if(total==N*M) return 0;

        while (!q.isEmpty()){
            depth++;
            int size = q.size();
            for(int i=0; i<size; i++){
                Point current = q.poll();
                if(arr[current.r][current.c]==0) arr[current.r][current.c] = 1;

                for(int d=0;d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]==-1) continue;
                    if(arr[nr][nc]==0){
                        arr[nr][nc]=1;
                        q.offer(new Point(nr,nc));
                        total++;
                    }
                }
            }
            if(total==N*M) return depth;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) {
                    q.offer(new Point(i,j)); // 익은 토마토는 큐에 담음
                    total++;
                } else if (arr[i][j]==-1) {
                    total++;
                }
            }
        }

        System.out.println(bfs());

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
