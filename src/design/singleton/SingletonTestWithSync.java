package design.singleton;

public class SingletonTestWithSync {
	
	private static SingletonTestWithSync singletonTestWithSync;
	
	private SingletonTestWithSync(){
		
	}
// 同步方法，线程安全但性能底。其实只是第一次初始化时 需要控制线程。
// 如果频次使用不是很高，则可以接受
// 如果持有成本不高，则在定义时赋值。
	public static synchronized SingletonTestWithSync getSingletonTestWithSyncInstance(){
		if(singletonTestWithSync == null){
			singletonTestWithSync = new SingletonTestWithSync();
		}
		return singletonTestWithSync;
	}

}
