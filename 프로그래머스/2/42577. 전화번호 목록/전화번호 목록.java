import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook); // 사전순 정렬

        for (int i = 0; i < phoneBook.length - 1; i++) {
            if (phoneBook[i+1].startsWith(phoneBook[i])) {
                return false;
            }
        }

        return true;
    }
}
