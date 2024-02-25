package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.config.GameConfig;
import si.um.feri.pizzadeliveryecs.ecs.component.DimensionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.WorldWrapComponent;
public class WorldWrapSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            WorldWrapComponent.class
    ).get();

    public WorldWrapSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        if (position.x >= GameConfig.WIDTH - dimension.width) {
            position.x = GameConfig.WIDTH - dimension.width;
        } else if (position.x < 0) {
            position.x = 0;
        }
    }
}
