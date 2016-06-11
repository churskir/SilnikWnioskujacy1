/**
 * Created by Radek on 2016-06-07.
 */
public class Negation extends OneArgumentOperator {

    public Boolean solve(Boolean bool) {
        return !bool;
    }

    @Override
    public String toString() {
        return "-";
    }
}
