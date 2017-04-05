package mbtexample;

import java.util.ArrayList;
import java.util.List;

public class ModelUtil {
	
	public static List<List<Path>> getCaseList(String[][] matrix,String[] vertex){
		    ModelGraph<String> graph = new ModelGraph<>(matrix, vertex);  
	        List<List<String>> result = graph.startSearch();     
	        System.out.println(result);
	        List<List<Path>> caseSuit =  new ArrayList<List<Path>>();
	        for(List<String> list: result){
	        	List<Path> casePath = new ArrayList<Path>();
	        	for(int i = 0 ; i+1< list.size() ;i++){
	        		int x = getIndex(vertex,list.get(i));
	        		int y = getIndex(vertex,list.get(i+1));
	        		String act = matrix[x][y];
	        		Path path = new Path(new Status(list.get(i)),new Status(list.get(i+1)),new Action(act));
	        		casePath.add(path);
	        	}
	        	caseSuit.add(casePath);
	        } 
	        return caseSuit;
	}
	
	private static int getIndex(String[] sa,String s){
		for(int i = 0 ; i<sa.length;i++)
			if(sa[i].equalsIgnoreCase(s))
				return i;
		return -1;
	}
	

}
