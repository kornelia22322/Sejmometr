package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import controller.HomeController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Builder Design Pattern
public class Deputy {

    @JsonView(views.Views.HomeView.class)
    private String name;
    private int area;
    private int interpolation_count;
    private double attendance;
    private String club;
    private int flight_count;

    @JsonView(views.Views.HomeView.class)
    private int id;
    private String town;
    private String voting_area;
    private Date birthdate;

    private Deputy(Builder builder) {
        name = builder.name;
        area = builder.area;
        interpolation_count = builder.interpolation_count;
        attendance = builder.attendance;
        club = builder.club;
        flight_count = builder.flight_count;
        id = builder.id;
        town = builder.town;
        voting_area = builder.voting_area;
        birthdate = builder.birthdate;
    }

    public static class Builder {

        private String name;
        private int area;
        private int interpolation_count;
        private double attendance;
        private String club;
        private int flight_count;
        private int id;
        private String town;
        private String voting_area;
        private Date birthdate;

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

        public Builder setTown(String town) {
            this.town = town;
            return this;
        }

        public Builder setVoting_area(String voting_area) {
            this.voting_area = voting_area;
            return this;
        }

        public Builder setBirthdate(String birthdate) {
            Date date;
            try {
                date = convertStringToDate(birthdate);
                this.birthdate = date;
            } catch (ParseException e) {
                System.out.println("Given birtdate in wrong format.");
            }
            return this;
        }

        private Date convertStringToDate(String stringDate) throws ParseException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) dateFormat.parse(stringDate);
            return date;
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

    public String getTown() { return town; }

    public String getVoting_area() {
        return voting_area;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
