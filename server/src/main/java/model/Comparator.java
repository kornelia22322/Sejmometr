package model;

import java.util.Comparator;

class CompareByFlightsNum implements Comparator<Deputy> {
        public int compare(Deputy one, Deputy other) {
            if (one.getFlight_count() > other.getFlight_count()) return -1;
            else if(one.getFlight_count() < other.getFlight_count()) return 1;
            else return 0;
        }
};

class CompareByAttendance implements Comparator<Deputy> {
        public int compare(Deputy one, Deputy other) {
            if (one.getAttendance() > other.getAttendance()) return -1;
            else if(one.getAttendance() < other.getAttendance()) return 1;
            else return 0;
        }
};