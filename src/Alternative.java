/**
 * Created by Radek on 2016-06-07.
 */
public class Alternative extends TwoArgumentOperator {
    @Override
    public String toString(){
        return "+";
    }

    public Boolean solve(Boolean boolean1, Boolean boolean2) {
        return boolean1 || boolean2;
    }
}
