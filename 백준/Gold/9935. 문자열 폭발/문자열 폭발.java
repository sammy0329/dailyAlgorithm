import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문제
 * 폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며, 남은 문자열은 합쳐지게 됨
 * 문자열이 폭발 문자열을 포함하고 있다면 -> 모든 폭발 문자열이 폭발하고, 남은 문자열을 순서대로 이어 붙여 새로운 문자열 만듦
 * 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있음
 * 폭발은 폭발 문자열이 문자열에 없을 때까지 계속 진행
 *
 * 입력
 * 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같음
 * 폭발 문자열 길이는 1보다 크거나 같고, 36보다 작거나 같음
 * 두 문자열 모두 알파벳/숫자로만 이루어짐
 *
 * 출력
 * 모든 폭발 후, 남은 문자열 출력하기
 *
 * 문제해결 프로세스
 * 1. 스택으로 주어진 문자열 하나씩 집어넣기
 * 2. 폭발 문자열과 비교해서 맞으면 pop 시키고, 다음 단어 집어넣기
 * 3. 반복 후, 모든 stack pop 시켜서 StringBuilder에 넣어두고, reverse해서 출력하기
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String inputString = br.readLine();
        String checkString = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(char ch : inputString.toCharArray()){
            // 한 단어씩 stack에 집어넣기
            stack.push(ch);

            // stack 크기가 폭발 문자열 크기보다 크거나 같으면 폭발 문자열이 있을 수 있으므로 flag를 활용해 검증 진행
            if(stack.size() >= checkString.length()){
                boolean flag = true;

                for(int i=0; i<checkString.length(); i++){
                    if(stack.get(stack.size()-checkString.length()+i)!=checkString.charAt(i)){
                        flag = false;
                        break;
                    }
                }
                // 폭발물이 있다면, pop 시켜서 폭발시키기
                if(flag){
                   for(int i=0; i<checkString.length(); i++){
                       stack.pop();
                   }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else{
            // StringBuilder에 Stack을 pop 시킨 값들을 넣고 reverse하여 출력하기
            while (!stack.isEmpty()){
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }

    }
}