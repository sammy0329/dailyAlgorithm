import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생김
 * 8방 탐색 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
 *
 * 1. 모든 구름이 di 방향으로 si칸 이동
 * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
 * 3. 구름이 모두 사라짐
 * 4. 2에서 물이 증가한 칸 (r, c)에 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양 증가
 * - 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아님.
 * 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듦. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함.
 *
 * 입력
 * N,M
 * N개의 줄에 N개 정수 주어짐
 * M개의 줄에 di,si가 순서대로 주어짐
 *
 * 출력
 * M번 이동 후, 바구니에 들어있는 총 물의 양
 *
 * 문제해결 프로세스
 * (N, 1), (N, 2), (N-1, 1), (N-1, 2) 시작
 * 이동 후, 각 영역에 +1, 대각선 방향에 물이 있는지 유무에 따라 ++
 * 전체에서 2이상인 값을 가진 곳이 구름이 되고, -2
 *
 * step 0
 * arr 채우기
 *
 * step1
 * 각 지점에서 di 방향으로 si 칸 이동한 좌표 체크 후, +1 진행
 * 각 영역 대각선 체크 후, 추가 ++
 *
 * step2
 * 완전탐색으로 전체 arr 배열에서 2이상인 구역 체크 후, -2 진행
 *
 * 반복
 */

public class Main {
    static int N,M;
    static int[][] arr;
    static int[] dr = {0,-1,-1,-1,0,1,1,1};
    static int[] dc = {-1,-1,0,1,1,1,0,-1};
    static Queue<Point> clouds = new ArrayDeque<>();
    static boolean[][] isVisited;

    static class Point {
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        isVisited = new boolean[N][N];

        clouds.offer(new Point(N-1,0));
        clouds.offer(new Point(N-1,1));
        clouds.offer(new Point(N-2,0));
        clouds.offer(new Point(N-2,1));

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());

            move(d,s);
            checkSpot();
            
            // 방문 배열 초기화
            isVisited = new boolean[N][N];
        }

        System.out.println(countTotalWater());

    }

    private static int countTotalWater() {
        int totalWater = 0;

        for(int r=0; r<N; r++) {
            for (int c = 0; c < N; c++) {
                totalWater+=arr[r][c];
            }
        }
        return totalWater;
    }

    private static void checkSpot() {
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(isVisited[r][c] || arr[r][c]<2) {
                    continue;
                }
                arr[r][c]-=2;
                clouds.offer(new Point(r,c));
            }
        }
    }


    private static void move(int d, int s) {
        for(Point p : clouds){
            // d방향 s칸 움직임
            p.r = (N+p.r+dr[d]*(s%N))%N;
            p.c = (N+p.c+dc[d]*(s%N))%N;

            // 해당 지역 +1
            arr[p.r][p.c]++;
        }

        while (!clouds.isEmpty()) {
            Point p = clouds.poll();
            int cnt = 0;

            // 해당지역 방문처리
            isVisited[p.r][p.c] = true;

            // 대각선 체크 : 대각선에 물이 1이상 있으면 ++
            for(int j=1; j<8 ; j+=2){
                int nr = p.r + dr[j];
                int nc = p.c + dc[j];

                if(nr>N-1 || nr<0 || nc>N-1 || nc<0) continue;

                if(arr[nr][nc]>0) {
                    cnt++;
                }
            }

            arr[p.r][p.c]+=cnt;
        }
    }
}