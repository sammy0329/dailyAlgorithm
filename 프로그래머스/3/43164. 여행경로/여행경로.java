import java.util.*;

class Solution {
    boolean[] visited;
    String[] answer;
    boolean found = false;

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a,b) -> {
            if (!a[0].equals(b[0])) return a[0].compareTo(b[0]);
            return a[1].compareTo(b[1]);
        });

        visited = new boolean[tickets.length];
        answer = new String[tickets.length + 1];
        answer[0] = "ICN";

        dfs(tickets, "ICN", 0);

        return answer;
    }

    private void dfs(String[][] tickets, String now, int depth) {
        if (depth == tickets.length) {
            found = true;
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(now)) {
                visited[i] = true;
                answer[depth + 1] = tickets[i][1];
                dfs(tickets, tickets[i][1], depth + 1);
                if (found) return;
                visited[i] = false;
            }
        }
    }
}
