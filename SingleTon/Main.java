package SingleTon;

class SingleTonNotThreadSafe {
    public static SingleTonNotThreadSafe instance;

    private SingleTonNotThreadSafe() {
    };

    public static SingleTonNotThreadSafe getInstance() {
        if (instance == null) {
            System.out.println("New");
            return new SingleTonNotThreadSafe();
        }
        System.out.println("Old");
        return instance;
    }
}

class SingleTonThreadSafe {
    public static volatile SingleTonThreadSafe instance;

    private SingleTonThreadSafe() {
    };

    public static synchronized SingleTonThreadSafe getInstance() {
        if (instance == null) {
            System.out.println("New");
            instance = new SingleTonThreadSafe();
        }
        System.out.println("Old");
        return instance;
    }
}

class DoubleLockingThreadSafe {
    public static DoubleLockingThreadSafe instance;

    private DoubleLockingThreadSafe() {
    };

    public static DoubleLockingThreadSafe getInstance() {
        if (instance == null) {
            synchronized (DoubleLockingThreadSafe.class) {
                if (instance == null) {
                    System.out.println("New");
                    instance = new DoubleLockingThreadSafe();
                }
            }
        }
        System.out.println("Old");
        return instance;
    }
}

class EagerInitialization {
    public static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {
    };

    public static EagerInitialization getInstance() {
        return instance;
    }
}

class BillPughInitialization {
    private BillPughInitialization() {
    };

    private static class InnerBillPughInitialization {
        private static final BillPughInitialization instance = new BillPughInitialization();
    }

    public static BillPughInitialization getInstance() {
        return InnerBillPughInitialization.instance;
    }
}

class StaticBlockInitialization {
    public static StaticBlockInitialization instance;

    private StaticBlockInitialization() {
    };

    static {
        try {
            instance = new StaticBlockInitialization();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockInitialization getInstance() {
        return instance;
    }
}

class TestClass {
    public static DoubleLockingThreadSafe method() {
        return DoubleLockingThreadSafe.getInstance();
    }
}

public class Main {
    public static void main(String[] args) {
        // SingleTonNotThreadSafe instance = SingleTonNotThreadSafe.getInstance();
        DoubleLockingThreadSafe instance = DoubleLockingThreadSafe.getInstance();
        TestClass.method();
    }
}