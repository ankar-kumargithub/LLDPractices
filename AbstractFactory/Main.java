package AbstractFactory;

interface Button {
    void paint();

    void onClick();
}

interface CheckBox {
    void paint();

    void onSelect();
}

class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("Windows Button Paqint");
    }

    @Override
    public void onClick() {
        System.out.println("Windows Button on cLICK");
    }
}

class MacosButton implements Button {
    @Override
    public void paint() {
        System.out.println("MacOs Button Paqint");
    }

    @Override
    public void onClick() {
        System.out.println("MacOs Button on cLICK");
    }
}

class WindowsCheckbox implements CheckBox {
    @Override
    public void paint() {
        System.out.println("Windows CheckBox Button Paqint");
    }

    @Override
    public void onSelect() {
        System.out.println("Windows CheckBox on select");
    }
}

class MacosCheckbox implements CheckBox {
    @Override
    public void paint() {
        System.out.println("MacOs CheckBox Button Paqint");
    }

    @Override
    public void onSelect() {
        System.out.println("MacOs CheckBox on select");
    }
}

interface GUIFactory {
    Button createButton();

    CheckBox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacosButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new MacosCheckbox();
    }
}

class Application {
    private final Button button;
    private final CheckBox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.paint();
        checkbox.paint();
    }
}

public class Main {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        GUIFactory factory;

        if (os.contains("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }

        Application app = new Application(factory);
        app.renderUI();

    }
}
