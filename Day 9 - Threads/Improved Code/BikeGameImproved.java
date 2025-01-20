import java.util.*;

class BikerData {
    private String name;
    private long startTime;
    private long endTime;
    private long duration;

    public BikerData(String name, long startTime, long endTime, long duration) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String toString() {
        return name + " | Start Time: " + startTime + " | End Time: " + endTime + " | Duration: " + duration + " ms";
    }

    public long getDuration() {
        return duration;
    }
}

class Biker extends Thread {
    private int distCovered = 0;
    private Random rand;
    private long startTime;
    private long endTime;

    private static final Object lock = new Object();

    public Biker() {
        rand = new Random();
    }

    public void run() {
        try {
            synchronized (lock) {
                lock.wait();
            }

            startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " started at " + startTime);

            while (distCovered < RacingDetails.distance) {
                //System.out.println(Thread.currentThread().getName() + " has covered " + distCovered + " units.");
                try {
                    Thread.sleep(rand.nextInt(100));
                    distCovered += rand.nextInt(10);
                    //System.out.println(Thread.currentThread().getName() + " has covered " + distCovered + " units.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            BikerData data = new BikerData(Thread.currentThread().getName(), startTime, endTime, elapsedTime);
            BikeGameImproved.bikerDataList.add(data);

            System.out.println(Thread.currentThread().getName() + " finished in " + elapsedTime + " ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void notifyBikers() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}

class RacingDetails {
    static int noOfBikers;
    static int distance;
}

public class BikeGameImproved {
    public static List<BikerData> bikerDataList = new ArrayList<>();

    public static void main(String args[]) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of bikers: ");
        RacingDetails.noOfBikers = sc.nextInt();

        System.out.print("Enter race distance in meters: ");
        RacingDetails.distance = sc.nextInt();

        Thread[] bikers = new Thread[RacingDetails.noOfBikers];
        for (int i = 0; i < bikers.length; i++) {
            bikers[i] = new Biker();
            bikers[i].setName("Biker " + (i + 1));
            bikers[i].start();
        }

        countdown();

        Biker.notifyBikers();

        for (Thread biker : bikers) {
            biker.join();
        }

        bikerDataList.sort(Comparator.comparingLong(BikerData::getDuration));

        System.out.println("-----------------Dashboard-----------------");
        for (BikerData data : bikerDataList) {
            System.out.println(data);
        }
    }

    static void countdown() {
        System.out.println("Race starting in...");

        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Go!");
    }
}
