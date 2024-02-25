package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.config.GameConfig;
import si.um.feri.pizzadeliveryecs.ecs.component.CarComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.MovementComponent;

public class CarInputSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CarComponent.class,
            MovementComponent.class
    ).get();

    public CarInputSystem() {
        super(FAMILY);
    }

    // we don't need to override the update Iterating system method because there is no batch begin/end

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = Mappers.MOVEMENT.get(entity);
        movement.xSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movement.xSpeed = GameConfig.MAX_CAR_X_SPEED * deltaTime;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movement.xSpeed = -GameConfig.MAX_CAR_X_SPEED * deltaTime;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            // TODO SHOOT SLICE
        }

    }
}