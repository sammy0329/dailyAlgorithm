import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * (0,0) -> (500,500) 이동해야 함 상,하,좌,우로 이동 가능 들어갈 수 없는 곳, 움직임마다 생명이 1 깎이는 곳, 생명 깎임 없이 움직일 수 있는 곳
 * 잃는 생명의 최솟값은?
 *
 * 입력
 * 위험한 구역의 수 N
 * X1 Y1 X2 Y2 위험구역 정보 주어짐
 * 죽음의 구역 M
 * X1 Y1 X2 Y2 죽음구역 정보 주어짐
 * 0<N,M<=50 구역은 겹칠 수 있고, 겹칠 때, 더 심한 구역이 해당
 * ex) 죽음+위험=죽음 / 위험+안전=위험 / 위험+위험=위험 / 죽음+안전=죽음
 *
 * 문제해결 프로세스
 * 최솟값을 우선 처리 하기 위해 우선순위 큐를 사용하여 BFS 진행
 */
public class Main {

    static int N, M;
    static boolean[][] visit = new boolean[501][501];
    static int[][] arr = new int[501][501];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node implements Comparable<Node> {

        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // arr에 위험지역 표시하기
            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    arr[j][k] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // arr에 죽음지역 표시하기
            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    arr[j][k] = 2;
                }
            }
        }

        System.out.println(bfs());
    }

    // BFS 함수
    static int bfs() {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, 0));
        visit[0][0] = true;
        arr[0][0] = 0; // 처음 서있는 자리는 영향 받지 않음

        while (!q.isEmpty()) {
            Node node = q.poll();
            int cost = node.cost;

            if (node.x == 500 && node.y == 500) {
                return cost;
            }

            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (nx < 0 || nx > 500 || ny < 0 || ny > 500) {
                    continue;
                }
                if (visit[nx][ny] || arr[nx][ny] == 2) {
                    continue;
                }

                visit[nx][ny] = true;
                arr[nx][ny] += cost;
                q.offer(new Node(nx, ny, arr[nx][ny]));
            }
        }
        return -1;
    }
}