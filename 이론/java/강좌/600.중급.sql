



/* ############################################################################
* 💖    22. Annotation 어노테이션
##############################################################################*/

/*****************************************************
* @interface
******************************************************/
package javas.basics.intermediate.lec;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Exa22AnnoCount1 {

}

/*****************************************************
* 
******************************************************/
	
	/*
어노테이션
어노테이션은 Java5에 추가된 기능
어노테이션은 클래스나 메소드위에 붙습니다. @(at)기호로 이름이 시작합니다.
어노테이션을 클래스나 메타코드에 붙인 후, 클래스가 컴파일되거나 실행될 때 어노테이션의 유무나 어노테이션에 설정된 값을 통하여 클래스가 좀 더 다르게 실행되게 할 수 있습니다.
이런 이유로 어노테이션을 일정의 설정파일처럼 설명하는 경우도 있습니다.
어노테이션은 자바가 기본으로 제공해주는 것도 있고, 사용자가 직접 만들 수도 있습니다.
사용자가 직접 작성하는 어노테이션을 Custom 어노테이션이라고 말합니다.
커스텀 어노테이션을 이용하는 방법
어노테이션을 정의한다.
어노테이션을 클래스에서 사용한다. (타겟에 적용)
어노테이션을 이용하여 실행.
패키지 익스플로러에서 [new - Annotation]을 이용하여 Count100이라는 어노테이션 생성
Count100어노테이션을 JVM실행시에 감지할 수 있도록 하려면 @Retention(RetentionPolicy.RUNTIME)를 붙여줘야 합니다.
	 * 
	 */
    public static void main(String[] args) {
        
		        Exa08BoxGeneric<String> example8Box2 = new Exa08BoxGeneric<>();
		
		        try{
		            Method method = example8Box2.getClass().getDeclaredMethod("hello");
		        if(method.isAnnotationPresent(Exa22AnnoCount1.class)){
		                for(int i = 0; i < 100; i++){
		                	example8Box2.hello();
		                }
		            }else{
		            	example8Box2.hello();
		            }
		        }catch(Exception ex){
		            ex.printStackTrace();
		        }       
        }



/* ############################################################################
* 💖   33. 람다식
##############################################################################*/

/*****************************************************
* interface
******************************************************/

/*

람다식 기본문법
(매개변수목록)->{실행문}

2개의 값을 비교하여 어떤 값이 더 큰지 구하는 compareTo()라는 메소드를 가지고 있는 Compara 인터페이스
2개의 값을 받아들인 후, 정수를 반환하는 메소드를 선언

*/
public interface Exa33LambdaInter {
	public int compareTo(int value1, int value2);
}

/*****************************************************
* class
******************************************************/
package javas.basics.intermediate.lec;

import java.lang.reflect.Method;

import javas.basics.intermediate.lec.Exa33LambdaExec;
import javas.basics.intermediate.lec.Exa33LambdaInter;

public class Exa33LambdaExec {

	/*
	 * 람다식은 다른말로 익명 메소드라고도 한다.  
	 * 람다식 : 익명함수를 이용해서 익명 객체를 생성하기 위한 식 
	 * 인터페이스 중에서 메소드를 하나만 가지고 있는 인터페이스를 함수형 인터페이스라고 한다.
	 * 쓰레드를 만들때 사용하는 Runnable 인터페이스의 경우 run()메소드를 하나만 가지고 있다. Runnable을 이용하여 쓰레드를 만드는 방법
	 */

	
	/*
	 * 쓰레드가 실행되면 쓰레드 생성자 안에 넣은 run()메소드가 실행된다.
		자바는 메소드만 매개변수로 전달할 방법이 없다. 인스턴스만 전달 할 수 있다.
		그렇기때문에 run()메소드를 가지고 있는 Runnable객체를 만들어서 전달한다.
	*/
	public void runableTest() {
        new Thread(new Runnable() {
        	public void run(){
        		for(int i = 0; i < 10; i++){
        			System.out.println("hello");
        		}
        	}
        }).start();
	}

	/*
	 * 메소드만 전달할 수 있다면, 좀더 편리하게 프로그래밍할 수 있을텐데,자바는 메소드만 전달할 수 있는 방법은 없었기 기때문에 매번 객체를
	 * 생성해서 매개변수로 전달해야 했다. 이런 부분을 해결한 것이 람다표현식이다. 
	 * 람다식을 이용해서 수정한 코드
	 */
	public void runableTest2() {
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                System.out.println(" 람다식을 이용해서 수정한 코드 : hello");
            }
        }).start();
	}	
	
    
    public static void main(String[] args) {    
    	
    	Exa33LambdaExec lambda1 = new Exa33LambdaExec();

    	//Runnable을 이용하여 쓰레드를 만드는 방법
//    	lambda1.runableTest();
    	
    	//람다식을 이용해서 수정한 코드
//    	lambda1.runableTest2();
    	
        // 람다식 테스트 
    	lambda1.LambdaTest1();
    	
    	/*
    	 * 2번째 예제 
    	*/
    	Exa33LambdaInter lambda2 = (int k, int m) -> {
    		System.out.println(":::::::::::::::::::::" + k + "   숫자2   :::::::::::::::::::::" + k);
    		return k;
    		
    	};
    	lambda2.compareTo(1, 2);
    	
      }

	/* ########################################################## */
	/*
	 * 자바는 메소드만 인자로 전달하려면 반드시 객체를 만들어서 전달해야 했다. 
	 * Java8에 람다식이 생기면서, 마치 함수만 전달하는 것처럼
	 * 간편하게 문법을 사용할 수 있게 되다.
	 */
    public static void exec(Exa33LambdaInter compara){
    	
        int k = 10;
        int m = 20;
        int value = compara.compareTo(k, m);
        System.out.println(value);
    }
    
    public void LambdaTest1() {
        exec((i, j)->{
            return i - j;
        }); 
        
        exec((i, j)->{
        	int k = i*j; 
            return k;
        }); 
    }


    
}


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 






