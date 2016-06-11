/**
 * Created by Radek on 2016-06-07.
 */
public class Conjunction extends TwoArgumentOperator {

    public Boolean solve(Boolean boolean1, Boolean boolean2) {
        return boolean1 && boolean2;
    }

    @Override
    public String toString() {
        return "*";
    }
}
