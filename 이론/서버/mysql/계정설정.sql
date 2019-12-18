
/* ############################################################################
*  
##############################################################################*/

	/*****************************************************
	*  mysqladmin
	******************************************************/
	http://localhost:8999/myadmin/index.php
	
	
	/*****************************************************
	*  
	******************************************************/
 
1. 처음 설치후 셋팅
#] mysql -u root mysql

> update user set password=password(' ') where user='root';
> flush privileges;
> q

#] /etc/rc.d/init.d/mysql restart

#] mysql -u root -p mysql

> create database 디비이름;
> insert into db values ('localhost','디비이름','계정','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y');
> insert into user values ('localhost','계정',password(''),'Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y');
> q

#] /etc/rc.d/init.d/mysql restart
#] mysql -u 계정 -p 디비이름
> use 디비이름;]
> q


 

mysql5 이상 부터 mysqldump 권한 문제

다음과 같이 해줘야 백업이 가능하다
update user set Lock_tables_priv='Y' where user='itjs';

 

MYSQL 시작

문자 변경하고 시작하려면
#] --default-character-set=utf8
기본
#] /etc/rc.d/init.d/mysql restart

 




추가 계정 줄때 설정

#] mysql -u root -p mysql

5.0 이상부터는

로컬
> CREATE DATABASE dywmch;

> grant all privileges on zip2014.* to  'zipuser'@'localhost' identified by 'zipuser1238';    /* 계정 지정 */
> grant all privileges on *.* to 'dywmch'@'localhost' identified by 'dywmch123';  /* 계정 지정 안한거 */
> flush privileges;


원격
> CREATE DATABASE 데이타베이스;
> grant CREATE,INSERT,SELECT,DELETE,UPDATE,DROP,ALTER on 데이타베이스.* to '계정'@'%' identified by '패스워드';

> flush privileges;

> grant SELECT on 데이타베이스.* to '계정'@'%' identified by '패스워드'; //원격 지원 select 만 허용할때


백화점세이 mysql5 , 포트 3307, 접속 알될때 아래처럼
> update user set Password=old_password('mobile1238') where Host='localhost' and User='mobile'
> flush privileges;

권한주기
/* LOAD DATA  권한 주기 */

update user set File_priv='Y' where user='say2003';
flush privileges;

 

/* 트리거 권한 주기 (root 나 슈퍼권한을 가진 계정만 된다) */

update user set Create_routine_priv='Y', Alter_routine_priv='Y' where user='iomodelinglab';
flush privileges;



로컬에서
  > CREATE DATABASE database;
  > grant CREATE,INSERT,SELECT,DELETE,UPDATE,DROP,ALTER on database.* to 계정@localhost;
  > set password for 'user'@'localhost'=password('패스워드');   // 로컬만 접속
  > flush privileges;

원격에서
  > CREATE DATABASE database;
  > grant CREATE,INSERT,SELECT,DELETE,UPDATE,DROP,ALTER on database.* to user;  // 원격으로 어디서나 접속
  > set password for 'user'@'%'=password('패스워드');  // 원격으로 어디서나 접속할 수 있게
  > flush privileges;


   - alter : alter 기능 사용 가능하게
   - drop : drop table 테이블이름 사용가능하게

용접학회 원격 219.240.134.21 번 접속 가능하도록
  > grant CREATE,INSERT,SELECT,DELETE,UPDATE,DROP,ALTER on kws.* to kws;  // 원격으로 어디서나 접속
  > set password for 'kws'@'219.240.134.21'=password('kws7878');  // 원격으로 어디서나 접속할 수 있게
  > flush privileges;

root 패스워드 분실시

1>암호 초기화
# killall mysqld (데몬을 죽입니다)

# cd /usr/local/mysql (mysql이 깔린 곳으로 갑니다)

# ./bin/safe_mysqld --skip-grant &

mysql -u root mysql   접속해서 변경한다
	/*****************************************************
	*  
	******************************************************/
 