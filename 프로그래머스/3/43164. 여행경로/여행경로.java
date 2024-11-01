import java.util.*;

class Solution {
    
    boolean[] isVisited;
    int targetCnt;
    String[] answer;
    List<String> path = new ArrayList<>();
    
    public boolean dfs(String[][] tickets, int cnt) {
        if (cnt == targetCnt) {
            answer = path.toArray(new String[0]);
            return true; // 경로를 찾았으므로 true 반환
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!isVisited[i] && path.get(cnt - 1).equals(tickets[i][0])){
                isVisited[i] = true;
                path.add(tickets[i][1]);
                
                if (dfs(tickets, cnt + 1)) {
                    return true; // 경로를 찾은 경우 종료
                }
                
                path.remove(path.size() - 1); // 백트래킹
                isVisited[i] = false;
            }
        }
        return false;
    }
    
    public String[] solution(String[][] tickets) {
        targetCnt = tickets.length + 1;
        isVisited = new boolean[tickets.length];
        
        // 티켓을 도착지 기준으로 오름차순 정렬
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        path.add("ICN"); // 인천부터 시작
        dfs(tickets, 1);
        
        return answer;
    }
}
