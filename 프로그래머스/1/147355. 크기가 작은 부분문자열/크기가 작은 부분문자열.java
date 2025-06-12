class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int n = p.length();

        for (int i = 0; i <= t.length() - n; i++) {
            String sub = t.substring(i, i + n);
            if (sub.compareTo(p) <= 0) {
                answer++;
            }
        }

        return answer;
    }
}
