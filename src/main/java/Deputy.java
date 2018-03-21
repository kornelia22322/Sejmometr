import java.util.Comparator;

//Builder Design Pattern
public class Deputy {
    private String name;
    private int area;
    private int interpolation_count;
    private double attendance;
    private String club;
    private int flight_count;
    private int id;

    private Deputy(Builder builder) {
        name = builder.name;
        area = builder.area;
        interpolation_count = builder.interpolation_count;
        attendance = builder.attendance;
        club = builder.club;
        flight_count = builder.flight_count;
        id = builder.id;
    }

    public static class Builder {

        private String name;
        private int area;
        private int interpolation_count;
        private double attendance;
        private String club;
        private int flight_count;
        private int id;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setArea(int area) {
            this.area = area;
            return this;
        }

        public Builder setInterpolation_count(int interpolation_count) {
            this.interpolation_count = interpolation_count;
            return this;
        }

        public Builder setAttendance(double attendance) {
            this.attendance = attendance;
            return this;
        }

        public Builder setClub(String club) {
            this.club = club;
            return this;
        }

        public Builder setFlight_count(int flight_count) {
            this.flight_count = flight_count;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Deputy build() {
            return new Deputy(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }

    public int getInterpolation_count() {
        return interpolation_count;
    }

    public double getAttendance() {
        return attendance;
    }

    public String getClub() {
        return club;
    }

    public int getFlight_count() {
        return flight_count;
    }

    public int getId() {
        return id;
    }

}
