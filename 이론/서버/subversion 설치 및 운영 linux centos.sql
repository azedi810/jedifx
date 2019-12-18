
/* ############################################################################
*  
##############################################################################*/

	/*****************************************************
	*  세종시 집 서버 정보 
	******************************************************/
		계정정보
		svnroot : 홍*s**
		계정 : knu /  knu9012   -->    url(svn://192.168.219.129/knu)     kr.ac.knu.ipsi1   경북대 입시
		계정 : fund=djufund777   -->    url(svn://192.168.219.129/djufund)     kr.ac.dju.fund   대전대 발전기금
		계정 : javis=javis0518   -->    url(svn://192.168.219.129/javis)        개발서버
		계정 : peaks=0822        -->    url(svn://192.168.219.129/peaks)      라인픽스

	/*****************************************************
	*  itjs : 219.240.134.13 번 서버 정보 
	******************************************************/
		계정정보
		svnroot : 홍*s**
		계정 : knu /  knu9012   -->    url(svn://219.240.134.13/knu)     kr.ac.knu.ipsi1   경북대 입시
		계정 : fund=djufund777   -->    url(svn://219.240.134.13/djufund)     kr.ac.dju.fund   대전대 발전기금
		계정 : javis=javis0518   -->    url(svn://219.240.134.13/javis)        개발서버
		계정 : peaks=0822        -->    url(svn://219.240.134.13/peaks)      라인픽스

php 계정
계정 : phpdev=phpwebprogram   -->    url(svn://219.240.134.13/phpdevpriviate)        php 개발 테스트

인터커뮤니티 21번 컴퓨터 공유 목적
window=document09~!     ----------->  svn://219.240.134.13/windowdocu

백업받기
발전기금 : svn checkout svn://localhost/djufund         /* /data/nas/sdb1/subversion/djufund/workspace  경로에서 실행한다 */
자비스프 : svn checkout svn://localhost/javis        /* /data/nas/sdb1/subversion/javis/workspace  경로에서 실행한다 */
경북대학 : svn checkout svn://localhost/knu     
 
subversion 설치
#] yum install   subversion
Installing:
 subversion-devel                                 i386                                1.6.11-11.el5_9                                   base                                266 k
 subversion-javahl                                i386                                1.6.11-11.el5_9                                   base                                177 k
 subversion-perl                                  i386                                1.6.11-11.el5_9                                   base                                1.0 M
 subversion-ruby                                  i386                                1.6.11-11.el5_9                                   base                                461 k
Installing for dependencies:
 apr-devel                                        i386                                1.2.7-11.el5_6.5                                  base                                233 k
 apr-util-devel                                   i386                                1.2.7-11.el5_5.2                                  base                                 53 k
 ruby-libs                                        i386                                1.8.5-31.el5_9                                    base                                1.6 M
Transaction Summary

명령어 help
#] svnadmin help
   crashtest
   create
   deltify
   dump
   help (?, h)
   hotcopy
   list-dblogs
   list-unused-dblogs
   load
   lslocks
   lstxns
   pack
   recover
   rmlocks
   rmtxns
   setlog
   setrevprop
   setuuid
   upgrade
   verify
 
저장소 만들기
가. 버클리 DB를 이용한 저장소 만들기
#]  svnadmin create --fs-type   bdb  /data/nas/sdb1/subversion/knuipsi1
 
나. 파일 시스템을 이용한 저장소 만들기  (이걸로 만들어라)
#]  svnadmin create --fs-type  fsfs  /data/nas/sdb1/subversion/knuipsi1 

#]  adduser svnroot
#] passwd svnroot
#] chown svnroot.svnroot -R  /data/nas/sdb1/subversion/knuipsi1
#] vi /data/nas/sdb1/subversion/knuipsi1/conf/svnserve.conf
anon-access = none
auth-access = write
password-db = passwd
realm = knuipsi1 Repository

#] vi passwd
[users]
# harry = harryssecret
# sally = sallyssecret
#knuipsi1 = knu777
test=1111


star stop
#] su - svnroot
#] svnserve -d -r /data/nas/sdb1/subversion      /* svnadmin create 생성한 폴더명은 빼고 진행  */

서비스 죽이기
#] killall svnserve
네트워크 확인 
#] netstat -an | grep LI | grep 3690     /* 포토 번호 확인해서 iptable 에서 설정 풀어준다 */

iptable
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 3690 -j ACCEPT

경북대학교 checkout

상태 확인
#] svn checkout svn://localhost/knuipsi1 test       /* 확인한다 */
 
 
#] svn list svn://219.240.134.13/knuipsi1  /* 리스트 확인하기 */
#] svn list svn://219.240.134.13/knuipsi1/trunk/
co.kr.interbank.api/
com.jk.test1/
#]
 



eclipse 에서 확인 사항
svn://219.240.134.13/knuipsi1
test= 1111
 
    
    
    
    
    
    