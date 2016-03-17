/**
 * Created by Peonsson on 17/03/16.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void sort() {
        strategy.sort();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}