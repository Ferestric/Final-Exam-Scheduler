//Times class are in the format of timeStart (timeA) - timeEnd (timeB) (E.g: 10:00am - 10:50am)
public class Times {
    private Time timeA;
    private Time timeB;
    
    //Each Time is in the format of hour:minute (E.g: 10:00). Meridiem (am/pm) is changed into 24 hours setting for easier comparison.
    public class Time {
        private int hour;
        private int minute;
        
        //Constructor for class Time to create time and determine Meridiem.
        public Time(int h, int m, String p) {
            if (p.equals("P") || p.equals("PM")) {
                hour = h+12;
            }
            else if (p.equals("N")) {
                hour = 12;
            }
            else {
                hour = h;
            }

            minute = m;
        }
    }
    
    //Constructor for class Times to assign timeA and timeB based on a given string. (E.g: 600P - 950P)
    public Times(String t) {
        String [] T = t.split(" - ");
        int h = 0;
        int m = 0;
        String p = null;
        //This condition is to adjust to that one case of time which for some random reason has : in its time (5:30AM)
        if (T[0].contains(":")) {
            h = Integer.parseInt(T[0].substring(0, 1));
            m = Integer.parseInt(T[0].substring(2, 4));
            p = T[0].substring(4);
        }
        else if (T[0].length()==5 || T [0].length()==6) {
            h = Integer.parseInt(T[0].substring(0, 2));
            m = Integer.parseInt(T[0].substring(2, 4));
            p = T[0].substring(4);
        }
        else if (T[0].length()==4) {
            h = Integer.parseInt(T[0].substring(0, 1));
            m = Integer.parseInt(T[0].substring(1, 3));
            p = T[0].substring(3);
        }
        timeA = new Time(h, m, p);
        if (T[1].length()==5 || T[1].length()==6) {
            h = Integer.parseInt(T[1].substring(0, 2));
            m = Integer.parseInt(T[1].substring(2, 4));
            p = T[1].substring(4);
        }
        else if (T[1].length()==4) {
            h = Integer.parseInt(T[1].substring(0, 1));
            m = Integer.parseInt(T[1].substring(1, 3));
            p = T[1].substring(3);
        }
        timeB = new Time(h ,m ,p);
    }

    //Method to determine whether they do collide, which would return true. Else false.
    public boolean Collide(Times other) {
        if (this.timeB.hour<other.timeA.hour) {
            return false;
        }
        else if (this.timeA.hour>other.timeB.hour) {
            return false;
        }
        else if (this.timeB.hour==other.timeA.hour) {
            if (this.timeB.minute<=other.timeA.minute) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (this.timeA.hour==other.timeB.hour) {
            if (this.timeA.minute>=other.timeB.minute) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
    }

    @Override
    public String toString() {
        String s = timeA.hour + ":" + timeA.minute + "-" + timeB.hour + ":" + timeB.minute;
        return s;
    }
}
