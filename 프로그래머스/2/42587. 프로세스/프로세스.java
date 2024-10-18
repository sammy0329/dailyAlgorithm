import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        HashSet<Integer> set = new HashSet<>();
        int answer = 0;

        // PriorityQueue에 priorities 값 넣기
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
        }

        A:
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (!set.contains(i) && priorities[i] == pq.peek()) {
                    pq.poll();
                    set.add(i);
                    answer++;

                    if (i == location) {
                        break A;
                    }
                }
            }
        }
        
        return answer;
    }
}