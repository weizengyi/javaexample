package design.singleton;

public class SingletonTestWithSync2 {
	
	private static SingletonTestWithSync2 singletonTestWithSync ;
	
	private SingletonTestWithSync2(){
		
	}
//	双重检查机制
	public SingletonTestWithSync2 getSingletonTestWithSyncInstance(){
		if(singletonTestWithSync == null){
			synchronized(SingletonTestWithSync.class){
				if(singletonTestWithSync == null)
					singletonTestWithSync = new SingletonTestWithSync2();
			}
		}
		return singletonTestWithSync;
	}

}
