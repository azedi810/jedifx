
/* ############################################################################
*  
##############################################################################*/

	/*****************************************************
	*  ������ �� ���� ���� 
	******************************************************/
		��������
		svnroot : ȫ*s**
		���� : knu /  knu9012   -->    url(svn://192.168.219.129/knu)     kr.ac.knu.ipsi1   ��ϴ� �Խ�
		���� : fund=djufund777   -->    url(svn://192.168.219.129/djufund)     kr.ac.dju.fund   ������ �������
		���� : javis=javis0518   -->    url(svn://192.168.219.129/javis)        ���߼���
		���� : peaks=0822        -->    url(svn://192.168.219.129/peaks)      �����Ƚ�

	/*****************************************************
	*  itjs : 219.240.134.13 �� ���� ���� 
	******************************************************/
		��������
		svnroot : ȫ*s**
		���� : knu /  knu9012   -->    url(svn://219.240.134.13/knu)     kr.ac.knu.ipsi1   ��ϴ� �Խ�
		���� : fund=djufund777   -->    url(svn://219.240.134.13/djufund)     kr.ac.dju.fund   ������ �������
		���� : javis=javis0518   -->    url(svn://219.240.134.13/javis)        ���߼���
		���� : peaks=0822        -->    url(svn://219.240.134.13/peaks)      �����Ƚ�

php ����
���� : phpdev=phpwebprogram   -->    url(svn://219.240.134.13/phpdevpriviate)        php ���� �׽�Ʈ

����Ŀ�´�Ƽ 21�� ��ǻ�� ���� ����
window=document09~!     ----------->  svn://219.240.134.13/windowdocu

����ޱ�
������� : svn checkout svn://localhost/djufund         /* /data/nas/sdb1/subversion/djufund/workspace  ��ο��� �����Ѵ� */
�ں��� : svn checkout svn://localhost/javis        /* /data/nas/sdb1/subversion/javis/workspace  ��ο��� �����Ѵ� */
��ϴ��� : svn checkout svn://localhost/knu     
 
subversion ��ġ
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

��ɾ� help
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
 
����� �����
��. ��Ŭ�� DB�� �̿��� ����� �����
#]  svnadmin create --fs-type   bdb  /data/nas/sdb1/subversion/knuipsi1
 
��. ���� �ý����� �̿��� ����� �����  (�̰ɷ� ������)
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
#] svnserve -d -r /data/nas/sdb1/subversion      /* svnadmin create ������ �������� ���� ����  */

���� ���̱�
#] killall svnserve
��Ʈ��ũ Ȯ�� 
#] netstat -an | grep LI | grep 3690     /* ���� ��ȣ Ȯ���ؼ� iptable ���� ���� Ǯ���ش� */

iptable
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 3690 -j ACCEPT

��ϴ��б� checkout

���� Ȯ��
#] svn checkout svn://localhost/knuipsi1 test       /* Ȯ���Ѵ� */
 
 
#] svn list svn://219.240.134.13/knuipsi1  /* ����Ʈ Ȯ���ϱ� */
#] svn list svn://219.240.134.13/knuipsi1/trunk/
co.kr.interbank.api/
com.jk.test1/
#]
 



eclipse ���� Ȯ�� ����
svn://219.240.134.13/knuipsi1
test= 1111
 
    
    
    
    
    
    