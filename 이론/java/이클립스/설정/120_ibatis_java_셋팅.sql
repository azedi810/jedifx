
/* ###########################################################################################
  *   
  * ######################################################################################### */
 
			 /********************************************************
			*  example/ibatismysql/IbatisConnect.java
			*********************************************************/		
package example.ibatismysql;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisConnect {
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		final SqlMapClient sqlMap;
    	System.out.println("###############" );
    	
        try {
        	System.out.println("config/sql/SqlMapConfig.xml  reading.............  " );
            Reader reader = Resources.getResourceAsReader("config/sql/SqlMapConfig.xml");
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
            
            List list = sqlMap.queryForList("MyLottoSql.getList","a");
            for(int i=0; i < list.size(); i++) {
            	System.out.println("--> " + list.get(i) );
            }
        } catch (IOException e) {
            throw new RuntimeException("  ###  IOException  vv 에러 1 :### " + e, e);
        } catch (Exception e){
            //throw new RuntimeException("  ### Exception 에러 vv  2 : ###" + e, e);
            e.printStackTrace();
        }
	}

}

			 /********************************************************
			*  config/sql/SqlMapConfig.xml
			*********************************************************/		
			
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE sqlMapConfig
        PUBLIC "-//iBATIS.com//DTD SQL Map Config 2.0//EN"
        "http://www.ibatis.com/dtd/sql-map-config-2.dtd">

<sqlMapConfig>

    <settings
        useStatementNamespaces="true"
        lazyLoadingEnabled="true"
        enhancementEnabled="true"
        maxSessions="20"
        />
 
    <transactionManager type="JDBC" commitRequired="false">
        <dataSource type="SIMPLE">
 
            <property name="SetAutoCommitAllowed" value="false"/>
            <property name="DefaultAutoCommit" value="false"/>
             
            <property name="JDBC.Driver" value="com.mysql.jdbc.Driver"/>
            <property name="JDBC.ConnectionURL" value="jdbc:mysql://localhost/itjs"/>
            <property name="JDBC.Username" value="itjs"/>
            <property name="JDBC.Password" value="itjs123"/>
    
        </dataSource>
    </transactionManager>

    <sqlMap resource="config/sql/MyLottoSql.xml"/> 	 
 </sqlMapConfig>

			 /********************************************************
			*  config/sql/MyLottoSql.xml
			*********************************************************/		

 <?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE sqlMap
    PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN"
    "http://www.ibatis.com/dtd/sql-map-2.dtd">
    
<sqlMap namespace="MyLottoSql" >
 
  <insert id="insertContoh" parameterClass="example.ibatismysql.Contoh" >
    insert into lotto (no, num)
    values (1, 10000)
  </insert>
  
 	<select id="getList" parameterClass="string" resultClass="hashmap">
		select * from lotto 
		where no=1588
	</select>  
    <!-- values (#nama:VARCHAR#, #alamat:VARCHAR#) -->
</sqlMap>

/* ###########################################################################################
  *   log4j 셋팅
  * ######################################################################################### */
 
			 /********************************************************
			* log4j-1.2.5.jar  파일 add jars  추가 
			*********************************************************/	
 
			 /********************************************************
			*  /src/log4j.properties
			*********************************************************/	

log4j.rootLogger=DEBUG, stdout

log4j.logger.com.ibatis=DEBUG
log4j.logger.com.ibatis.common.jdbc.SimpleDataSource=DEBUG
log4j.logger.com.ibatis.common.jdbc.ScriptRunner=DEBUG
log4j.logger.com.ibatis.sqlmap.engline.impl.SqlMapClientDelegate=DEBUG
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p[%t]-%m%n

			 /********************************************************
			*  /src/log4j.properties  설명 
			*********************************************************/	

-- log4j.logger.com.ibatis=DEBUG .................  위의 관련 설정들 
		- sqlmap 관련 로그 출력
		
		
-- Appender  :   출력방식
	- ConsoleAppender : 콘솔에 출력하는 방식 설정 필요 없음 
	- DailyRollingFileAppender : 시간을 주기로 파일을 생성하여 기록하는 방식
			- log4j.appender.Tech.DatePattern='.'yyyy-MM-dd    -- 일단위 저장
			- log4j.appender.Tech.DatePattern='.'yyyy-MM			-- 월단위
			- log4j.appender.Tech.DatePattern='.'yyyy-MM-dd-HH  -- 시간단위  
	- RollingFileAppender : 일정 용량만큼 파일에 쓰는 방식 
	
-- Layout : 출력내용
	- PatternLayout : 패턴을 정하는 방식
	- SimpleLayout : 심플하게
	- HTMLLayout : html 형태로 만들어준다 
	- TTCCLayout : 심플보다 많이 나옴
	
			 /********************************************************
			*  /신문고 epeople 참고 
			*********************************************************/	
		
# Root Logger
log4j.rootLogger=DEBUG, Console

log4j.category.com.skcc=DEBUG, Console
log4j.additivity.com.skcc=false

log4j.category.gov.paid=DEBUG, Console
log4j.additivity.gov.paid=false

log4j.category.com.ibatis=DEBUG
log4j.category.java.sql=DEBUG

# Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
#log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p %l - %m%n
log4j.appender.Console.layout.ConversionPattern=[%d{MM/dd HH:mm:ss}] %c{1}.%M(%3L) - %m%n

# File
log4j.appender.File=org.apache.log4j.RollingFileAppender
log4j.appender.File.File=/Was/bea923/user_projects/domains/mydomain/logs/application.log
log4j.appender.File.layout=org.apache.log4j.PatternLayout
log4j.appender.File.layout.ConversionPattern=%n%d%n\%p : %l%n%m%n%n
log4j.appender.File.MaxFileSize=1000KB
log4j.appender.File.MaxBackupIndex=1

log4j.category.gov.paid.common=DEBUG, Tech
# log4j.logger.gov.paid.common=INFO, Tech
log4j.appender.Tech=org.apache.log4j.DailyRollingFileAppender
log4j.appender.Tech.File=/Was/bea923/user_projects/domains/mydomain/logs/tech.log
log4j.appender.Tech.DatePattern='.'yyyy-MM-dd
# log4j.appender.Tech.Threshold=INFO
log4j.appender.Tech.layout=org.apache.log4j.PatternLayout
log4j.appender.Tech.layout.ConversionPattern=[%d{MM-dd HH:mm}] %c{1}(%L) : %m%n

log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG

log4j.additivity.java.sql.Connection=true
log4j.additivity.java.sql.Statement=true
log4j.additivity.java.sql.PreparedStatement=true
log4j.additivity.java.sql.ResultSet=false

		
		

		
		
		


