import java.util.*;

class Point {
    int r;
    int c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] isVisited = new boolean[n][m];
        
        Queue<Point> q = new ArrayDeque<>();
        isVisited[0][0] = true;
        q.offer(new Point(0, 0));
        
        int depth = 1;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int i=0; i<size; i++) {
                Point current = q.poll();
                
                if(current.r == n-1 && current.c == m-1) {
                    return depth;
                }
                
                for(int d=0; d<4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    
                    if(nr < 0 || nc < 0 || nr >= n || nc >= m || 
                       maps[nr][nc] == 0 || isVisited[nr][nc]) {
                        continue;
                    }
                    
                    isVisited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
            
            depth++;
        }
        
        return -1;
    }
}