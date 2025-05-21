class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoTime = toSeconds(video_len);
        int currentTime = toSeconds(pos);
        int opStart = toSeconds(op_start);
        int opEnd = toSeconds(op_end);

        for (String cmd : commands) {
            // 오프닝 시간대일 경우, 오프닝 건너뛰기
            if (currentTime >= opStart && currentTime <= opEnd) {
                currentTime = opEnd;
            }

            if (cmd.equals("prev")) {
                currentTime = Math.max(0, currentTime - 10);
            } else if (cmd.equals("next")) {
                currentTime = Math.min(videoTime, currentTime + 10);
            }
        }

        // 오프닝 시간대인지 마지막으로 다시 체크
        if (currentTime >= opStart && currentTime <= opEnd) {
            currentTime = opEnd;
        }

        return toTimeString(currentTime);
    }

    // "mm:ss" → 초 단위로 변환
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 초 → "mm:ss" 형식으로 변환
    private String toTimeString(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
