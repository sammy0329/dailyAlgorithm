import java.util.*;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int length = words.length;
        
        Queue<String> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[length];
                
        for(int i=0; i<length; i++){
            if(words[i].equals(begin)){
                isVisited[i] = true;
            }
        }
        
        q.offer(begin);
        int qSize = 0;
        
        while(!q.isEmpty()){
            qSize = q.size();
            
            for(int k=0; k<qSize; k++){
                String current = q.poll();
                if(current.equals(target)){
                    return answer;
                }
                A: for(int i=0; i<length; i++){
                    if(isVisited[i]){
                        continue;
                    }

                    int checkCnt=0;

                    for(int j=0; j<current.length(); j++){
                        if(current.charAt(j)!=words[i].charAt(j)){
                            if(checkCnt == 1){
                                continue A;
                            }
                            checkCnt++;
                        }    
                    }

                    if(checkCnt==1){
                        q.offer(words[i]);
                        isVisited[i] = true;
                    }
                }
            }
            answer++;
            }     
        return 0;
    }
}