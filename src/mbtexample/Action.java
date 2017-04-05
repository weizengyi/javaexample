package mbtexample;

public class Action {
	
    public String getActionTag() {
		return actionTag;
	}

	public void setActionTag(String actionTag) {
		this.actionTag = actionTag;
	}
	
	public Action(String tag){
		this.actionTag = tag;
	}

	private String actionTag;
	
	public void create(String order){
		System.out.println("创建订单："+order);
	}
	
	public void pay(String order){
		System.out.println("发起支付："+order);
	}
	
	public void refund(String order){
		System.out.println("发起退款："+order);
	}


}
