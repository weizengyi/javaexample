package mbtexample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModelGraph<T> {
	
	// 邻接矩阵，存储状态图  
    private String[][] matrix;  
    // 顶点数组  
    private T[] vertex;  
    // 顶点的数目  
    private int vertexNum;  
    // 当前结点是否还有下一个结点，判断递归是否结束的标志  
    private boolean noNext = false;  
    // 所有路径的结果集  
    private List<List<T>> result = new ArrayList<>();  
  
    public ModelGraph(String[][] matrix, T[] vertex) {  
        if (matrix.length != matrix[0].length) {  
            throw new IllegalArgumentException("该邻接矩阵不是方阵");  
        }  
        if (matrix.length != vertex.length) {  
            throw new IllegalArgumentException("结点数量和邻接矩阵大小不一致");  
        }  
        this.matrix = matrix;  
        this.vertex = vertex;  
        vertexNum = matrix.length;  
    }  
  
    /** 
     * 深度遍历的递归 
     */  
    private void DFS(int begin, List<T> path) {  
        path.add(vertex[begin]);  
        int rollBackNum = -1;   
        for (int i = 0; i < vertexNum; i++) {  
            if ((matrix[begin][i].length()>0)) {  
                path.add(vertex[i]);  
                if (containBranch(result, path)) {  
                    path.remove(vertex[i]);  
                    rollBackNum = i;  
                    continue;  
                } else {  
                    path.remove(vertex[i]);  
                    // 递归  
                    DFS(i, path);  
                }  
            }  
            // 终止递归  
            if (noNext) {  
                return;  
            }  
        }  
        if (rollBackNum > -1) {  
            // 循环结束仅有一个相邻结点，从这个相邻结点往下递归  
            DFS(rollBackNum, path);  
        } else {  
            // 当前结点没有相邻结点，设置flag以结束递归  
            noNext = true;  
        }  
    }  
  
    /** 
     * 开始深度优先遍历 
     */  
    public List<List<T>> startSearch() { 
        for (int i = 0; i < countPathNumber(); i++) {  
            // 用于存储遍历过的点  
            List<T> path = new LinkedList<>();  
            noNext = false;  
            // 开始遍历  
            DFS(0, path);  
            // 保存结果  
            result.add(path);  
        }  
        return result;  
    }  
  
    /** 
     * 计算路径的分支数量 
     */  
    private int countPathNumber() {  
        int[] numberArray = new int[vertexNum];  
        for (int i = 0; i < vertexNum; i++) {  
            for (int j = 0; j < vertexNum; j++) {  
                if (matrix[j][i].length() > 0) {  
                    numberArray[j]++;  
                }  
            }  
        }  
        int number = 1;  
        for (int k = 0; k < vertexNum; k++) {  
            if (numberArray[k] > 1) {  
                number++;  
            }  
        }  
        return number;  
    }  
  
    /** 
     * 判断当前路径是否被已有路径的结果集合所包含 
     */  
    private boolean containBranch(List<List<T>> nodeLists, List<T> edges) {  
        for (int i = 0; i < nodeLists.size(); i++) {  
            List<T> list = nodeLists.get(i);  
            if (list.containsAll(edges)) {  
                return true;  
            }  
        }  
        return false;  
    }  

}
