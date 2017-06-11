package backend;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
enum Gender {
    male, female
}
enum ActivityType {
    low, normal,high
}
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
}
