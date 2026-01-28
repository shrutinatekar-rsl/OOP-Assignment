interface Drive {
    void run();
    void stop();
}

interface Steer {
    void steer();
}

class HandleBar implements Steer {
    @Override 
    public void steer() {
        System.out.println("HandleBar");
    }
}

class Engine {
    public void start() {
        System.out.println("Engine Started");
        Ignition ignite = new Ignition();
        ignite.spark(); 
    }

    class Ignition{
        void spark(){
            System.out.println("Spark");
        }
    }
}

class Brake {
    public void apply(){
        System.out.println("Applied brakes");
    }
}

class Accessory {
    private String name;

    public Accessory(String name){
        this.name = name;
    }

    public void use(){
        System.out.println("Using "+name);
    }
}

abstract class Vehicle implements Drive {
    private String model;
    private int speed;
    private int wheels;

    protected Engine engine;
    protected Brake brake;
    protected Steer steer;
    protected Accessory accessory;

    public Vehicle(String model, int wheels, Accessory accessory){
        this.model = model;
        this.wheels = wheels;
        this.accessory = accessory;
        this.engine = new Engine();
        this.brake = new Brake();
    }

    public abstract void manufacture();

    public String getModel(){
        return model;
    }

    public int getSpeed(){
        return speed;
    }

    public int getWheels(){
        return wheels;
    }

    protected void setSpeed(int speed){
        this.speed = speed;
    }   
}

class SteeringWheel implements Steer {
    @Override 
    public void steer() {
        System.out.println("Steering Wheel");
    }
}

class Car extends Vehicle{

    public Car (String model, Accessory accessory){
        super(model, 4, accessory);
        this.steer = new SteeringWheel();
    }

    @Override
    public void manufacture() {
        System.out.println("Created Car");
    }

    @Override
    public void run(){
        engine.start();
        steer.steer();
        accessory.use();
        setSpeed(80);
        System.out.println("Car running");
    }

    @Override
    public void stop(){
        brake.apply();
        setSpeed(0);
        System.out.println("Car stopped");
    }
}

class Cycle extends Vehicle{
    public Cycle(String model, Accessory accessory){
        super(model, 2, accessory);
        this.steer = new HandleBar();
    }

    public void manufacture(){
        System.out.println("Created Cycle");
    }

    public void run(){
        steer.steer();
        accessory.use();
        setSpeed(20);
        System.out.println("Cycle running");
    }

    public void stop(){
        brake.apply();
        setSpeed(0);
        System.out.println("Cycle stopped");
    }
}

class Scooter extends Vehicle{
    public Scooter(String model, Accessory accessory){
        super(model, 2, accessory);
        this.steer = new HandleBar();
    }

    @Override 
    public void manufacture(){
        System.out.println("Scooter created");
    }

    @Override
    public void run(){
        engine.start();
        steer.steer();
        accessory.use();
        setSpeed(50);
        System.out.println("Scooter running");
    }

    public void stop(){
        brake.apply();
        setSpeed(0);
        System.out.println("Scooter stopped");
    }
}

class Factory{
    public static Vehicle creatVehicle(String type){
        Accessory accessory = new Accessory("Accessory");
        if (type.equals("car")){
            return new Car("Sedan", accessory);
        }
        else if(type.equals("cycle")){
            return new Cycle("Gear", accessory);
        }
        else if(type.equals("scooter")){
            return new Scooter("Elrctric", accessory);
        }
        return null;
    }
}
public class vehicleFactory {
    public static void main(String args[]){
        Vehicle car = Factory.creatVehicle("car");
        Vehicle cycle = Factory.creatVehicle("cycle");
        Vehicle scooter = Factory.creatVehicle("scooter");

        car.run();
        car.stop();
        
        System.out.println();

        scooter.run();
        scooter.stop();

        System.out.println();
        
        cycle.run();
        cycle.stop();
    }
}