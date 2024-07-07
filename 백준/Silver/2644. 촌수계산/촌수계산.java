import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제
 * 부모-자식 : 1촌, 아버지-할아버지 : 1촌, 자식-할아버지 : 2촌 ..
 *
 * 입력
 * [첫째 줄]
 * 전체 사람 수 n
 * [둘째 줄]
 * 촌수를 계산해야 하는 서로 다른 두 사람 번호 주어짐
 * [셋째 줄]
 * 부모 자식들 간의 관계 개수 m
 * [넷째 줄 ~]
 * 부모 자식간의 관계를 나타내는 두 번호 x,y
 *
 * 출력
 * 요구한 두 사람의 촌수 (촌수 계산 불가시 -1)
 *
 * 문제해결 프로세스
 * 1. 부모-자식 관계 입력 받을 때, 인접 리스트를 만든다.
 * 2. isVisited 1차원 방문 배열을 만든다.
 * 3. start,end,cnt를 인자로 받는 dfs 함수를 만든다.
 * 3-1. 기저 조건은 start와 end가 같아지는 지점
 * 3-2. start 방문 체크 진행
 * 3-3. 인접 리스트를 돌며, 방문하지 않은 곳 중 다음 start를 찾고, cnt를 1 증가시키기
 */

public class Main {
    static List<List<Integer>> list = new ArrayList<>();
    static int N,M,X,Y,result;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        init();
        dfs(X,Y,0);
        System.out.println(result);
    }

    private static void dfs(int start, int end, int cnt) {
       if(start == end){ // 기저 조건 : 시작점이 종착점과 같을 때
           result = cnt;
           return;
       }

       isVisited[start] = true;

       for(int i=0; i<list.get(start).size(); i++){
           int next = list.get(start).get(i);
           if(!isVisited[next]){
               dfs(next,end,cnt+1);
           }
       }

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        result = -1;
        N = Integer.parseInt(br.readLine());

        // N개 만큼 인접 리스트 생성
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        // 방문 배열 초기화
        isVisited = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        // 인접 리스트 생성
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }
    }

}