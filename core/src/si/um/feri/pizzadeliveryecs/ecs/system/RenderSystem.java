package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import si.um.feri.pizzadeliveryecs.common.Mappers;
import si.um.feri.pizzadeliveryecs.ecs.component.DimensionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.PositionComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.TextureComponent;
import si.um.feri.pizzadeliveryecs.ecs.component.ZOrderComparator;
import si.um.feri.pizzadeliveryecs.ecs.component.ZOrderComponent;


public class RenderSystem extends SortedIteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class,
            ZOrderComponent.class
    ).get();

    private final SpriteBatch batch;
    private final Viewport viewport;

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY, ZOrderComparator.INSTANCE);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {   // override to avoid calling batch.begin/end for each entity
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        super.update(deltaTime);    // calls processEntity method, which is wrapped with begin/end

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        TextureComponent texture = Mappers.TEXTURE.get(entity);

        if (texture.region != null){
            batch.draw(texture.region,
                    position.x, position.y,
                    dimension.width / 2, dimension.height / 2,
                    dimension.width, dimension.height,
                    1, 1,
                    position.r
            );
        }

        if (texture.particleEffect != null) {
            texture.particleEffect.update(deltaTime);
            texture.particleEffect.setPosition(position.x, position.y);
            texture.particleEffect.draw(batch);
        }
    }
}