import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nameToIndex.put(friends[i], i);
        }

        int[][] arr = new int[n][n];
        int[] giftJisoo = new int[n];

        for (String s : gifts) {
            String[] p = s.split(" ");
            int give = nameToIndex.get(p[0]);
            int take = nameToIndex.get(p[1]);

            arr[give][take]++;
            giftJisoo[give]++;
            giftJisoo[take]--;
        }

        int[] nextMonthGifts = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (arr[i][j] > arr[j][i]) {
                    nextMonthGifts[i]++;
                } else if (arr[i][j] == arr[j][i]) {
                    if (giftJisoo[i] > giftJisoo[j]) {
                        nextMonthGifts[i]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int x : nextMonthGifts) {
            answer = Math.max(answer, x);
        }

        return answer;
    }
}
