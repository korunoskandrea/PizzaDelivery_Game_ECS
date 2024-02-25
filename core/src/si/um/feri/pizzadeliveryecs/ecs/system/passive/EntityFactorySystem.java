package si.um.feri.pizzadeliveryecs.ecs.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;

import si.um.feri.pizzadeliveryecs.assets.AssetDescriptors;
import si.um.feri.pizzadeliveryecs.assets.RegionNames;
import si.um.feri.pizzadeliveryecs.config.GameConfig;
import si.um.feri.pizzadeliveryecs.ecs.component.BoundsComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.CarComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.CleanUpComponent;
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
import si.um.feri.pizzadeliveryecs.ecs.component.WorldWrapComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.ZOrderComponent;

public class EntityFactorySystem extends EntitySystem {
    private static final int HOLE_Z_ORDER = 8;
    private static final int FUEL_Z_ORDER = 3;
    private static final int CAR_Z_ORDER = 2;
    private static final int PIZZA_BOX_Z_ORDER = 4;
    private static final int PIZZA_SLICE_Z_ORDER = 5;
    private static final int STAR_Z_ORDER = 6;
    private static final int HOUSE_Z_ORDER = 7;
    private static final int BACKGROUND_Z_ORDER = 1;

    private final AssetManager assetManager;

    private PooledEngine engine;
    private TextureAtlas gamePlayAtlas;

    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        setProcessing(false);   // passive system
        init();
    }

    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createCar() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = GameConfig.WIDTH / 2f - GameConfig.CAR_WIDTH / 2;
        position.y = 20;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.CAR_WIDTH;
        dimension.height = GameConfig.CAR_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        CarComponent car = engine.createComponent(CarComponent.class);

        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        TextureComponent carTexture = engine.createComponent(TextureComponent.class);
        carTexture.region = gamePlayAtlas.findRegion(RegionNames.CAR);
        carTexture.particleEffect = assetManager.get(AssetDescriptors.LIGHT);
        carTexture.particleEffect.setPosition(position.x, position.y);
        carTexture.particleEffect.start();

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = CAR_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(car);
        entity.add(worldWrap);
        entity.add(carTexture);
        //entity.add(texture2);
        entity.add(zOrder);
        engine.addEntity(entity);
    }


    public void createHole() {
        PositionComponent position = engine.createComponent(PositionComponent.class);

        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.HOLE_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-1f, 1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        float randFactor = MathUtils.random(1f, 4f);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.HOLE_WIDTH * randFactor;
        dimension.height = GameConfig.HOLE_HEIGHT * randFactor;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        HoleComponent hole = engine.createComponent(HoleComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.HOLE);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = HOLE_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(hole);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createFuel() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.FUEL_SIZE);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-0.1f, 0.1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        FuelComponent fuel = engine.createComponent(FuelComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.FUEL_SIZE;
        dimension.height = GameConfig.FUEL_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.FUEL);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = FUEL_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(fuel);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createHouse() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.HOUSE_SIZE);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-0.1f, 0.1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        HouseComponent house = engine.createComponent(HouseComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.HOUSE_SIZE;
        dimension.height = GameConfig.HOUSE_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.HOUSE);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = HOUSE_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(house);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createPizzaBox() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.PIZZA_BOX_SIZE);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-0.1f, 0.1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        PizzaBoxComponent pizzaBox = engine.createComponent(PizzaBoxComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.PIZZA_BOX_SIZE;
        dimension.height = GameConfig.PIZZA_BOX_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.PIZZA);
        texture.particleEffect = assetManager.get(AssetDescriptors.SMOKE);
        texture.particleEffect.setPosition(position.x, position.y);
        texture.particleEffect.start();

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = PIZZA_BOX_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(pizzaBox);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createPizzaSlice() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.PIZZA_SLICE_SIZE);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-0.1f, 0.1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        PizzaSliceComponent pizzaSlice = engine.createComponent(PizzaSliceComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.PIZZA_SLICE_SIZE;
        dimension.height = GameConfig.PIZZA_SLICE_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.SLICE_OF_PIZZA);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = PIZZA_SLICE_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(pizzaSlice);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createStar() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.STAR_SIZE);
        position.y = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(-0.1f, 0.1f);
        movement.ySpeed = -GameConfig.HOLE_SPEED_X_MIN * MathUtils.random(1f, 2f);
        //movement.rSpeed = MathUtils.random(-1f, 1f);

        StarComponent star = engine.createComponent(StarComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.STAR_SIZE;
        dimension.height = GameConfig.STAR_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.STAR);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = STAR_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(star);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createBackground() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = 0;
        position.y = 0;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WIDTH;
        dimension.height = GameConfig.HEIGHT;

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BACKGROUND_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);



        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }
}
