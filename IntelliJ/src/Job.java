/**
 * Created by Andrei on 10/14/2014.
 */
public class Job implements Runnable {
    //the counter of jobs
    protected int jobNumber;

    Job(int jobName) {
        this.jobNumber = jobNumber;
    }

    public void run() {
        //Undertake required work, here we will emulate it by sleeping for a period
        System.out.println("Job: " + jobNumber + " is being processed by thread: "
                + Thread.currentThread().getName());

        try {
            Thread.sleep((int)(1000));
            System.out.println("Hello");
        } catch (InterruptedException e) {
            //no cathing as example should not experience interruptions
        }
        System.out.println("Job: " + jobNumber + " is ending in thread: "
                + Thread.currentThread().getName());
    }

}

