package si.um.feri.pizzadeliveryecs.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class HoleComponent implements Component, Pool.Poolable {
    public boolean hit;

    @Override
    public void reset() {
        hit = false;
    }
}
