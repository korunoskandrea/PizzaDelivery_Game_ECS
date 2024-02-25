package si.um.feri.pizzadeliveryecs.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {

    public TextureRegion region;
    public ParticleEffect particleEffect;

    @Override
    public void reset() {
        region = null;
        particleEffect = null;
    }
}
