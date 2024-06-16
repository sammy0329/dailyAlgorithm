import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * BFS/DFS 구하기
 *
 * 입력
 * 정점의 개수 N
 * 간선의 개수 M
 * 정점의 번호 V
 * M개의 줄에 두 정점의 번호가 주어짐
 * (양방향 간선)
 *
 * 출력
 * DFS,BFS 수행 결과
 *
 * 문제해결 프로세스
 * DFS
 * 재귀를 통한 구현
 * BFS
 * 큐를 이용한 구현
 */
public class Main {
    static int N,M,V;
    static List<List<Integer>> graph;
    static boolean[] isVisited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        isVisited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }

        dfs(V);
        Arrays.fill(isVisited, false);
        sb.append("\n");
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int start){
        isVisited[start]=true;
        sb.append(start).append(" ");
        for(Integer element: graph.get(start)) {
            if(!isVisited[element]){
                dfs(element);
            }
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start] = true;

        while (!q.isEmpty()){
            Integer current = q.poll();
            sb.append(current).append(" ");

            for(Integer element: graph.get(current)){
                if(!isVisited[element]){
                    q.offer(element);
                    isVisited[element]=true;
                }
            }
        }
    }
}