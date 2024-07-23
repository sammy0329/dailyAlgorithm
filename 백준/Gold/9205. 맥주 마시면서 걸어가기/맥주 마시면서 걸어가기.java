import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * 출발은 상근이네 집 / 맥주 한 박스 들고 출발 / 맥주 20개 들어있음
 * 한 병을 마시면 50m 갈 수 있음
 * 편의점에 들리면 빈 병은 버리고 새 맥주 병을 살 수 있으나, 맥주는 20병을 넘길 수 없음
 *
 * 입력
 * [첫째 줄]
 * 테스트 케이스 개수 t
 * [둘째 줄]
 * 편의점의 개수 n
 * [셋째 줄 ~]
 * 집, 편의점, 펜타포트 락 페스티벌 좌표 x,y
 * 두 좌표 사이의 거리는 x좌표 차이 + y좌표 차이
 *
 * 출력
 * 페스티벌 갈 수 있으면 happy, 없으면 sad
 *
 * 문제해결 프로세스
 * bfs 진행
 * 만약 현재 위치에서 끝 위치까지 거리가 1000 이하라면 갈 수 있는 것
 * 1000 보다 크면, 근방 1000 이하에 편의점 있는지 체크해 맥주 리필해야 함.
 * 이내에 있다면 큐에 넣고, 방문 처리
 */
public class Main {

    static int N;
    static List<Point> storePoints;
    static Point startPoint, endPoint;

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            N = Integer.parseInt(br.readLine());
            storePoints = new ArrayList<>();
            // 집 위치 입력
            st = new StringTokenizer(br.readLine());
            startPoint = new Point(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

            // 편의점 위치 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                storePoints.add(
                    new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            // 락 페스티벌 위치 입력
            st = new StringTokenizer(br.readLine());
            endPoint = new Point(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

            bfs();
        }

    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N]; // 편의점 방문처리
        q.offer(startPoint);

        while (!q.isEmpty()) {
            Point current = q.poll();
            if (Math.abs(current.x - endPoint.x) + Math.abs(current.y - endPoint.y) <= 1000) {
                System.out.println("happy");
                return;
            }

            for (int i = 0; i < N; i++) {
                if (isVisited[i]) {
                    continue;
                }
                if (Math.abs(current.x - storePoints.get(i).x) + Math.abs(
                    current.y - storePoints.get(i).y) <= 1000) {
                    isVisited[i] = true;
                    q.offer(storePoints.get(i));
                }
            }
        }

        System.out.println("sad");
    }


}