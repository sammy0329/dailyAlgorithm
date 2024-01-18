package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이해
 * 수빈이는 현재점 N(0<=N<=100,000)에 있고, 동생은 점 K(0<=K<=100,000)에 있다.
 * 걷기 : 1초 후 (X-1 혹은 X+1)
 * 순간이동 : 1초 후 (2*X)
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇초 후인가? 몇가지 방법이 있는가?
 *
 * 문제해결 프로세스
 * -1,+1,*2로 이동
 * 첫방문(move[goNum]==0), 이전 방문보다 더 빠르게 도착했을 때 (move[goNum]>=move[current]+1)일 때 다음 탐색 허용
 * 이전 탐색보다 빠르게 도착시 최솟값을 갱신하고 카운트
 * 최소 시간보다 많이 움직이는 경우 강제 break
 */

// https://www.acmicpc.net/problem/12851
public class BJ_12851_숨바꼭질2 {
    static int N,K;
    static int[] move = new int[100001];
    static int[] dir = {-1,1,2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int resultTime = Integer.MAX_VALUE;
        int resultCnt = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);

        while(!q.isEmpty()){
            int current = q.poll();

            if(resultTime<move[current]) break;
            if(move[current]<=resultTime && current == K){
                resultTime = move[current];
                resultCnt++;
            }

            for(int d=0;d<3;d++){
                int goNum=current;

                if(d==2) goNum*=dir[d];
                else goNum+=dir[d];

                if (goNum>=0 && goNum <100001) {
                    if(move[goNum] ==0 || move[goNum] >= move[current] + 1) {
                        move[goNum] = move[current]+1;
                        q.add(goNum);
                    }
                }
            }
        }
        System.out.println(resultTime);
        System.out.println(resultCnt);
    }

}
