package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N×N 공간 물고기 M마리와 아기 상어 1마리 존재.
 * 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동.
 *
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있음.
 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있음.
 * 크기가 같은 물고기는 먹을 수 없지만, 지나갈 수 있음.
 *
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움 요청.
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 감.
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 감.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값.
 * 거리가 가까운 물고기가 많다면, 상 - 좌 순으로 먹음
 * 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정.
 * 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹음.
 * 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 *
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가.
 * 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 됨.
 *
 * 몇 초 동안 엄마 상어에게 도움 요청 없이 잡아먹을 수 있는가?
 *
 * 문제해결 프로세스
 * 먹잇감 찾기 : 레벨별 BFS를 통해 경로안에 먹잇감이 있는지 판단 (단, 자신보다 몸집이 큰 물고기가 있는 곳은 q에 넣지 않음)
 * 방문 배열은 먹잇감 찾기 전 초기화
 *
 */
public class BJ_16236_아기상어 {
    static int n;
    static int[][] arr;
    static boolean[][] isVisited;
    static int time;

    // 상좌하우
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static int sharkSize = 2;
    static int eatFish = 0;

    public static Point bfs(Point point){
        Queue<Point> q = new ArrayDeque<>();
        q.offer(point);
        isVisited[point.r][point.c] = true;

        int size = 1;
        int nr = 0;
        int nc = 0;
        int checkNR = Integer.MAX_VALUE;
        int checkNC = Integer.MAX_VALUE;
        int cnt = 0;
        boolean flag = false;

        while (!q.isEmpty() && !flag){
            size = q.size();
            cnt++;
            for(int i=0;i<size;i++){
                Point current = q.poll();
                for(int d=0;d<4;d++){
                    nr = current.r + dr[d];
                    nc = current.c + dc[d];

                    if(nr>=n || nr<0 || nc>=n || nc<0 || isVisited[nr][nc] || arr[nr][nc]==9) continue;
                    if(sharkSize<arr[nr][nc]) continue; // 자신보다 몸집 큰애 있으면 못지나감

                    if(sharkSize>arr[nr][nc] && arr[nr][nc]>0) {
                        flag = true;

                        if(checkNR == nr) {
                            // 그 위에 있는 물고기의 수가 많다면 가장 왼쪽에 있는 물고기 선택
                            if(checkNC > nc) {
                                checkNR = nr;
                                checkNC = nc;
                            }
                        } else if(checkNR > nr) { // 더 위에 있는 물고기가 한 마리 라면
                            checkNR = nr;
                            checkNC = nc;
                        }
                    }

                    q.offer(new Point(nr,nc));
                    isVisited[nr][nc]=true;
                }
            }
        }
        if(flag) {
            time+=cnt;
            eatFish++;
            arr[checkNR][checkNC] = 0;
            if(eatFish==sharkSize) {
                sharkSize++;
                eatFish=0;
            }
            return new Point(checkNR,checkNC);
        }
        else return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        Point sharkPosition = null;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==9) {
                    sharkPosition = new Point(i,j);
                }
            }
        }

        while (true){
            isVisited = new boolean[n][n];
            // 먹잇감 탐색
            Point goNext = bfs(sharkPosition);
            if(goNext != null) {
                arr[sharkPosition.r][sharkPosition.c] = 0;
                sharkPosition = goNext;
                arr[sharkPosition.r][sharkPosition.c] = 9;
            }
            else break;
        }

        System.out.println(time);

    }
    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
