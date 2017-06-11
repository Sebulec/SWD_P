package backend;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
enum ActivityRatio {
    low(1.), normal(1.5), high(2.);
    double value;

    ActivityRatio(double v) {
        this.value = v;
    }
}
public class BMR {

    public Double value;

}
