package dailyProblems;

// https://www.acmicpc.net/problem/2529

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력
 * 부등호 문자의 개수를 나타내는 정수 k (2 <= k <= 9)
 * k개의 부등호 기호가 하나의 공백을 두고 한 줄에 제시
 *
 * 출력
 * 부등호 관계를 만족하는 k+1 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력 (단, 첫 자리가 0인 경우도 정수에 포함)
 *
 * 문제해결 프로세스
 * 순서의 중요 -> 순열진행
 * 종료조건 K+1개의 숫자가 만들어 졌을때
 * 0~9에서 고름
 * 부등호 조건을 만족하는 모든 결괏값을 리스트에 넣고 0번째 인덱스를 꺼내면 최솟값, 마지막 인덱스 값을 꺼내면 최댓값
 *
 */

public class BJ_2529_부등호 {
    static int K;
    static char[] signArr;
    static String num;
    static boolean [] isVisited = new boolean[10]; // 1~9까지 방문 체크
    static List<String> list = new ArrayList<>();

    public static void dfs(String num, int idx){
        // 기저조건 : 주어진 부등호의 모든 조건을 완성하면 리턴
        if(idx == K+1){
            // 부등호 조건을 성립하는 모든 경우의 수 등록
            list.add(num);
            return;
        }

        for(int i=0; i<10; i++){
            if(isVisited[i]) continue; // 이미 방문한 숫자는 패스
            if(idx==0 || checkCondition(num.charAt(idx-1)-'0',i,signArr[idx])){
                isVisited[i]=true;
                dfs(num+i, idx+1);
                isVisited[i]=false;
            }
        }
    }

    // 부등호 조건들을 확인하는 함수
    public static boolean checkCondition(int num1, int num2, char inequality){
        if(inequality == '>') return num1>num2;
        else return num1<num2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        signArr = new char[K+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=K;i++){
            signArr[i] = st.nextToken().charAt(0);
        }

        dfs("",0);

        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));

    }
}
