import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean exists = false;
        for (String w : words) {
            if (w.equals(target)) {
                exists = true;
                break;
            }
        }
        if (!exists) return 0;

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        q.offer(begin);
        visited.add(begin);

        int depth = 0;

        while (!q.isEmpty()) {
            int cnt = q.size();

            for (int i = 0; i < cnt; i++) {
                String cur = q.poll();
                if (cur.equals(target)) return depth;

                for (String next : words) {
                    if (visited.contains(next)) continue;
                    if (!diffOne(cur, next)) continue;

                    visited.add(next);
                    q.offer(next);
                }
            }
            depth++;
        }
        return 0;
    }

    private boolean diffOne(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return diff == 1;
    }
}
