import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        Set<Integer>[] winList = new HashSet[n + 1];
        Set<Integer>[] loseList = new HashSet[n + 1];
        
        // winList와 loseList 초기화
        for (int i = 0; i <= n; i++) {
            winList[i] = new HashSet<>();
            loseList[i] = new HashSet<>();
        }

        // 결과에 따라 winList와 loseList 관리
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            winList[winner].add(loser);
            loseList[loser].add(winner);
        }
        
        // 승/패 관계 전파
        for (int i = 1; i <= n; i++) {
            for (int winner : winList[i]) {
                loseList[winner].addAll(loseList[i]);
            }
            for (int loser : loseList[i]) {
                winList[loser].addAll(winList[i]);
            }
        }

        // 순위 확정 가능한 선수 수 확인
        for (int i = 1; i <= n; i++) {
            if (winList[i].size() + loseList[i].size() == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
