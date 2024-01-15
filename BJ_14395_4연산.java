package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 입력
 * s, t
 *
 * 출력
 * s를 t로 바꾸는 방법을 출력
 * s와 t가 같은 경우에는 0을, 바꿀 수 없는 경우에는 -1을 출력.
 * 가능한 방법이 여러가지라면, 사전 순으로 앞서는 것을 출력.
 *
 * 문제해결 프로세스
 * 최소 - bfs
 * Node 객체를 만들어서 s에 대한 숫자 정보와 현재까지의 연산 정보를 가지고 있자
 * 연산이 된 수는 set에 넣자
 * 결과가 list 안에 없으면 리스트에 결괏값 넣고, 큐에 집어넣기
 */

// https://www.acmicpc.net/problem/14395
public class BJ_14395_4연산 {
    static long s,t;
    static Set<Long> resultSet = new HashSet<>(); // 이전에 나온 결괏값들 저장 집합\
    static char[] operators = { '*', '+', '-', '/' };

    public static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(s,""));
        resultSet.add(s); // 결괏값 넣기

        while (!q.isEmpty()){
            Node current = q.poll();
            if(current.s == t) {
                System.out.println(current.result);
                return;
            }

            for(int d=0; d<4; d++){ // 각각의 연산 진행해보기
                long check = current.s;
                switch (d){
                    case 0:
                        check *= current.s;
                        break;
                    case 1:
                        check += current.s;
                        break;
                    case 2:
                        check -= current.s;
                        break;
                    case 3:
                        if(current.s == 0) continue;
                        check /= current.s;
                        break;
                }
                if(!resultSet.contains(check)) { // 결괏값에 없다면 resultSet에 넣고 큐에 삽입
                    resultSet.add(check);
                    q.offer(new Node(check, current.result + operators[d]));
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Long.parseLong(st.nextToken());
        t = Long.parseLong(st.nextToken());

        if (s==t) System.out.println(0);
        else bfs();
    }

    static class Node {
        long s;
        String result;
        public Node(long s, String result){
            super();
            this.s = s;
            this.result = result;
        }
    }
}
