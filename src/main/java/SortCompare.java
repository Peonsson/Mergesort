/**
 * Created by Peonsson on 17/03/16.
 */
public class SortCompare {

    private Strategy strategy;

    public SortCompare(Strategy strategy) {
        this.strategy = strategy;
    }

    public void sort() {
        strategy.sort();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}