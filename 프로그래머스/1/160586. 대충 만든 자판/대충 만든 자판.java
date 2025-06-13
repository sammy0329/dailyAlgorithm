import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int n = targets.length;
        int[] answer = new int[n];
        Map<Character, Integer> wordMap = new HashMap<>();
        
        for (String k : keymap) {
            for (int i = 0; i < k.length(); i++) {
                char c = k.charAt(i);
                int idx = i + 1;
                
                if (wordMap.containsKey(c)) { // 이미 존재하면 더 작은 값으로 갱신
                    wordMap.put(c, Math.min(wordMap.get(c), idx));
                } else {
                    wordMap.put(c, idx);
                }
            }
        }
        
        A: for(int i=0; i<n; i++){
            String t = targets[i];
            int result = 0;
            
            for(int j=0; j<t.length(); j++){
                char c = t.charAt(j);
                if(!wordMap.containsKey(c)){
                    answer[i] = -1;
                    continue A;
                }
                result += wordMap.get(c);
            }
            answer[i]=result;
        }
        
        return answer;
    }
}
