class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n]; // 방문 여부 체크

        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 컴퓨터라면 새로운 네트워크의 시작점
            if (!visited[i]) {
                dfs(i, n, computers, visited);
                answer++; // DFS가 끝나면 네트워크 한 개를 다 찾은 것
            }
        }

        return answer;
    }

    private void dfs(int i, int n, int[][] computers, boolean[] visited) {
        visited[i] = true; // 현재 컴퓨터 방문 처리

        for (int j = 0; j < n; j++) {
            // 1. 자기 자신이 아니고 2. 연결되어 있으며 3. 아직 방문하지 않았다면
            if (i != j && computers[i][j] == 1 && !visited[j]) {
                dfs(j, n, computers, visited);
            }
        }
    }
}