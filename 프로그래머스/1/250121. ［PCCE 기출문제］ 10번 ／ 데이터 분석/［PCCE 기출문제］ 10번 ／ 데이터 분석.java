import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> stringToIndex = new HashMap<>();
        stringToIndex.put("code", 0);
        stringToIndex.put("date", 1);
        stringToIndex.put("maximum", 2);
        stringToIndex.put("remain", 3);

        int targetIdx = stringToIndex.get(ext);
        List<int[]> filteredList = new ArrayList<>();

        for (int[] d : data) {
            if (d[targetIdx] < val_ext) {
                filteredList.add(d);
            }
        }

        int sortIdx = stringToIndex.get(sort_by);
        filteredList.sort((a, b) -> a[sortIdx] - b[sortIdx]);

        // List<int[]> → int[][]
        int[][] answer = new int[filteredList.size()][data[0].length];
        for (int i = 0; i < filteredList.size(); i++) {
            answer[i] = filteredList.get(i);
        }

        return answer;
    }
}
