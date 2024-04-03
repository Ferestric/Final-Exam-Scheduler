import java.util.ArrayList;
import java.util.HashMap;

public class College_Finals {
    public static void main(String[] args) {
        readFile a = new readFile("courses336.txt");
        a.scanFile();
        a.combine();
        GetFinalsSchedule m = new GetFinalsSchedule();
        HashMap<String,ArrayList<String>> s = a.getSchedule();
        m.GetFinalsSchedule(a.getSize());
        m.assignCourses(s);
        System.out.println();
        System.out.println("Acknowledgement: \nThe example lists of courses used in this project was downloaded from https://github.com/ziyan/final-exam-scheduler/tree/master/data. No code was replicated from their Github.");
    }
}
