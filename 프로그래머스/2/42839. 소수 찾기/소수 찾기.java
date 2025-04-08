import java.util.*;

class Solution {
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] isVisited = new boolean[7];
    
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
 
        return true;
    }
    
    public void dfs(int cnt, String numbers, String num){
        if(cnt>numbers.length()){
            return;
        }    
        for(int i=0; i<numbers.length(); i++){
            if(isVisited[i]){
                continue;
            }
            
            isVisited[i] = true;
            set.add(Integer.parseInt(num+numbers.charAt(i)));
            dfs(cnt+1, numbers, num+numbers.charAt(i));
            isVisited[i] = false;
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        dfs(0,numbers,"");
        
        for (Integer num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
}