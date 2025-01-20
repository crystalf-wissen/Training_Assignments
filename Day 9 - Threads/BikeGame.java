import java.util.*;

class BikerData
{
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

class Biker extends Thread
{
    private int distCovered = 0;
    private Random rand;
    private long startTime;
    private long endTime;

    public Biker()
    {
        rand = new Random();
    }

	public void run()
	{
        startTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " started at  " + startTime);

		for(int i=0;i<=200;i++)
        {
            int distance = rand.nextInt(10);
            distCovered+=distance;
            System.out.println(Thread.currentThread().getName() + " has covered " + distCovered + " units.");
            if(distCovered>=500)
            {
                endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                BikerData data = new BikerData(Thread.currentThread().getName(), startTime, endTime, elapsedTime);
                BikeGame.bikerDataList.add(data);
                Thread.currentThread().stop();
            }
        }
	}
}
public class BikeGame
{
    public static List<BikerData> bikerDataList = new ArrayList<>();
	public static void main(String args[]) throws Exception
	{
        
        countdown();

        Thread b1 = new Biker();
        Thread b2 = new Biker();
        Thread b3 = new Biker();
        Thread b4 = new Biker();
        Thread b5 = new Biker();
        Thread b6 = new Biker();
        Thread b7 = new Biker();
        Thread b8 = new Biker();
        Thread b9 = new Biker();
        Thread b10 = new Biker();

        b1.setName("Biker 1");
        b2.setName("Biker 2");
        b3.setName("Biker 3");
        b4.setName("Biker 4");
        b5.setName("Biker 5");
        b6.setName("Biker 6");
        b7.setName("Biker 7");
        b8.setName("Biker 8");
        b9.setName("Biker 9");
        b10.setName("Biker 10");

		b1.start();
		b2.start();
		b3.start();
        b4.start();
        b5.start();
		b6.start();
		b7.start();
        b8.start();
		b9.start();
		b10.start();


        b1.join();
        b2.join();
        b3.join();
        b4.join();
        b5.join();
        b6.join();
        b7.join();
        b8.join();
        b9.join();
        b10.join();
        
        for (int i = 0; i < bikerDataList.size()-1; i++) {
            for (int j = i+1; j < bikerDataList.size(); j++) {
                if (bikerDataList.get(i).getDuration() > bikerDataList.get(j).getDuration()) {
                    BikerData temp = bikerDataList.get(i);
                    bikerDataList.set(i, bikerDataList.get(j));
                    bikerDataList.set(j, temp);
                }
            }
        }
        
        System.out.println("-----------------Dashboard-----------------");
        for (int i = 0; i < bikerDataList.size(); i++) {
            BikerData data = bikerDataList.get(i);
            System.out.println(data);
        }
	}

    static void countdown() {
        System.out.println("Race starting in...");

        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try
            {
                Thread.sleep(500); 
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Go!");
    }
}