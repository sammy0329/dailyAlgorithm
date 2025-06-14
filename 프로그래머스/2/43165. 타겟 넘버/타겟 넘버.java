class Solution {
    static int answer;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, numbers.length, 0, 0, target);
        return answer;
    }
    
    public void dfs(int[] numbers, int n, int cnt, int sum, int target){
        if(cnt==n){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        dfs(numbers, n, cnt+1, sum+numbers[cnt], target);
        dfs(numbers, n, cnt+1, sum-numbers[cnt], target);
    }
}