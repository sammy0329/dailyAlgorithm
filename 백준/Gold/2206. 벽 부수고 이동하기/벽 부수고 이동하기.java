import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * NxM 맵에서 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽이 있는 곳을 나타냄
 * (1,1) -> (N,M) 위치까지 최단 경로로 이동 (가장 적은 개수의 칸을 지나는 경로 : 시작,끝 칸 포함)
 * 만약, 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 짧은 경로라면, 한 개 까지 부수고 이동.
 *
 * 입력
 * M M
 * 맵 행렬 형태로 주어짐
 *
 * 출력
 * 최단 거리 (불가능시 -1 출력)
 *
 * 문제해결 프로세스
 * 1. 맵 입력 받기
 * 2. (1,1) -> (N,M) BFS 진행 (이때, 방문 배열을 3차원으로 설정하여 벽을 부수지 않고 방문한 경우와 벽을 부수고 박문했는지 여부를 체크)
 * 3. 최단거리 출력
 */

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][][] isVisited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {

        int r;
        int c;
        int cnt;
        boolean destroyed;

        public Point(int r, int c, int cnt, boolean destroyed) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        isVisited = new boolean[N + 1][M + 1][2];

        for (int i = 1; i < N + 1; i++) {
            String line = br.readLine();
            for (int j = 1; j < M + 1; j++) {
                int num = line.charAt(j - 1) - '0';
                if (num == 1) {
                    map[i][j] = num;
                }
            }
        }
        bfs();
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(1, 1, 1, false));

        while (!q.isEmpty()) {
            Point current = q.poll();

            if (current.r == N && current.c == M) {
                System.out.println(current.cnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                // 범위 벗어나는지 체크
                if (nr > N || nr < 1 || nc > M || nc < 1) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    if (!current.destroyed) { // 벽이고, 여태 벽을 부수지 않았다면, 부수기
                        q.add(new Point(nr, nc, current.cnt + 1, true));
                        isVisited[nr][nc][1] = true;
                    }
                    // 한번 부수고 간 벽이 있었다면, 이후에는 벽을 부술 수 없으므로 부순적이 있는지 확인하는 조건은 필요 X
                } else {
                    if (!current.destroyed && !isVisited[nr][nc][0]) { // 벽이 아니고, 부순 벽이 여태까지 없었으면
                        q.offer(new Point(nr, nc, current.cnt + 1, false));
                        isVisited[nr][nc][0] = true;
                    } else if (current.destroyed && !isVisited[nr][nc][1]) { // 벽이 아니고, 부순 적이 있으면
                        q.offer(new Point(nr, nc, current.cnt + 1, true));
                        isVisited[nr][nc][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}