package design.command;

public class LightOnCommand implements Command{
	
	OutDoorLight light;
	
	public LightOnCommand(OutDoorLight light){
		this.light = light ;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		light.on();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		light.off();
	}

}
