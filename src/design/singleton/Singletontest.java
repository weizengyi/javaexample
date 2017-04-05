package design.singleton;

public class Singletontest {
	
//	静态变量记录唯一的实例
	private static Singletontest uniqueInstance;
	
	private Singletontest(){
		
	}
	
	public static Singletontest getInstance(){
//		延迟初始化，但线程不安全
		if(uniqueInstance == null){
			uniqueInstance = new Singletontest();
		}
		return uniqueInstance;
	}
	
	

}
