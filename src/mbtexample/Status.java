package mbtexample;

public class Status {
	
	public Status(String tag){
		this.stateTag = tag;
	}
	
	public String getStateTag() {
		return stateTag;
	}

	public void setStateTag(String stateTag) {
		this.stateTag = stateTag;
	}
//	状态标签
	private String stateTag;

}

