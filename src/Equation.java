import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public abstract class Equation {

    public abstract Boolean solve(Collection<Fact> facts) throws NoFactException;
}
