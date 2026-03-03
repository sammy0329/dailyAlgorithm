class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        // 깊이 0, 현재 합계 0에서 시작
        dfs(numbers, target, 0, 0);
        return answer;
    }

    public void dfs(int[] numbers, int target, int depth, int sum) {
        // 1. 기저 조건: 모든 숫자를 다 사용했을 때
        if (depth == numbers.length) {
            // 최종 합이 target과 같으면 정답 카운트 증가
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 2. 재귀 호출: 현재 숫자를 더하는 경우
        dfs(numbers, target, depth + 1, sum + numbers[depth]);
        
        // 3. 재귀 호출: 현재 숫자를 빼는 경우
        dfs(numbers, target, depth + 1, sum - numbers[depth]);
    }
}