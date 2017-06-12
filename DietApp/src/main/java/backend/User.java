package backend;

public class User {

    Integer age;
    Double weight;
    Double height;
    Gender gender;
    BMI bmi;
    BMR bmr;
    ActivityType activityType;

    public User(Integer age, Double weight, Double height, Gender gender, ActivityType activityType) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.bmi = new BMI(this);
        this.activityType = activityType;
        this.bmr = new BMR(this);
    }

    public Gender getGender() {
        return gender;
    }

    public BMI getBmi() {
        return bmi;
    }

    public BMR getBmr() {
        return bmr;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBmi(BMI bmi) {
        this.bmi = bmi;
    }

    public void setBmr(BMR bmr) {
        this.bmr = bmr;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
