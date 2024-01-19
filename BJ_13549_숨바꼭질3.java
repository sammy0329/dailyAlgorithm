package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이해
 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000), 동생은 점 K(0 ≤ K ≤ 100,000)
 * 걸을 때 - X-1, X+1
 * 순간이동시 0초 후에 2^X
 *
 */

// https://www.acmicpc.net/problem/13549
public class BJ_13549_숨바꼭질3 {
    static int N,K;
    static int[] isVisited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(N, 1));
        isVisited[N]=1;

        while (!q.isEmpty()) {
            Point current = q.poll();

            if(current.x+1>=0 && current.x+1<=100000){ // 앞으로 한칸
                if(isVisited[current.x+1] == 0 || isVisited[current.x+1] > current.time+1){
                    isVisited[current.x+1] = current.time+1;
                    q.add(new Point(current.x + 1, current.time + 1));
                }
            }

            if(current.x-1>=0 && current.x-1<=100000){ // 뒤로 한칸
                if(isVisited[current.x-1] == 0 || isVisited[current.x-1] > current.time+1) {
                    isVisited[current.x - 1] = current.time + 1;
                    q.add(new Point(current.x - 1, current.time + 1));
                }
            }

            if(current.x*2>=0 && current.x*2<=100000){ // 순간이동
                if(isVisited[current.x*2] == 0 || isVisited[current.x*2] > current.time) {
                    isVisited[current.x*2] = current.time;
                    q.add(new Point(current.x*2, current.time));
                }
            }
        }

        System.out.println(isVisited[K]-1);

    }

    private static class Point{
        int x;
        int time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }

    }
}
