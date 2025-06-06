import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();

        // terms를 Map에 저장
        for (String term : terms) {
            String[] split = term.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]));
        }

        // 오늘 날짜를 총 일수로 변환
        int todayTotal = convertToDays(today);

        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            String date = split[0];
            String type = split[1];

            int period = termMap.get(type);

            // 수집 일자를 총 일수로 바꾸고 유효기간 더하기
            int dateTotal = convertToDays(date) + (period * 28) - 1;

            // 오늘이 더 크거나 같으면 파기 대상
            if (todayTotal > dateTotal) {
                result.add(i + 1);
            }
        }
        
        // 결과 리스트를 배열로 변환
        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        
        return arr;
    }

    private int convertToDays(String date) { // 날짜를 총 일수로 변환하는 함수
        String[] split = date.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        
        return (year * 12 * 28) + (month * 28) + day;
    }
}
