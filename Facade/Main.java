package Facade;

class VersionControl {
    public boolean pullLatestChanges(String branch) {
        System.out.println("Pulling Latest Changes from branch " + branch);
        simulateDelay(5000);
        System.out.println("Pulled Latest Changes from branch " + branch);
        return true;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pull interrupted!");
        }
    }
}

class BuildSystem {
    public boolean compileProject() {
        System.out.println("BuildSystem: Compiling project...");
        simulateDelay(5000);
        System.out.println("BuildSystem: Build successful.");
        return true;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Build interrupted!");
        }
    }
}

class TestingFramework {
    public boolean runUnitTests() {
        System.out.println("Testing: Running unit tests...");
        simulateDelay(1500);
        System.out.println("Testing: Unit tests passed.");
        return true;
    }

    public boolean runIntegrationTests() {
        System.out.println("Testing: Running integration tests...");
        simulateDelay(3000);
        System.out.println("Testing: Integration tests passed.");
        return true;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Tests interrupted!");
        }
    }
}

class DeploymentTarget {
    public boolean transferArtifact(String artifactPath, String server) {
        System.out.println("Deployment: Transferring " + artifactPath + " to " + server + "...");
        simulateDelay(1000);
        System.out.println("Deployment: Transfer complete.");
        return true;
    }

    public boolean activateNewVersion(String server) {
        System.out.println("Deployment: Activating new version on " + server + "...");
        simulateDelay(500);
        System.out.println("Deployment: Now live on " + server + "!");
        return true;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Deployment interrupted!");
        }
    }
}

class DeploymentFacade {
    private final VersionControl versionControl;
    private final BuildSystem buildSystem;
    private final TestingFramework testingFramework;
    private final DeploymentTarget deploymentTarget;

    public DeploymentFacade() {
        this.versionControl = new VersionControl();
        this.buildSystem = new BuildSystem();
        this.testingFramework = new TestingFramework();
        this.deploymentTarget = new DeploymentTarget();
    }

    public boolean deployApplication(String branch, String serverAddress) {
        try {
            if (!versionControl.pullLatestChanges(branch)) {
                return rollback();
            }
            if (!buildSystem.compileProject()) {
                return rollback();
            }
            if (!testingFramework.runUnitTests()) {
                return rollback();
            }
            if (!testingFramework.runIntegrationTests()) {
                return rollback();
            }
            if (!deploymentTarget.transferArtifact(branch, serverAddress)) {
                return rollback();
            }
            if (!deploymentTarget.activateNewVersion(serverAddress)) {
                return rollback();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Deployment failed: " + e.getMessage());
            return rollback();
        }
    }

    private boolean rollback() {
        System.out.println("Rolling back changes...");
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        DeploymentFacade facade = new DeploymentFacade();
        boolean success = facade.deployApplication("main", "http://121.000.432");
        System.out.println("\nDeployment " + (success ? "successful!" : "failed!"));
    }
}