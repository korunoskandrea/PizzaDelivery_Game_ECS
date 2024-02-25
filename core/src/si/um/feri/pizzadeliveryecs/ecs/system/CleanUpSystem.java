package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.ecs.component.CleanUpComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.DimensionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;


public class CleanUpSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CleanUpComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    public CleanUpSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        if (position.y < -dimension.height) {
            getEngine().removeEntity(entity);
        }
    }
}
