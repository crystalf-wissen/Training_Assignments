import java.util.*;
import java.util.concurrent.*;
 
class Bike implements Callable<Long> {
    private static final CountDownLatch raceStartSignal = new CountDownLatch(1);
    private int distance = RacingDetails.distance;
    String name;
    long startTime;
    long endTime;
    long timeTaken;
 
    Bike(String name) {
        this.name = name;
    }
 
    public Long call() {
        try {
            raceStartSignal.await();
            System.out.println(name + " is ready to race.....");
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted while waiting to start the race.");
        }
        startTime = System.currentTimeMillis();
        while (distance > 0) {
            try {
                long randomSpeed = 50 + (long) (Math.random() * 100);
                distance -= randomSpeed;
 
                if (distance < 0) {
                    distance = 0;
                }
                System.out.println(name + " has covered " + (RacingDetails.distance - distance) + "m.");
                Thread.sleep(randomSpeed);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        return timeTaken;
    }
 
    public static void startRace() {
        raceStartSignal.countDown();
    }
}
 
class RacingDetails {
    static int noOfBikers;
    static int distance;
    static String unit = "m";
}
 
class Dashboard {

    public static void displayRankings(Bike[] bikers) {
        Arrays.sort(bikers, Comparator.comparingLong(b -> b.timeTaken));

        System.out.println("\n--- Race Results ---");
        System.out.println("--------------------");
        System.out.println("Overall Standings:");
        System.out.println("--------------------");

        for (int i = 0; i < RacingDetails.noOfBikers; i++) {
            Bike biker = bikers[i];
            System.out.printf(
                "%2d. %-15s | Start (ms): %-13d | Finish (ms): %-13d| Duration: %-8d ms\n",
                    (i + 1),
                    biker.name,
                    biker.startTime, 
                    biker.endTime,
                    biker.timeTaken
            );
        }
        System.out.println("--------------------");
    }

}
 
public class BikeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Enter the number of bikers: ");
        RacingDetails.noOfBikers = scanner.nextInt();
        System.out.println("Enter the distance of the race: ");
        RacingDetails.distance = scanner.nextInt();
 
        Bike[] bikers = new Bike[RacingDetails.noOfBikers];
        ExecutorService executor = Executors.newFixedThreadPool(RacingDetails.noOfBikers);
        List<Future<Long>> results = new ArrayList<>();
 
        for (int i = 0; i < RacingDetails.noOfBikers; i++) {
            System.out.println("Enter the name of biker " + (i + 1) + ": ");
            String name = scanner.next();
            bikers[i] = new Bike(name);
            results.add(executor.submit(bikers[i]));
        }
        System.out.println("Race will begin in...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
 
        System.out.println();
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        System.out.println("Go....");
 
        Bike.startRace();
 
        for (int i = 0; i < RacingDetails.noOfBikers; i++) {
            try {
                bikers[i].timeTaken = results.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println(e);
            }
        }
 
        Dashboard.displayRankings(bikers);
 
        executor.shutdown();
        scanner.close();
    }
}
 