package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import si.um.feri.pizzadeliveryecs.common.GameManager;
import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.ecs.component.BoundsComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.CarComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.FuelComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.HoleComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.HouseComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PizzaBoxComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PizzaSliceComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.StarComponent;
import si.um.feri.pizzadeliveryecs.ecs.system.passive.SoundSystem;

public class CollisionSystem extends EntitySystem {

    private static final Family CAR_FAMILY = Family.all(CarComponent.class, BoundsComponent.class).get();
    private static final Family HOLE_FAMILY = Family.all(HoleComponent.class, BoundsComponent.class).get();
    private static final Family HOUSE_FAMILY = Family.all(HouseComponent.class, BoundsComponent.class).get();
    private static final Family STAR_FAMILY = Family.all(StarComponent.class, BoundsComponent.class).get();
    private static final Family FUEL_FAMILY = Family.all(FuelComponent.class, BoundsComponent.class).get();
    private static final Family PIZZA_BOX_FAMILY = Family.all(PizzaBoxComponent.class, BoundsComponent.class).get();
    private static final Family PIZZA_SLICE_FAMILY = Family.all(PizzaSliceComponent.class, BoundsComponent.class).get();

    private SoundSystem soundSystem;

    public CollisionSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        soundSystem = engine.getSystem(SoundSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;

        ImmutableArray<Entity> cars = getEngine().getEntitiesFor(CAR_FAMILY);
        ImmutableArray<Entity> holes = getEngine().getEntitiesFor(HOLE_FAMILY);
        ImmutableArray<Entity> houses = getEngine().getEntitiesFor(HOUSE_FAMILY);
        ImmutableArray<Entity> stars = getEngine().getEntitiesFor(STAR_FAMILY);
        ImmutableArray<Entity> fuels = getEngine().getEntitiesFor(FUEL_FAMILY);
        ImmutableArray<Entity> pizzaBoxes = getEngine().getEntitiesFor(PIZZA_BOX_FAMILY);
        ImmutableArray<Entity> pizzaSlices = getEngine().getEntitiesFor(PIZZA_SLICE_FAMILY);

        for (Entity carEntity : cars) {
            BoundsComponent carBounds = Mappers.BOUNDS.get(carEntity);

            // check collision with holes
            for (Entity holeEntity : holes) {
                HoleComponent hole = Mappers.HOLE.get(holeEntity);

                if (hole.hit) {
                    continue;
                }

                BoundsComponent holeBounds = Mappers.BOUNDS.get(holeEntity);

                if (Intersector.overlaps(carBounds.rectangle, holeBounds.rectangle)) {
//                    hole.hit = true;
                    GameManager.INSTANCE.damage();
                    soundSystem.pickBeeps();
                }
            }

            // check collision with pizza boxes
            for (Entity pizzaBoxEntity : pizzaBoxes) {
                PizzaBoxComponent pizzaBox = Mappers.PIZZA_BOX.get(pizzaBoxEntity);

                if (pizzaBox.hit) {
                    continue;
                }

                BoundsComponent pizzaBoxBounds = Mappers.BOUNDS.get(pizzaBoxEntity);

                if (Intersector.overlaps(carBounds.rectangle, pizzaBoxBounds.rectangle)) {
                    pizzaBox.hit = true;
                    GameManager.INSTANCE.incCollectedPizzas();
                    soundSystem.pickPackageDrop();
                    getEngine().removeEntity(pizzaBoxEntity);
                }
            }

            // check collision with fuels
            for (Entity fuelEntity : fuels) {
                FuelComponent fuel = Mappers.FUEL.get(fuelEntity);

                if (fuel.hit) {
                    continue;
                }

                BoundsComponent fuelBounds = Mappers.BOUNDS.get(fuelEntity);

                if (Intersector.overlaps(carBounds.rectangle, fuelBounds.rectangle)) {
                    fuel.hit = true;
                    GameManager.INSTANCE.incFuel();
                    soundSystem.pickGas();
                    getEngine().removeEntity(fuelEntity);
                }
            }

            // check collision with houses
            for (Entity houseEntity : houses) {
                HouseComponent house = Mappers.HOUSE.get(houseEntity);

                if (house.hit) {
                    continue;
                }

                BoundsComponent houseBounds = Mappers.BOUNDS.get(houseEntity);

                if (Intersector.overlaps(carBounds.rectangle, houseBounds.rectangle)) {
                    house.hit = true;
                    GameManager.INSTANCE.damage();
                    soundSystem.pickBeeps();
                    getEngine().removeEntity(houseEntity);
                }
            }

            // check collision with stars
            for (Entity starsEntity : stars) {
                StarComponent star = Mappers.STAR.get(starsEntity);

                if (star.hit) {
                    continue;
                }

                BoundsComponent starBounds = Mappers.BOUNDS.get(starsEntity);

                if (Intersector.overlaps(carBounds.rectangle, starBounds.rectangle)) {
                    star.hit = true;
                    GameManager.INSTANCE.damage();
                    soundSystem.pickStar();
                    getEngine().removeEntity(starsEntity);
                }
            }

            // check collision with pizza slices
            for (Entity pizzaSEntity : pizzaSlices) {
                for (Entity houseEntity : houses) {
                    PizzaSliceComponent pizzaSlice = Mappers.PIZZA_SLICE.get(pizzaSEntity);

                    if (pizzaSlice.hit) {
                        continue;
                    }

                    BoundsComponent pizzaSliceBounds = Mappers.BOUNDS.get(pizzaSEntity);
                    BoundsComponent houseBounds = Mappers.BOUNDS.get(houseEntity);

                    if (Intersector.overlaps(houseBounds.rectangle, pizzaSliceBounds.rectangle)) {
                        pizzaSlice.hit = true;
                        GameManager.INSTANCE.incCollectedPizzas();
                        soundSystem.pickYum();
                        getEngine().removeEntity(pizzaSEntity);
                        getEngine().removeEntity(houseEntity);
                    }
                }
            }
        }
    }
}
