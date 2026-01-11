package Command;

import java.util.HashMap;
import java.util.Stack;

class Light {
    private boolean isOn = false;

    public void turnOn() {
        this.isOn = true;
        System.out.println("Light Turned On!");
    }

    public void turnOff() {
        this.isOn = true;
        System.out.println("Light Turned Off!");
    }
}

class Thermostat {
    private double temperature;

    public Thermostat(double temperature) {
        this.temperature = temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.err.println("Temperature set to: " + temperature);
    }

    public double getTemperature() {
        System.err.println("Current temperature is: " + temperature);
        return this.temperature;
    }
}

interface Command {
    void execute();

    void undo();
}

class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class setThermostatTemperatureCommand implements Command {
    private final Thermostat thermostat;
    private double newTemperature;
    private double prevTemperature;

    public setThermostatTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    public setThermostatTemperatureCommand(Thermostat thermostat, double temperature) {
        this.thermostat = thermostat;
        this.newTemperature = temperature;
    }

    @Override
    public void execute() {
        this.prevTemperature = thermostat.getTemperature();
        thermostat.setTemperature(newTemperature);
    }

    @Override
    public void undo() {
        thermostat.setTemperature(prevTemperature);
    }
}

class Remote {
    private Stack<Command> commands = new Stack<>();
    private HashMap<String, Command> links = new HashMap<>();

    public Remote() {
    }

    public void addCommand(String name, Command command) {
        links.putIfAbsent(name, command);
    }

    public void press(String name) {
        Command command = links.get(name);
        commands.add(command);
        command.execute();
    }

    public void undoLast() {
        if (!commands.isEmpty()) {
            Command command = commands.firstElement();
            commands.removeFirst();
            command.undo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Thermostat thermostat = new Thermostat(20);
        Remote remote = new Remote();
        remote.addCommand("lightOn", new LightOnCommand(light));
        remote.addCommand("lightOff", new LightOffCommand(light));
        // remote.addCommand("setTemp", new
        // setThermostatTemperatureCommand(thermostat));
        remote.press("lightOn");
        remote.undoLast();
        remote.press("lightOn");
        remote.press("lightOff");
        remote.undoLast();
        remote.undoLast();
        // remote.press("setTemp");
    }
}
