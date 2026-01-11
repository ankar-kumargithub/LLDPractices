package ChainOfResponsibility;

enum LogTypes{
    TRACE,
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    CRITICAL
}

abstract class Logs{
    protected Logs nextHandler;
    public Logs(Logs nextHandler) {
        this.nextHandler = nextHandler;
    }
    abstract void handle(LogTypes log, String message);
}

class TraceLog extends Logs{
    private final LogTypes logType = LogTypes.TRACE;
    public TraceLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("TRACE LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}

class DebugLog extends Logs{
    private final LogTypes logType = LogTypes.DEBUG;
    public DebugLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("DEBUG LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}

class InfoLog extends Logs{
    private final LogTypes logType = LogTypes.INFO;
    public InfoLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("INFO LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}

class WarningLog extends Logs{
    private final LogTypes logType = LogTypes.WARNING;
    public WarningLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("WARNING LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}

class ErrorLog extends Logs{
    private final LogTypes logType = LogTypes.ERROR;
    public ErrorLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("ERROR LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}

class CriticalLog extends Logs{
    private final LogTypes logType = LogTypes.CRITICAL;
    public CriticalLog(Logs nextHandler){
        super(nextHandler);
    }

    @Override
    public void handle(LogTypes log, String message){
        if(log == logType){
            System.out.println("CRITICAL LOG: "+ message);
            return;
        }
        if(this.nextHandler != null){
            this.nextHandler.handle(log, message);
        }
        else{
            System.err.println("This error cannot be handled! => Error: " + message);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Logs log = new TraceLog(new DebugLog(new InfoLog(new WarningLog(new ErrorLog(new CriticalLog(null))))));
        log.handle(LogTypes.CRITICAL, "Critical Message");
    }
}
