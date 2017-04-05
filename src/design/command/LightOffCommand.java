package design.command;

public class LightOffCommand implements Command {
	
	OutDoorLight light;
	
	public LightOffCommand(OutDoorLight light){
		this.light = light;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		light.off();
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		light.on();
	}
	
	

}
