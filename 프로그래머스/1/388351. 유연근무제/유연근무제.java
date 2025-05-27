class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            int scheduled = schedules[i];
            int hour = scheduled / 100;
            int minute = scheduled % 100;
            
            // 허용 시간 계산 (10분 추가)
            minute += 10;
            if (minute >= 60) {
                hour += 1;
                minute -= 60;
            }
            int allowedTime = hour * 100 + minute;
            
            boolean isGift = true;
            
            for (int j = 0; j < 7; j++) {
                // 주말(토요일, 일요일) 체크
                int currentDay = (startday - 1 + j) % 7;
                if (currentDay >= 5) { // 5=토요일, 6=일요일 (0:월 ~6:일)
                    continue;
                }
                
                int logTime = timelogs[i][j];
                if (logTime > allowedTime) {
                    isGift = false;
                    break;
                }
            }
            
            if (isGift) {
                answer++;
            }
        }
        
        return answer;
    }
}
