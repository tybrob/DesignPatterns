public class CommandDesignPattern {
    //Encapsulates a request as an object, thereby letting you parameterize clients with different requests
    //queue or log requests, and support undoable operations

    public void run() {

        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new LivingRoomLight();
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);

        remote.setCommand(0,lightOn);
        remote.setCommand(1,lightOff);
        remote.buttonWasPressed(0);
        remote.undoButtonWasPressed();
        remote.buttonWasPressed(1);
        remote.undoButtonWasPressed();
    }

    public interface Command {
        void execute();
        void undo(); //to proper implement undo you have to hold the previous state on the CommandImpl object
    }

    public class LightOnCommand implements Command {

        Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.on();
        }

        public void undo() {
            light.off();
        }
    }

    public class LightOffCommand implements Command {

        Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.off();
        }

        public void undo() {
            light.on();
        }
    }

    public class SimpleRemoteControl {

        Command[] slot;
        Command undoCommand;

        public SimpleRemoteControl() {
            slot = new Command[2];
        }
        public void setCommand(int slotNumber ,Command command) {
            this.slot[slotNumber] = command;
        }

        public void buttonWasPressed(int slotNumber) {
            this.slot[slotNumber].execute();
            this.undoCommand = this.slot[slotNumber];
        }

        public void undoButtonWasPressed() {
            undoCommand.undo();
        }
    }

    public abstract class Light {

        public void on() {
            System.out.println("Light is on");
        }

        public void off() {
            System.out.println("Light is off");
        }
    }

    public class LivingRoomLight extends Light { }
}
