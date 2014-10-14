/**
 * Created by Andrei on 10/14/2014.
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.InetAddress;


public class ThreadPoolApp  {
    public static void main (String[] args) {
        if (args.length < 2)
            ThreadPoolApp.error();
        try {
            int numberOfJobs = Integer.parseInt(args[0]);
            int numberOfThreads = Integer.parseInt(args[1]);
            if ((numberOfJobs < 1) || (numberOfThreads < 1))
                ThreadPoolApp.error();
            ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

            Job[] jobs = new Job[numberOfJobs];
            for (int i = 0; i < numberOfJobs; i++) {

                if (i == 0) {
                    jobs[0] = new Job(0){
                        public void run() {
                            //Undertake required work, here we will emulate it by sleeping for a period
                            System.out.println("Job: " + jobNumber + " is being processed by thread: "
                                    + Thread.currentThread().getName());

                            try {
                                Thread.sleep((int)(1000));
                                System.out.println("Hello World!");
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                //get current date time with Date()
                                Date date = new Date();
                                System.out.println(dateFormat.format(date));

                                //get current date time with Calendar()
                                Calendar cal = Calendar.getInstance();
                                System.out.println(dateFormat.format(cal.getTime()));
                            } catch (InterruptedException e) {
                                //no cathing as example should not experience interruptions
                            }
                            System.out.println("Job: " + jobNumber + " is ending in thread: "
                                    + Thread.currentThread().getName());
                        }

                    };
                    pool.execute(jobs[0]);
                }

                if (i == 1) {
                    jobs[1] = new Job(1){
                        public void run() {
                            //Undertake required work, here we will emulate it by sleeping for a period
                            System.out.println("Job: " + jobNumber + " is being processed by thread: "
                                    + Thread.currentThread().getName());

                            try {

                                Thread.sleep((int)(1000));
                                try {
                                    java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
                                    System.out.println("Hostname of local machine: " + localMachine.getHostName());
                                } catch (Exception ex) {
                                    //comment
                                }
                            } catch (InterruptedException e) {
                                //no cathing as example should not experience interruptions
                            }
                            System.out.println("Job: " + jobNumber + " is ending in thread: "
                                    + Thread.currentThread().getName());
                        }

                    };
                    pool.execute(jobs[1]);
                }
                if ( i > 1) {
                    jobs[i] = new Job(i);
                    pool.execute(jobs[i]);
                }
                //pool.execute(jobs[i]);  //executes the given command at some future time

            }
            pool.shutdown(); //Shutdown: previously submitted tasks are executed,
            //but no new tasks will be accepted.
            System.out.println(" Last line " + Thread.currentThread().getName());
        } catch (NumberFormatException e) {
            ThreadPoolApp.error();
        }
    }
    private static void error() {
        System.out.println("ThreadPoolApp must be run with two positive valued" +
                " integer arguments. The first detaling the number of jobs" +
                " the second the number of processing threads in the pool");
        System.exit(0); //exit the program
    }

}