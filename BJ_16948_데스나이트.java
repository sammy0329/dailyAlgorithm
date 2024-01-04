package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16948

/**
 * 입력
 * 체스판 크기 N, 시작지점 r1,c1, 종료지점 r2,c2
 * 출력
 * 시작지점(r1,c1)에서 종료지점(r2,c2)로 이동할 수 있는 최소 이동 횟수 출력
 * 문제해결 프로세스
 * 레벨별 BFS를 통해 start 지점부터 end지점까지 갈 수 있는 경우가 있다면 최소 거리 보장 - depth 반환
 * 만약 큐가 모두 비워져도 end지점에 도달 못하면 갈 수 없는 것이므로 -1 반환
 */

public class BJ_16948_데스나이트 {
    static int[] dr = {-2,-2,0,0,2,2}; // 행 방향 이동
    static int[] dc = {-1,1,-2,2,-1,1}; // 열 방향 이동
    static boolean[][] isVisited;
    static int N,sr,sc,er,ec;

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr,sc));
        int depth = 1;
        int size = 1;

        while (!q.isEmpty()){
            size = q.size();
            for(int i=0;i<size;i++){
                Point current = q.poll();

                for(int d=0;d<6;d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if(nr==er && nc==ec) return depth;

                    if(nr<=0 || nc<=0 || nr>N || nc>N) continue;

                    if(!isVisited[nr][nc]) {
                        q.offer(new Point(nr,nc));
                        isVisited[nr][nc] =true;
                    }
            }
            }
            depth++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N+1][N+1];

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
