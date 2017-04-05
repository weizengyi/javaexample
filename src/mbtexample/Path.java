package mbtexample;

public class Path {

	public Status getCurrState() {
		return currState;
	}

	public void setCurrState(Status currState) {
		this.currState = currState;
	}

	public Status getNextState() {
		return nextState;
	}

	public void setNextState(Status nextState) {
		this.nextState = nextState;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	private Status currState;
	
	private Status nextState;
	
	private Action action;
	
	public Path(Status curr,Status next, Action act){
		this.currState = curr;
		this.nextState = next;
		this.action = act;
	}
	
	public String toString(){
		String toStr = getCurrState().getStateTag() 
				+" , action:" +getAction().getActionTag() +
				"  ,result: "+getNextState().getStateTag();
		return toStr ;
	}


}
