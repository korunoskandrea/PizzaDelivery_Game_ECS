package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.ecs.component.MovementComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;

public class MovementSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            MovementComponent.class
    ).get();

    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;
        //position.r += movement.rSpeed;
    }
}

