import java.util.*;
import java.io.*;

class Solution {
    static int[][] arr = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    static int maxScore = -1;

    public int[] solution(int[] answers) {
        int[] answerPoints = new int[3];
        List<Integer> resultList = new ArrayList<>();

        // 각 수포자의 정답 점수 계산
        for (int s = 0; s < 3; s++) {
            int answerCnt = 0;

            for (int i = 0; i < answers.length; i++) {
                if (arr[s][i % arr[s].length] == answers[i]) {
                    answerCnt++;
                }
            }
            answerPoints[s] = answerCnt;
            maxScore = Math.max(maxScore, answerCnt);
        }

        // 최대 점수를 받은 수포자 찾기 및 resultList에 추가
        for (int i = 0; i < 3; i++) {
            if (answerPoints[i] == maxScore) {
                resultList.add(i + 1); // 수포자 번호는 1번부터 시작
            }
        }

        // 결과 리스트를 배열로 변환
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
        
    }
}
