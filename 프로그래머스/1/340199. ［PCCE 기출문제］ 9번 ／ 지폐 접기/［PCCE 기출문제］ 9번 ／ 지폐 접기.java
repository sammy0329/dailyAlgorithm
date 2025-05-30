// 지폐를 접을때는 길이가 긴 쪽을 반으로 접음
// 접기 전 길이가 홀수였다면 접은 후 소수점 이하는 버림
// 접힌 지폐를 그대로 혹은 90도 돌려서 지갑에 넣을 수 있다면 그만 접음
class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while(Math.min(bill[0],bill[1])>Math.min(wallet[0],wallet[1]) || Math.max(bill[0],bill[1])>Math.max(wallet[0],wallet[1])){
            if(bill[0]>bill[1]){
                bill[0]/=2;
            }else{
                bill[1]/=2;
            }
            answer++;
        }
        return answer;
    }
}