package backend;

public class BMI {

    public Double value;
    public WeightType weightType;

    public BMI(User user) {
        value = (user.getWeight()) / Math.pow(user.getHeight() * 0.01,2);
        if (value < 18.5) {
            weightType = WeightType.underweight;
        } else if( value > 25.) {
            weightType = WeightType.flesh;
        } else {
            weightType = WeightType.normal;
        }
    }
}
