package Adapter;

class WeightMachine {
    private int weightLbs;

    public WeightMachine(int weight) {
        this.weightLbs = weight;
    }

    public int getWeightInLbs() {
        return weightLbs;
    }
}

class WeightAdapter {
    private WeightMachine machine;

    public WeightAdapter(WeightMachine machine) {
        this.machine = machine;
    }

    public int getWeight() {
        return (int) (machine.getWeightInLbs() * 2.5);
    }
}

public class Main {
    public static void main(String[] args) {
        WeightMachine machine = new WeightMachine(13);
        WeightAdapter adapter = new WeightAdapter(machine);
        System.err.println(adapter.getWeight());
    }
}
