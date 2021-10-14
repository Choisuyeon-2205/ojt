### 회원 가입 프로젝트  
  
- 아이디 중복 체크  
- 주민 번호 정상 유무 체크   
- 우편번호 찾기 ⇒ [중요] 다음 카카오 API 적용하여 처리  
- 오라클 DB 연동하여 회원 테이블에 insert 처리 (+오라클 설치)  
  
1. 프로젝트 디렉토리 구조  
프로젝트 명: Membership_Join  
   Java Resources 폴더    
    src 폴더에서  
      패키지 명: member 생성   
        클래스 명: DBBean ⇒ main() 메소드 포함X    
   WebContent 폴더에서  
    jsp 파일명: MemberForm 생성    
               IdCheck  
               Member_control  
               Welcome  
    js 폴더 생성   
      javascript 파일명: idCheck  
                        change_email   
                        juminCheck  
  
  
2. 오라클(Oracle) 설치  
OracleXEUniv.zip ⇒ 교수님께서 보내 줌   
파일 사용하여 설치를 진행  
![1.png](./imgs/1.png) 

=> 설치 확인  
  
C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib 폴더에 접근  
⇒ ojdbc14.jar 파일  
![2.png](./imgs/2.png)
  
이클립스에서 생성한 프로젝트 WebContent → WEB-INF → lib 아래에 ojdbc.jar 파일 붙여넣기  
![3.png](./imgs/3.png)



3. DB 접속 후 계정 생성  
sysdba 관리자 계정으로 접속 후  
- 계정 생성(암호 포함)
- 생성된 계정에 권한 부여 ⇒ connect. resource 를 (Role) 권한 부여
- 생성된 계정으로 DB에 접속
- 회원 테이블 생성
- 데이터 입력 ⇒ 아이디 중복 체크를 하기 위해서 한 건의 데이터 입력이 필요하다.
- commit; ⇒ 반드시 commit한다.
- select 확인

SQL> conn /as sysdba

SQL> create user testdb identified by testdb1234;

SQL> grant connect, resource to testdb;

SQL> conn testdb/testdb1234

![4.png](./imgs/4.png)
