//@formatter:off
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer; /**
 * [문제]
 * 각 건물 옥상에서 양 옆에 존재하는 건물의 옆을 몇 개 볼 수 있을까?
 * 현재 건물의 높이가 L이면, L보다 큰 곳의 건물만 볼 수 있음
 * [입력]
 * 첫번째 줄 : 건물의 개수 N (1<=N<=100,000)
 * 두번째 줄 : N개의 건물 높이(L)가 공백으로 구분되어 주어짐 (1<=L<=100,000)
 * [출력]
 * i(1<=i<N)번째 건물에서 볼 수 있는 건물의 개수 출력
 * 볼 수 있는 건물의 개수가 1개 이상이면, i번째 건물에서 거리가 가장 가까운 건물의 번호 중 작은 번호 출력
 *
 * [문제 해결 프로세스]
 * N^2은 시간 초과 발생하기 때문에 X
 * 스택을 활용해서 왼쪽->오른쪽 / 오른쪽->왼쪽 탐색 진행
 * 1. 건물의 개수 N 입력 받기
 * 2. 건물의 높이를 저장할 배열 heights 생성 및 입력 받기
 * 3. 왼쪽,오른쪽에서 보이는 건물 개수와 가까운 건물 번호 저장용 배열 생성하기
 * 4. 왼쪽에서 보이는 건물 계산하기 위한 스택 생성
 * 4-1. 스택에서 현재 건물보다 낮거나 같은 건물 제거
 * 4-2. 스택이 비어있지 않다면 가장 가까운 왼쪽 건물 기록 (보이는 건물이 있다면 기존 인덱스+1, 아니면 -1 저장)
 * 4-3. 현재 스택의 크기로 보이는 건물 개수 저장
 * 4-4. 현재 건물 스택에 추가
 * 5. 오른쪽에서 보이는 건물 계산하기 위한 스택 생성
 * 5-1. 스택에서 현재 건물보다 낮거나 같은 건물 제거
 * 5-2. 스택이 비어있지 않다면 가장 가까운 오른쪽 건물 기록 (보이는 건물이 있다면 기존 인덱스+1, 아니면 -1 저장)
 * 5-3. 현재 스택의 크기로 보이는 건물 개수 저장
 * 5-4. 현재 건물 스택에 추가
 * 6. 결과 출력
 * 6-1. 총 보이는 건물 개수 계산
 * 6-2. 보이는 건물이 하나 이상인 경우 가장 가까운 건물 번호 결정
 * 7. 결과 출력
 */
//@formatter:on

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 건물의 개수 N 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 2. 건물의 높이를 저장할 배열 heights 생성 및 입력 받기
        int[] heights = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 3. 왼쪽,오른쪽에서 보이는 건물 개수와 가까운 건물 번호 저장용 배열 생성하기
        int[] leftVisibleCnt = new int[n];
        int[] rightVisibleCnt = new int[n];
        int[] leftNearTop = new int[n];
        int[] rightNearTop = new int[n];

        // 4. 왼쪽에서 보이는 건물 계산하기 위한 스택 생성
        Stack<Integer> leftStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 4-1. 스택에서 현재 건물보다 낮거나 같은 건물 제거
            while (!leftStack.isEmpty() && heights[leftStack.peek()] <= heights[i]) {
                leftStack.pop();
            }

            // 4-2. 스택이 비어있지 않다면 가장 가까운 왼쪽 건물 기록 (보이는 건물이 있다면 기존 인덱스+1, 아니면 -1 저장)
            if (!leftStack.isEmpty()) {
                leftNearTop[i] = leftStack.peek() + 1;
            } else {
                leftNearTop[i] = -1; // 보이는 건물이 없는 경우
            }

            // 4-3. 현재 스택의 크기로 보이는 건물 개수 저장
            leftVisibleCnt[i] = leftStack.size();

            // 4-4. 현재 건물 스택에 추가
            leftStack.push(i);
        }

        // 5. 오른쪽에서 보이는 건물 계산하기 위한 스택 생성
        Stack<Integer> rightStack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 5-1. 스택에서 현재 건물보다 낮거나 같은 건물 제거
            while (!rightStack.isEmpty() && heights[rightStack.peek()] <= heights[i]) {
                rightStack.pop();
            }

            // 5-2. 스택이 비어있지 않다면 가장 가까운 오른쪽 건물 기록 (보이는 건물이 있다면 기존 인덱스+1, 아니면 -1 저장)
            if (!rightStack.isEmpty()) {
                rightNearTop[i] = rightStack.peek() + 1;
            } else {
                rightNearTop[i] = -1; // 보이는 건물이 없는 경우
            }

            // 5-3. 현재 스택의 크기로 보이는 건물 개수 저장
            rightVisibleCnt[i] = rightStack.size();

            // 5-4. 현재 건물 스택에 추가
            rightStack.push(i);
        }

        // 6. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // 6-1. 총 보이는 건물 개수 계산
            int totalVisible = leftVisibleCnt[i] + rightVisibleCnt[i];
            sb.append(totalVisible);

            // 6-2. 보이는 건물이 하나 이상인 경우 가장 가까운 건물 번호 결정
            if (totalVisible > 0) {
                if (leftNearTop[i] == -1) {
                    // 왼쪽에서 보이는 건물이 없는 경우
                    sb.append(" ").append(rightNearTop[i]);
                } else if (rightNearTop[i] == -1) {
                    // 오른쪽에서 보이는 건물이 없는 경우
                    sb.append(" ").append(leftNearTop[i]);
                } else {
                    // 양쪽 모두 보이는 경우 거리 비교
                    int leftDistance = Math.abs(i - (leftNearTop[i] - 1));
                    int rightDistance = Math.abs(i - (rightNearTop[i] - 1));
                    if (leftDistance <= rightDistance) {
                        sb.append(" ").append(leftNearTop[i]);
                    } else {
                        sb.append(" ").append(rightNearTop[i]);
                    }
                }
            }

            sb.append("\n");
        }

        // 7. 결과 출력
        System.out.print(sb);
    }
}