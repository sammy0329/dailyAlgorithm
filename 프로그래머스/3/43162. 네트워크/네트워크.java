import java.util.*;

class Solution {
    static boolean[] isVisited;
    
    public void bfs(int[][] computers, int n, int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisited[start] = true;
        
        while(!q.isEmpty()){
            int current = q.poll();
            
            for(int i=0; i<n; i++){
                if(computers[current][i] == 1 && !isVisited[i]){
                    isVisited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(!isVisited[i]){
                bfs(computers, n, i);
                answer++;
            }
        }
        
        return answer;
    }
}