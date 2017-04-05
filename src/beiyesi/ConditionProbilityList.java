package beiyesi;

import java.util.List;

/**
 * 包含所有条件和结果发生的概率。条件概率之和
 *
 */
public class ConditionProbilityList {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ConditionProbility> getConditionProbilityList() {
		return conditionProbilityList;
	}
	public void setConditionProbilityList(List<ConditionProbility> conditionProbilityList) {
		this.conditionProbilityList = conditionProbilityList;
	}
	private String name;
	private List<ConditionProbility> conditionProbilityList;
	
	public ConditionProbilityList(String n,List<ConditionProbility> l){
		this.name = n;
		this.conditionProbilityList = l;
	}
	
	public double getConditionProbilitySUM(){
		if(this.conditionProbilityList==null || this.conditionProbilityList.size() ==0)
			return -1;
		double sum = 0;
		for(ConditionProbility cp :this.conditionProbilityList)
			sum =sum + cp.getConditionProbility();
		return sum;
	}

}
