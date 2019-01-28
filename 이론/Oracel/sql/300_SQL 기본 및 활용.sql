 
/* ############################################################################
*  제목   like  and 검색 
##############################################################################*/

/*****************************************************
*  oracle like and in together
******************************************************/
  
  SELECT * FROM LISTOFVALUE L 
INNER JOIN TBL ON TBL.MYCOL LIKE '%' || L.VALUE  || L.VALUE  || '%'
 
 SELECT * FROM TBL
 WHERE EXISTS (SELECT 1 FROM ALL_LIKES WHERE ALL_LIKES.VALUE = SUBSTR(TBL.MY_COL,0, LENGTH(TBL.MY_COL)))
 
 
 /*****************************************************
*  oracle like and in together
******************************************************/
 



