import java.util.*;

class Solution {
    static int[][] arr;
    
    public int bfs(int n, int start) {
        boolean[] isVisited = new boolean[n+1];
        int cnt = 1;
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start] = true;
        
        while(!q.isEmpty()) {
            int current = q.poll();
            
            for(int i=1; i<=n; i++) {
                if(isVisited[i]) continue;
                if(arr[current][i] == 1) {
                    q.offer(i);
                    isVisited[i] = true;
                    cnt++;
                }
            }
        }
        return Math.abs(cnt - (n - cnt));
    }
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        arr = new int[n+1][n+1];
        
        // 인접행렬 만들기
        for(int i=0; i<wires.length; i++) {
            int x = wires[i][0];
            int y = wires[i][1];
            
            arr[x][y] = 1;
            arr[y][x] = 1;
        }
        
        for(int i=0; i<wires.length; i++) {
            int x = wires[i][0];
            int y = wires[i][1];
            
            // 간선 끊기
            arr[x][y] = 0;
            arr[y][x] = 0;
            
            // 두 전력망의 노드 개수 차이 계산
            answer = Math.min(answer, bfs(n, x));
            
            // 간선 복구
            arr[x][y] = 1;
            arr[y][x] = 1;
        }
        
        return answer;
    }
}