
/* ############################################################################
* Chapter 11 컬렉션 프레임웍
##############################################################################*/

/*****************************************************
*  1. 컬렉션 프레임웍(Collection Framework) / 578 
	1.1 컬렉션 프레임웍의 핵심 인터페이스 / 578 
******************************************************/

	자바에서 컬렉션 프레임워크(collection framework)란 다수의 데이터를 쉽고 효과적으로 처리할 수 있는 표준화된 방법을 제공하는 클래스의 집합을 의미합니다
	즉, 데이터를 저장하는 자료 구조와 데이터를 처리하는 알고리즘을 구조화하여 클래스로 구현해 놓은 것입니다.
	이러한 컬렉션 프레임워크는 자바의 인터페이스(interface)를 사용하여 구현됩니다.
- 주요 인터페이스의 간략한 특징
  - 인터페이스	                        설명	                                                                      구현 클래스
     List<E>	        순서가 있는 데이터의 집합으로, 데이터의 중복을 허용함.	                 Vector, ArrayList, LinkedList, Stack, Queue
     Set<E>	        순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않음.	     HashSet, TreeSet
     Map<K, V>    키와 값의 한 쌍으로 이루어지는 데이터의 집합으로, 순서가 없음.		 HashMap, TreeMap, Hashtable, Properties

이때 키는 중복을 허용하지 않지만, 값은 중복될 수 있음.

public class Collection01 {
    public static void main(String[] args) {

        // 리스트 생성
        ArrayList<String> arrList = new ArrayList<String>();
        
        // 리스트에 요소의 저장
        arrList.add("넷");
        arrList.add("둘");
        arrList.add("셋");
        arrList.add("하나");
        
        // 리스트 요소의 출력
        for(int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
    }
}
/*****************************************************
*  1.2 ArrayList / 584 
******************************************************/
	1.3 LinkedList / 596 
	1.4 Stack과 Queue / 604 
	1.5 Iterator, ListIterator, Enumeration / 614 
	1.6 Arrays / 624 
	1.7 Comparator와 Comparable / 628 
	1.8 HashSet / 631 
	1.9 TreeSet / 638 
	1.10 HashMap과 Hashtable / 644 
	1.11 TreeMap / 654 
	1.12 Properties / 658 
	1.13 Collections / 664 
	1.14 컬렉션 클래스 정리 & 요약 / 667 
 
 
 
 
 
 
 
 






