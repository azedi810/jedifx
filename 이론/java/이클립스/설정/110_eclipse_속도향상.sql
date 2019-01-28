
/* ###########################################################################################
  *   속도 향상  
  * ######################################################################################### */
 
/********************************************************
*   eclipse.ini    기본 
*********************************************************/		
 -vmargs
-Dfile.encoding=UTF-8
-Dosgi.requiredJavaVersion=1.6
-Xms40m
-Xmx512m
 
/********************************************************
*   eclipse.ini     수정후  : 성능향상 
*********************************************************/		
-vmargs
-Dosgi.requiredJavaVersion=1.5
-Xverify:none
-XX:+UseParallelGC
-XX:-UseConcMarkSweepGC
-XX:PermSize=32M
-XX:MaxPermSize=128M
-XX:MaxNewSize=32M
-XX:NewSize=32M
-Xms1024m
-Xmx1024m


/********************************************************
*   eclipse.ini  성능향상 설명
*********************************************************/		
-Dosgi.requiredJavaVersion=1.6 => JDK 1.6 이상을 설치했을 경우에 1.6으로 설정하면 속도가 빨라진다.



-Xverify:none => 클래스의 유효성을 검사 생략. (시작 시간이 줄어 빨라진다.)

-XX:+UseParallelGC => 병렬 가비지 컬렉션 사용. (병렬 처리로 속도 향상)

-XX:+AggressiveOpts => 컴파일러의 소수점 최적화 기능을 작동시켜 빨라진다.

-XX:-UseConcMarkSweepGC => 병행 mark-sweep GC 수행하여 이클립스 GUI의 응답을 빠르게한다.

-XX:+CMSIncrementalMode=true => 점진적인 GC

-XX:PermSize=128M        => Permanent Generation(영구 영역) 크기(Out Of Memory 에러시 크기 조절)

-XX:MaxPermSize=128M  => 최대 Permanent Generation 크기

-XX:NewSize=128M         => New Generation(새 영역) 크기

-XX:MaxNewSize=128M   => New Generation(새 영역) 의 최대 크기


-Xms512m : 이클립스가 사용하는 최소 Heap 메모리

-Xmx512m : 이클립스가 사용하는 최대 Heap 메모리

                     최소와 최대를 같은 값으로 설정하면 오르락 내리락 하지않아 빨라진다.


2. 자동폴딩 해제

Preferences - Java - Editor - Folding - [Enable folding] 체크 해제



3. 코드 자동완성기능 해제

Preferences - Java - Editor - Content Assist - [Auto Activation]의 [Enable auto activation] 체크해제



4. 스펠링 체크설정 해제

Preferences - General - Editors - Text Editors - Spelling - [Enable spell checking] 체크 해제



5. 유효성 체크(Validation) 설정 해제

Preferences - Validation 에서 웹관련을 제외하고 체크해제



6. 메모리 사용 상태 표시
Preferences - General - [Show heap status] 체크



7. 에디터 라인번호 표시
Preferences - General - Editors - Text Editors - [Show line numbers] 체크


8. 새프로젝트 기본 인코딩 설정
Preferences - General - Workspace - [Text file encoding]에서 UTF-8 설정


9. Compare 공백 무시하기
Preferences - General - Compare/Patch - [Ignore white space] 체크



10. 저장액션 설정
Preferences - Java - Editor - Save Actions - 

[Perform the selected actions on save] 체크

[Additional actions] 체크

  - 공백없애기 : [Configure...]버튼눌러서 [Remove trailing whitespace] 체크 (All lines)

  - 안쓰는 임포트 지우기: [Configure...]버튼눌러서 Unnecessary Code탭의 [Remove unused imports] 체크



11. 실행/종료 속도 개선
Preferences - General - Startup and Shutdown - Plugins activated on startup에서 체크해제
