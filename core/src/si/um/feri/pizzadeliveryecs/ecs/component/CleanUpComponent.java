package si.um.feri.pizzadeliveryecs.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CleanUpComponent implements Component, Pool.Poolable {

    @Override
    public void reset() {
    }
}
