package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 수빈이가 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 됨.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동
 *
 * 입력
 * 수빈이가 있는 위치 N, 동생이 있는 위치 K
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간 출력
 * 어떻게 이동해야 하는지 공백으로 구분해 출력
 *
 * 문제해결 프로세스
 * bfs 돌릴 때, 자신의 직전 값을 저장하는 배열 생성, 시간 방문 배열 생성
 * K를 찾으면 stack으로 역추척 하기
 *
 */

// https://www.acmicpc.net/problem/13913
public class BJ_13913_숨바꼭질4 {
    static int N,K;
    static int[] parent = new int[100001];
    static int[] time = new int[100001];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(time[K]-1);

        // 역으로 찾아감
        int idx = K;

        Stack<Integer> stack = new Stack<>();
        stack.push(K);

        // stack에 넣기
        while (idx!=N){
            stack.push(parent[idx]);
            idx= parent[idx];
        }

        // stack에서 꺼내기
        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    public static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        time[N] = 1;

        while (!q.isEmpty()){
            int current = q.poll();

            if(current == K) return;

            if (current * 2 <= 100000 && time[current * 2] == 0) { // X*2 이동
                time[current * 2] = time[current] + 1;
                parent[current * 2] = current;
                q.offer(current * 2);
            }
            if (current + 1 <= 100000 && time[current + 1] == 0) { // X+1 이동
                time[current + 1] = time[current] + 1;
                parent[current + 1] = current;
                q.offer(current + 1);
            }
            if (current - 1 >= 0 && time[current - 1] == 0) { // X-1 이동
                time[current - 1] = time[current] + 1;
                parent[current - 1] = current;
                q.offer(current - 1);
            }
        }
    }

}
