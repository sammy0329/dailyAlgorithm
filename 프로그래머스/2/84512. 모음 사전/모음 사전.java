import java.util.*;

class Solution {
    List<String> dictionary = new ArrayList<>();
    String vowels = "AEIOU";
    
    // DFS로 모든 가능한 단어 생성
    public void generateWords(String current, int maxLength) {
        if (current.length() > 0) {
            dictionary.add(current);
        }
        
        if (current.length() == maxLength) {
            return;
        }
        
        for (int i = 0; i < vowels.length(); i++) {
            generateWords(current + vowels.charAt(i), maxLength);
        }
    }
    
    public int solution(String word) {
        generateWords("", 5);
        
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(word)) {
                return i + 1;
            }
        }
        
        return -1;
    }
}