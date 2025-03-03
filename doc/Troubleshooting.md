 # Github

github clone시 아래와 같이 문제가 발생할 경우

`Please make sure you have the correct access rights and the repository exists.`

1. SSH 에이전트 시작 `eval "$(ssh-agent -s)"` SSH 에이전트 실행
2. SSH 키 등록 ssh-add ~/.ssh/id_rsa SSH 키를 ssh-agent에 추가
3. 등록된 키 확인 ssh-add -l SSH 키가 등록되었는지 확인
4. GitHub 연결 확인 `ssh -T git@github.com` GitHub과 SSH 연결 테스트
5. SSH 키 등록 (GitHub에 추가)	cat ~/.ssh/id_rsa.pub	SSH 공개 키 확인 후 GitHub에 등록

3번으로 키 확인 후 4번을 실행 시키니 비밀번호를 확인 후 키가 등록되었다.