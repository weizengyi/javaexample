package beiyesi;

import java.util.List;

/**
 * 一组条件和导致结果发生的概率
 */
public class ConditionProbility {


	public List<Condition> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}

	public Probility getProbility() {
		return probility;
	}

	public void setProbility(Probility probility) {
		this.probility = probility;
	}

	private List<Condition> conditionList;
	
	private Probility probility;
	
	public ConditionProbility(List<Condition> conList,Probility pro){
		this.conditionList = conList;
		this.probility = pro;
	}
	
	public double getConditionProbility(){
		if(this.conditionList == null || this.probility ==null)
			return -1;
		if(this.conditionList.size() == 0)
			return -1;
		double cb = 1;
		for(Condition c :conditionList)
			cb = cb * c.getPro().getProbility();
		return cb*this.probility.getProbility();
	}

}
