import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class readFile {
    private HashMap<String,ArrayList<String>> list;
    private LinkedList<String[]> times;
    private File f;
    private final String college = "courses20.txt";

    //Constructors to read file name, default is 20 courses.
    public readFile() {
        f = new File(college);
    }
    public readFile(String a) {
        f = new File(a);
    }

    //Method to create a Hashmap with time as Keys and an array of courses as Values
    public void scanFile() {
        list = new HashMap<String,ArrayList<String>>();
        times = new LinkedList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] value = line.split("\t"); //length = 11
                if (!value[7].equals("ONLINE") &&
                    !value[8].equals("COURSE") &&
                    !value[7].equals("TBA") &&
                    !value[8].equals("TBA")) {
                    String time = value[6] + " @ " + value[7] + " - " + value[8];
                    String[] t = {value[6],value[7] + " - " + value[8]};
                    if (times.size()!=0) {
                        boolean listed = false;
                        for (String[] i : times) {
                            if (i[0].equals(t[0]) && i[1].equals(t[1])) {
                                listed = true;
                            }
                        }
                        if (listed) {}
                        else if (value[6].length()>times.get(0).length) {
                            times.addFirst(t);
                        }
                        else {
                            times.add(t);
                        }
                    }
                    else {
                        times.add(t);
                    }

                    String course = value[0];

                    if (!list.containsKey(time)) {
                        ArrayList<String> s = new ArrayList<String>();
                        s.add(course);
                        list.put(time,s);
                    }
                    else {
                        ArrayList<String> a = list.get(time);
                        a.add(course);
                        list.put(time,a);
                    }
                }
            }
            //Print out number of groups before collision.
            //Courses with the same time are added into the same group//
            //-------------------------//
            System.out.println("Original size: "+list.size());
            //-------------------------//
        } catch (FileNotFoundException e) {}
    }
    
    //Method to group courses with colliding times
    public void combine() {
    for (int i=0;i<times.size();i++) {
        if (times.get(i)==null) {}
        else {
            Times time_i= new Times(times.get(i)[1]);
            for (int j=i+1;j<times.size();j++) {
                if (times.get(j)==null) {}
                else {
                    Times time_j = new Times(times.get(j)[1]);
                    if (time_i.Collide(time_j)) {
                        boolean sameDay = false;
                        ArrayList<String> i_ = new ArrayList<>(Arrays.asList(times.get(i)[0].split("")));
                        ArrayList<String> j_ = new ArrayList<>(Arrays.asList(times.get(j)[0].split("")));
                        for (String m : j_) {
                            if (i_.contains(m)) {
                                sameDay = true;
                            }
                        }
                        if (sameDay) {
                            String key_i = times.get(i)[0] + " @ " + times.get(i)[1];
                            String key_j = times.get(j)[0] + " @ " + times.get(j)[1];
                            
                            ArrayList<String> i_v = list.get(key_i);
                            
                            ArrayList<String> j_v = list.get(key_j);

                            i_v.addAll(j_v);
                            list.put(key_i,i_v);
                            list.remove(key_j);
                            times.set(j, null);
                        }
                    }
                }
            }
        }
    }
    //Print out number of groups after collisions are combined//
    //-------------------------//
    System.out.println("Combined size: "+list.size());
    System.out.println();
    //-------------------------//    

    //Print out times with groups of courses combined based on collision//
    //-------------------------//
    for (String i : list.keySet()) {
        System.out.println(i);
    }
    times.removeIf(n->n==null);
    }
    //-------------------------//

    //Method to get Hashmap list of class readFile, to be used for GetFinalsSchedule
    public HashMap<String,ArrayList<String>> getSchedule() {
        return list;
    }

    //Method to get size of Hashmap list of class readFile, to be used in main
    public int getSize() {
        return list.size();
    }
}
