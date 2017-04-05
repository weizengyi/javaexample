package mbtexample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<Path> model;
	public Model(List list) {
		model = list;
	}
//	执行测试，忽略测试数据
	public boolean execute() {
		for (Path p : model) {
			Class clazz = Action.class;
			try {
				Action act = p.getAction();
				Method m = clazz.getDeclaredMethod(act.getActionTag(), String.class);
				m.invoke(p.getAction(), "ORDER");
				check(p.getNextState(),getActul());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
//	模拟进行检查
	private boolean check(Status expect,Status actul){
		System.out.println("do some checks");
		return true;
	}
//	模拟获得实际状态
	private Status getActul(){
		System.out.println("get actul state");
		return new Status("FreakState");
	}
	
}
