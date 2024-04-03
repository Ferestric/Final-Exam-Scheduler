import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.math.*;

public class GetFinalsSchedule {
    private ArrayList<String> days;
    private ArrayList<Times[]> times;
    private HashMap<String, ArrayList<String>> courseFinals;
    private final int size = 28;

    //Constructor to generate finals schedule with 28 periods.
    public GetFinalsSchedule() {
        days = new ArrayList<>();
        times = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        
        Times time9 = new Times("900A - 1200N");
        Times time12 = new Times("1200N - 300P");
        Times time3 = new Times("300P - 6PM");
        Times time6 = new Times("600P - 9PM");
        Times[] timeList = {time9, time12, time3, time6};
        
        for (int i=0;i<days.size();i++) {
            times.add(timeList.clone());
        }
    }

    //Constructor to fix the schedule based on the actual size
    public void GetFinalsSchedule(int s) {
        int n = size/s;
        int m = size%s;
        int step = n;
        if (n>=2) {
            int index = 0;
            for (int i=0;i<times.size();i++) {
                for (int j=0;j<times.get(i).length;j++) {
                    index += 1;
                    if (index>size-m) {
                        times.get(i)[j]=null;
                    }
                    else if (index==n) {
                        
                        n=n+step;
                    }
                    else {
                        times.get(i)[j]=null;
                    }
                }
            }
        }
        else if (n<=1) {
            int index = 0;
            for (int i=0;i<times.size();i++) {
                for (int j=0;j<times.get(i).length;j++) {
                    index += 1;
                    if (index==s) {
                        
                    }
                    else {
                        times.get(i)[j]=null;
                        s+=1;
                    }
                }
            }
        }
        //Test list of finals times without courses (non-available times are null):

        // for (int i=0;i<times.size();i++) {
        //     for (int j=0;j<times.get(i).length;j++) {
        //         System.out.print(times.get(i)[j]);
        //     }
        //     System.out.println();
        // }
    }

    //Method to assignCourses to schedule of available final periods.
    public void assignCourses(HashMap<String,ArrayList<String>> list) {  
        courseFinals = new HashMap<String, ArrayList<String>>();
        Iterator<ArrayList<String>> v = list.values().iterator();
        for (int i=0;i<days.size();i++) {
            for (int j=0;j<times.get(i).length;j++) {
                if (times.get(i)[j] != null) {
                    String dayName = days.get(i) +" @ "+times.get(i)[j];
                    courseFinals.put(dayName,v.next());
                }
            }
        }
        //Print the finals period (key) and the group of courses (value)
        //-------------------------//
        for (String i : courseFinals.keySet()) {
            System.out.print(i+ "    ");
            System.out.println(courseFinals.get(i));
        }
        //-------------------------//
    }
}
