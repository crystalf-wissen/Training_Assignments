sealed class Manager permits SalesManager, MarketingManager {
    int x = 10;
    public void abc() {
        System.out.println("HI");
    }
}

final class Peun { //extends Manager 
}

non-sealed class SalesManager extends Manager {
}

sealed class MarketingManager extends Manager permits DistributionManager {
}

final class DistributionManager extends MarketingManager {
}

class GlobalManager extends SalesManager {
}

public class SealedDemo {
    public static void main (String args[]) {
        GlobalManager gm = new GlobalManager();
        gm.abc();
    }
}