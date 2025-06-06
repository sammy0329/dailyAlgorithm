class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public int solution(String[][] board, int h, int w) {
        int n = board.length;
        int count = 0;
        
        for(int d=0; d<4; d++){
            int nr = h+dr[d];
            int nc = w+dc[d];
            
            if(nr<0 || nr>n-1 || nc<0 || nc>n-1){
                continue;
            }
            
            if(board[nr][nc].equals(board[h][w])){
                count++;
            }
        }
        
        return count;
    }
}