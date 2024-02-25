package si.um.feri.pizzadeliveryecs.common;


public class GameManager {
    public static final GameManager INSTANCE = new GameManager();

    private int collectedPizzas; // result
    private int fuel; // health

    private GameManager() {
    }

    public int getCollectedPizzas() {
        return collectedPizzas;
    }

    public int getFuel() {
        return fuel;
    }

    public void resetResult() {
        collectedPizzas = 0;
        fuel = 100;
    }

    public void incCollectedPizzas() {
        collectedPizzas++;
    }

    public boolean isGameOver() {
        return fuel <= 0;
    }

    public void damage() {
        fuel--;
    }

    public void incFuel() {
        fuel++;
    }
}
