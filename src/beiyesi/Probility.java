package beiyesi;
/**
 * 
 * 概率
 * 概率描述
 * 概率值的大小
 * 非的值的大小
 * 
 */

public class Probility {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProbility() {
		return probility;
	}

	public void setProbility(double probility) {
		this.probility = probility;
	}

	public double getAntiProbility() {
		return AntiProbility;
	}

	public void setAntiProbility(double antiProbility) {
		AntiProbility = antiProbility;
	}

	private String name;
	
	private double probility;
	
	private double AntiProbility;
	
	public Probility(String name,double probility){
		this.name = name;
		this.probility = probility;
		this.AntiProbility = 1- probility;
	}

}
