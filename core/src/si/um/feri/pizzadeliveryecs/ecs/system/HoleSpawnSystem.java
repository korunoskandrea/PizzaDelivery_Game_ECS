package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;

import si.um.feri.pizzadeliveryecs.config.GameConfig;
import si.um.feri.pizzadeliveryecs.ecs.system.passive.EntityFactorySystem;

public class HoleSpawnSystem extends IntervalSystem {

    private EntityFactorySystem factory;

    public HoleSpawnSystem(){
        super(GameConfig.HOLE_SPAWN_TIME);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        factory = engine.getSystem(EntityFactorySystem.class);
    }

    @Override
    protected void updateInterval() {
        factory.createHole();
    }
}
