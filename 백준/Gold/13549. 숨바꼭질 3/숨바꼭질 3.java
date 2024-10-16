import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//@formatter:off
/**
 * [문제]
 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000), 동생은 점 K(0 ≤ K ≤ 100,000)
 * 걸을 때 - X-1, X+1
 * 순간이동시 0초 후에 2^X
 *
 * [입력]
 * 수비이가 있는 위치 N, 동생이 있는 위치 K
 *
 * [출력]
 * 수빈이가 동생을 찾는 가장 빠른 시간
 *
 * [문제해결 프로세스]
 * 1. N,K 입력을 받는다.
 * 2. isVisited 방문 배열을 100,001 크기로 만들어서 방문 관리한다.
 * - 현재 수빈이의 위치 N 인덱스에 1초를 넣어준다. -> 모든 배열이 0으로 초기화 되어 있기 때문에 최종 값에서 -1 처리
 * 3. 큐에 현재 위치와 시간을 저장하는 객체를 담고, 앞으로 한칸, 뒤로 한칸, 순간이동(x2) 할 경우를 고려하여 또 큐에 넣어주는 작업을 반복한다.
 */
//@formatter:on
public class Main {

    static int N, K;
    static int[] isVisited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(N, 1));
        isVisited[N] = 1;

        while (!q.isEmpty()) {
            Point current = q.poll();

            if (current.x == K) {
                break;
            }
            
            if (current.x + 1 >= 0 && current.x + 1 <= 100000) { // 앞으로 한칸
                if (isVisited[current.x + 1] == 0 || isVisited[current.x + 1] > current.time + 1) {
                    isVisited[current.x + 1] = current.time + 1;
                    q.add(new Point(current.x + 1, current.time + 1));
                }
            }

            if (current.x - 1 >= 0 && current.x - 1 <= 100000) { // 뒤로 한칸
                if (isVisited[current.x - 1] == 0 || isVisited[current.x - 1] > current.time + 1) {
                    isVisited[current.x - 1] = current.time + 1;
                    q.add(new Point(current.x - 1, current.time + 1));
                }
            }

            if (current.x * 2 >= 0 && current.x * 2 <= 100000) { // 순간이동
                if (isVisited[current.x * 2] == 0 || isVisited[current.x * 2] > current.time) {
                    isVisited[current.x * 2] = current.time;
                    q.add(new Point(current.x * 2, current.time));
                }
            }
        }

        System.out.println(isVisited[K] - 1);

    }

    private static class Point {

        int x;
        int time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }

    }
}