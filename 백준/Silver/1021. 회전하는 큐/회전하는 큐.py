from collections import deque

n,m=map(int,input().split())
cnt=0
q=deque([i for i in range(1,n+1)])

answer=list(map(int,input().split()))


for i in range(len(answer)):
    idx=q.index(answer[i])



    if idx<=len(q)//2:
        for j in range(idx):
            q.append(q.popleft())
            cnt+=1


    else:
        idx_=len(q)-idx
        for j in range(idx_):
            q.appendleft(q.pop())
            cnt+=1

    q.popleft()

print(cnt)
    

