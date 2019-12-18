
/* ############################################################################
*  공통컴포넌트 설정
* 가이드1 : http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:dev3.8:imp:editor:common_component 
##############################################################################*/

	/*****************************************************
	* eGovFrame Web Project
	******************************************************/
메뉴표시줄에서 우클릭 > New > eGovFrame Web Project를 선택한다. (단 eGovFrame 퍼스펙티브 내에서) 
 - project name : egovframework-all-in-one
 - target runtime : apache tomcat v8.5
 - dynamic web module version 3.1 
 - Mave setting : Group id (egovframework),  version (3.8.0) 
 

	/*****************************************************
	* 공통컴포넌트 다운로드
	******************************************************/
참고 사이트 : http://www.egovframe.go.kr/EgovEnvReleaseNote.jsp?menu=3&submenu=4

- 1. 3.8.0공통컴포넌트 3.8.0 all-in-one 배포파일  다운로드  : egovframework-all-in-oneAllNew.zip[10,795,330 byte]

- 다운로드 받은 배포파일의 압축을 푼다. 그리고 압축을 푼 파일들을 복사해서 이클립스의 egovframework-all-in-one 프로젝트 아래에 붙여넣는다. 

- 프로젝트명을 마우스 우클릭하여 run as > maven install을 실행한다. 

- 공통컴포넌트를 설치한 프로젝트 내에 위치한 “globals.properties” 파일의 정보를 바꿔야 한다.
   (globals.properties　파일의 위치: src/main/resources > egovframework > egovProps > globals.properties을 확인한다.) 
    해당 properties 파일에 수동으로 정보를 입력한다. 
    그리고 maven clean, maven install을 한 후 run as-run on server 를 실행한다.
    
/* ############################################################################
*  db 세팅  
##############################################################################*/
   
	/*****************************************************
	* mysql   /src/main/resources/egovframework/egovProps/globals.properties
	******************************************************/
Globals.OsType = WINDOWS

Globals.DbType = mysql

Globals.mysql.DriverClassName=net.sf.log4jdbc.DriverSpy
Globals.mysql.Url=jdbc:log4jdbc:mysql://127.0.0.1:3306/egov
Globals.mysql.UserName = egov
Globals.mysql.Password = egovtest
    
	/*****************************************************
	* dml, ddl  스크립트로 생성해준다. 
	******************************************************/
    mysql/com_DDL_mysql.sql
    com_DML_mysql.sql
    
/* ############################################################################
*  problems 
##############################################################################*/
   
	/*****************************************************
	* Can not find the tag library descriptor for "http://ajaxtags.sourceforge.net/tags/ajaxtags"
	******************************************************/
    
    
    
    