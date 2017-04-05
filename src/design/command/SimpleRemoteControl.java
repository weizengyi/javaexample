package design.command;

import org.junit.Test;

public class SimpleRemoteControl {
	
	Command slot;
	
	public SimpleRemoteControl(){};
	
	public void setCommand(Command command){
		slot = command;
	}
	
	public void buttonWasPressed(){
		slot.execute();
	}
	
	@Test
	public void simpleTest(){
		SimpleRemoteControl remote = new SimpleRemoteControl();
		OutDoorLight light = new OutDoorLight();
		LightOnCommand lightOn = new LightOnCommand(light);
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
	}

}
