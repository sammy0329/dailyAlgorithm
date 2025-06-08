import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        
        boolean[] isVisited = new boolean[10];
        
        for(int n: numbers){
            isVisited[n]=true;
        }
        
        for(int i=0; i<10; i++){
            if(!isVisited[i]){
                answer+=i;
            }
        }
        
        return answer;
    }
}