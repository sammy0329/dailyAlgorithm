/**
* 1. brown+yellow로 총 개수를 구하기
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
**/
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;
        int r=0;
        int c=0;
        
        for(int n=2; n<total/2; n++){
            if(total%n!=0){
                continue;
            }
            
            r = total/n;
            c = n;
            
            if(brown == c*2 + (r-2)*2){
                answer[0] = r;
                answer[1] = c;
                break;
            }
            
        }
        return answer;
    }
}