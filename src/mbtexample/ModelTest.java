package mbtexample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ModelTest {
	
	@Test
	public void test1(){
		List<Path> pathList = new ArrayList<Path>();
		Path path1 = new Path(new Status(StatusList.start),new Status(StatusList.init),new Action("createOrder"));
		Path path2 = new Path(new Status(StatusList.init),new Status(StatusList.success),new Action("pay"));
		Path path3 = new Path(new Status(StatusList.success),new Status(StatusList.refunded),new Action("refund"));
		Path path4 = new Path(new Status(StatusList.init),new Status(StatusList.init),new Action("query"));
		Path path5 = new Path(new Status(StatusList.success),new Status(StatusList.success),new Action("query"));
		Path path6 = new Path(new Status(StatusList.refunded),new Status(StatusList.refunded),new Action("query"));
		pathList.add(path1);
		pathList.add(path2);
		pathList.add(path3);
		pathList.add(path4);
		pathList.add(path5);
		pathList.add(path6);
		new Model(pathList).execute();
	}
	
	@Test
	public void test2(){
		 
		    String[] vertex = { StatusList.start, StatusList.init, StatusList.success, StatusList.refunded};  
	        double[][] matrix = {   
	                { 0, 1, 0, 0 },  
	                { 0, 0, 1, 0 },  
	                { 0, 0, 0, 1 },  
	                { 0, 0, 0, 0 },  
	                };  
	        
	        Graph<String> graph = new Graph<>(matrix, vertex);  
	        System.out.println(graph.startSearch());  
	}
	
	@Test
	public void test3(){
		 
		    String[] vertex = {StatusList.start, StatusList.init, StatusList.success, StatusList.refunded};  
		    String[][] matrix = {   
	                { "",    "create",  "pay",    ""        },  
	                { "",    "",        "pay", ""        },  
	                { "",    "",        "",    "refund"  },  
	                { "",    "",        "",    ""        },  
	                };  
	       
		    List<List<Path>> caseSuit = ModelUtil.getCaseList(matrix, vertex);
	        for(List<Path> pathList :caseSuit){
	        	System.out.println("测试用例：");
	        	for(Path path:pathList){
	        		System.out.println(path.toString());
	        	};
	        	System.out.println("执行记录：");
	        	new Model(pathList).execute();
	        }
	}
	
	

}
