import java.util.*;

public class Solution {
    public int solution(int[] mats, String[][] park) {        
        int n = park.length;
        int m = park[0].length;

        Arrays.sort(mats);  // 오름차순 정렬 후 큰 돗자리부터 확인
        
        for (int k = mats.length - 1; k >= 0; k--) {
            int matSize = mats[k];

            for (int r = 0; r <= n - matSize; r++) {
                for (int c = 0; c <= m - matSize; c++) {
                    if (canPlace(park, r, c, matSize)) {
                        return matSize;
                    }
                }
            }
        }

        return -1;
    }

    // 해당 돗자리를 깔 수 있는지 확인 메서드 
    private boolean canPlace(String[][] park, int startR, int startC, int size) {
        for (int i = startR; i < startR + size; i++) {
            for (int j = startC; j < startC + size; j++) {
                if (!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}
