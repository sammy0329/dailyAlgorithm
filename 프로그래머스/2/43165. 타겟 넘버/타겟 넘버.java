import java.util.*;

class Solution {
    static char[] ch = {'+','-'};
    static char[] picked;
    static int answer;
    
    public void dfs(int[] numbers, int n, int target, int cnt){
        if(cnt == n){
            int result = 0;
            for(int i=0; i<n; i++){
                if(picked[i] == '+'){
                    result += numbers[i];
                }else if(picked[i] == '-'){
                    result -= numbers[i];
                }
            }
            if(result == target){
                answer++;
            }
            return;
        }
        
        // 현재 위치(cnt)의 숫자에 대해서만 + 또는 - 연산자 선택
        picked[cnt] = '+';
        dfs(numbers, n, target, cnt+1);
        
        picked[cnt] = '-';
        dfs(numbers, n, target, cnt+1);
    }
    
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        picked = new char[n];
        dfs(numbers, n, target, 0);
        return answer;
    }
}