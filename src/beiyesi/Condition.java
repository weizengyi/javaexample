package beiyesi;
/**
 * 
 * 条件
 * 条件描述
 * 条件为真／假
 * 条件为真／假的概率
 *
 */

public class Condition {
	
	public Probility getPro() {
		return pro;
	}
	public void setPro(Probility pro) {
		this.pro = pro;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private String name;
	private boolean flag;
	private Probility pro;
	
	public Condition(String n,boolean f,Probility pro){
		this.name = n ;
		this.flag = f;
		this.pro = pro;
	}
	
	public boolean isSameCondition(Condition c){
		return (this.name.equalsIgnoreCase(c.name)) 
				&& (this.flag == c.flag) 
				&& this.pro.getProbility() == c.pro.getProbility() ;
		
	}

}
