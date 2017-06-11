package backend;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */

public class BMR {

    public Double value;
    public Double caloriesNeeded;

    public BMR(User user) {
        this.value = 9.99 * user.getWeight() + 6.25 * user.getHeight() - 4.92 * user.getAge() + (user.gender == Gender.female ? -161 : 5);
        switch (user.activityType) {
            case low:
                this.caloriesNeeded = this.value;
                break;
            case normal:
                this.caloriesNeeded = this.value * 1.5;
                break;
            case high:
                this.caloriesNeeded = this.value * 2.;
                break;
        }
    }
}
