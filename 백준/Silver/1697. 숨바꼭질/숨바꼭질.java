import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//@formatter:off
/**
 * [문제 이해]
 * 수빈이는 현재 점 N에 있음. (0<=N<=100,000)
 * 동생은 점 K에 있음. (0<=K<=100,000)
 * 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동.
 *
 * 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인가?
 *
 * [입력]
 * 수빈이 위치 N, 동생 위치 K
 *
 * [출력]
 * 수빈이가 동생을 찾는 가장 빠른 시간
 *
 * [문제 해결 프로세스]
 * 수빈이의 위치가 N이라고 하면,
 * 0초 : N 위치
 * 1초 : N-1, N+1, N*2 위치
 * ...
 * 이를 배열 0~100,000까지 만들고 수빈이의 위치를 배열의 인덱스로 생각하면서 시간이 지날 때마다 배열에 반영
 * 0초일 때, N위치에 1을 저장하고, 큐에 삽입
 * 1초일 때, N-1,N+1,N*2를 for문을 통해 돌면서 해당 인덱스에 1+1인 2 저장
 * 2초일 때, 큐에 있는 값들에 -1,+1,*2 연산 후, 해당 인덱스에 2+1인 3 저장
 * 이렇게 q에서 뽑은 값이 K일 때까지 진행하고, 만약 K라면 result를 couter[current]-1로 만들어주고 return
 *
 */
//@formatter:on

public class Main {

    public static int N, K, currentN, counter[];
    public static int[] check = new int[]{-1, 1, 2};

    public static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        counter[N] = 1;
        q.offer(N);

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == K) {
                return counter[current] - 1;
            }

            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    currentN = current * 2;
                } else {
                    currentN = current + check[i];
                }

                if (currentN >= 0 && currentN <= 100_000 && counter[currentN] == 0) {
                    q.offer(currentN);
                    counter[currentN] = counter[current] + 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        counter = new int[100_001]; // 0<=N,K<=100,000
        System.out.println(bfs());
    }
}