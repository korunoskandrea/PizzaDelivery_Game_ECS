package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.ecs.component.BoundsComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.DimensionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;

public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            BoundsComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        bounds.rectangle.x = position.x;
        bounds.rectangle.y = position.y;
        bounds.rectangle.width = dimension.width;
        bounds.rectangle.height = dimension.height;
    }
}
