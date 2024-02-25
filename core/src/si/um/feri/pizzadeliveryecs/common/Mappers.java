package si.um.feri.pizzadeliveryecs.common;

import com.badlogic.ashley.core.ComponentMapper;

import si.um.feri.pizzadeliveryecs.ecs.component.BoundsComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.CarComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.DimensionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.FuelComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.HoleComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.HouseComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.MovementComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PizzaBoxComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PizzaSliceComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.StarComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.TextureComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.ZOrderComponent;


public final class Mappers {
    public static final ComponentMapper<HoleComponent> HOLE =
            ComponentMapper.getFor(HoleComponent.class);

    public static final ComponentMapper<FuelComponent> FUEL =
            ComponentMapper.getFor(FuelComponent.class);

    public static final ComponentMapper<HouseComponent> HOUSE =
            ComponentMapper.getFor(HouseComponent.class);

    public static final ComponentMapper<PizzaBoxComponent> PIZZA_BOX =
            ComponentMapper.getFor(PizzaBoxComponent.class);

    public static final ComponentMapper<PizzaSliceComponent> PIZZA_SLICE =
            ComponentMapper.getFor(PizzaSliceComponent.class);

    public static final ComponentMapper<StarComponent> STAR =
            ComponentMapper.getFor(StarComponent.class);

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<CarComponent> CAR =
            ComponentMapper.getFor(CarComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ZOrderComponent> Z_ORDER =
            ComponentMapper.getFor(ZOrderComponent.class);

    private Mappers() {
    }
}
