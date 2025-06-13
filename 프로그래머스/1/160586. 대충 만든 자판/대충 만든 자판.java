import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int[] charMap = new int[26]; // 'A' ~ 'Z'만 사용
        Arrays.fill(charMap, Integer.MAX_VALUE);

        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int idx = c - 'A';
                charMap[idx] = Math.min(charMap[idx], i + 1);
            }
        }

        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                char c = targets[i].charAt(j);
                int idx = c - 'A';
                if (idx < 0 || idx >= 26 || charMap[idx] == Integer.MAX_VALUE) {
                    sum = -1;
                    break;
                }
                sum += charMap[idx];
            }
            answer[i] = sum;
        }

        return answer;
    }
}