package State;

interface MachineState {
    void selectItem(VendingMachine machine, String itemCode);

    void insertCoin(VendingMachine machine, double amoubt);

    void dispenseItem(VendingMachine machine);
}

class IdleState implements MachineState {
    @Override
    public void selectItem(VendingMachine machine, String itemCode) {
        System.out.println("Item selected: " + itemCode);
        machine.setSelectedItem(itemCode);
        machine.setState(new ItemSelectedState());
    }

    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Please select an item before inserting coins.");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("No item selected. Nothing to dispense.");
    }
}

class ItemSelectedState implements MachineState {
    @Override
    public void selectItem(VendingMachine machine, String itemCode) {
        System.out.println("Item already selected: " + machine.getSelectedItem());
    }

    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Inserted $" + amount + " for item: " + machine.getSelectedItem());
        machine.setInsertedAmount(amount);
        machine.setState(new HasMoneyState());
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Insert coin before dispensing.");
    }
}

class HasMoneyState implements MachineState {
    @Override
    public void selectItem(VendingMachine machine, String itemCode) {
        System.out.println("Item already selected: " + machine.getSelectedItem());
    }

    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Money already inserted.");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Dispensing item: " + machine.getSelectedItem());
        machine.setState(new DispensingState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Item dispensed successfully.");
        machine.reset();
    }
}

class DispensingState implements MachineState {
    @Override
    public void selectItem(VendingMachine machine, String itemCode) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Already dispensing. Please wait.");
    }
}

class VendingMachine {
    private MachineState currentState;
    private String selectedItem;
    private double insertedAmount;

    public VendingMachine() {
        this.currentState = new IdleState();
    }

    public void setState(MachineState newState) {
        this.currentState = newState;
    }

    public void setSelectedItem(String itemCode) {
        this.selectedItem = itemCode;
    }

    public void setInsertedAmount(double amount) {
        this.insertedAmount = amount;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void selectItem(String itemCode) {
        currentState.selectItem(this, itemCode);
    }

    public void insertCoin(double amount) {
        currentState.insertCoin(this, amount);
    }

    public void dispenseItem() {
        currentState.dispenseItem(this);
    }

    public void reset() {
        this.selectedItem = "";
        this.insertedAmount = 0.0;
        this.currentState = new IdleState();
    }
}

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        vm.insertCoin(1.0); 
        vm.selectItem("A1");
        vm.insertCoin(1.5);
        vm.dispenseItem();

        System.out.println("\n--- Second Transaction ---");
        vm.selectItem("B2");
        vm.insertCoin(2.0);
        vm.dispenseItem();
    }
}
