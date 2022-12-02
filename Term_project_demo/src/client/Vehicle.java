package client;

public class Vehicle {
    String Agency;
    String CoachNo;
    String Time;
    String StartingCounter;
    String EndCounter;
    String CoachType;
    String Fare;
    String ArrivalTime;


    public Vehicle(String vehicleData) {
        String[] data = vehicleData.split("#");
        Agency = data[2];
        CoachNo = data[3];
        Time = data[4];
        StartingCounter = data[5];
        EndCounter = data[6];
        CoachType = data[7];
        Fare = data[8];
        ArrivalTime = data[9];
    }

    public String getAgency() {
        return Agency;
    }

    public void setAgency(String agency) {
        Agency = agency;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStartingCounter() {
        return StartingCounter;
    }

    public void setStartingCounter(String startingCounter) {
        StartingCounter = startingCounter;
    }

    public String getEndCounter() {
        return EndCounter;
    }

    public void setEndCounter(String endCounter) {
        EndCounter = endCounter;
    }

    public String getCoachType() {
        return CoachType;
    }

    public void setCoachType(String coachType) {
        CoachType = coachType;
    }

    public String getFare() {
        return Fare;
    }

    public void setFare(String fare) {
        Fare = fare;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getCoachNo() {
        return CoachNo;
    }

    public void setCoachNo(String coachNo) {
        CoachNo = coachNo;
    }
}
