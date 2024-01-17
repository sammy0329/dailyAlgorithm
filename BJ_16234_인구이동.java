package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제이해
 * NxN 땅
 * 각각의 땅에는 나라가 하나씩 존재
 * A[r][c]먕 살고 있음.
 * 국경선은 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 염.
 * 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작.
 * 연합 : 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수) -> 소수점 버림
 * 연합 해체 후 모든 국경선 닫음
 *
 * 출력
 * 인구이동이 며칠 동안 발생하는가?
 *
 * 문제해결 프로세스
 * bfs로 연결된것들 중 차이가 L이상 R이하인지 확인하고, 리스트에 저장 및 해당 값들 합 sum에 저장
 * 해당 리스트값이 1보다 크면 인구 이동 로직 진행
 * 더이상 인구이동 없을 때 까지 진행
 */
public class BJ_16234_인구이동 {
    static int N,L,R;
    static int[][] arr;
    static boolean[][] isVisited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static ArrayList<Node> list;

    public static int move(){
        int result = 0;

        while (true){
            boolean isMove = false;
            isVisited = new boolean[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!isVisited[i][j]){
                        int sum = bfs(i,j);
                        if(list.size()>1) {
                            isMove = true;
                            calcMove(sum);
                        }
                    }
                }
            }
            if (!isMove) return result;
            result++;
        }
    }

    public static void calcMove(int sum){ // 인구 이동 계산
        int avg = sum/ list.size();
        for(Node node:list){
            arr[node.r][node.c] = avg;
        }
    }

    public static int bfs(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        list = new ArrayList<>();
        int sum = arr[r][c];

        q.offer(new Node(r, c));
        list.add(new Node(r, c));
        isVisited[r][c] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N || isVisited[nr][nc]) continue;

                int subtract = Math.abs(arr[current.r][current.c] - arr[nr][nc]);
                if(L <= subtract && subtract <= R) {
                    sum += arr[nr][nc];

                    q.offer(new Node(nr, nc));
                    list.add(new Node(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());

    }
    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
