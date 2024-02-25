package si.um.feri.pizzadeliveryecs.ecs.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import si.um.feri.pizzadeliveryecs.common.GameManager;


public class HudRenderSystem extends EntitySystem {

    private static final float PADDING = 20.0f;

    private final SpriteBatch batch;
    private final Viewport hudViewport;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();

    public HudRenderSystem(SpriteBatch batch, Viewport hudViewport, BitmapFont font) {
        this.batch = batch;
        this.hudViewport = hudViewport;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        font.setColor(Color.WHITE);

        // delivered
        String deliveredString = "PIZZA: " + GameManager.INSTANCE.getCollectedPizzas();
        layout.setText(font, deliveredString);
        float y = hudViewport.getWorldHeight() - PADDING;
        font.draw(batch, layout, PADDING, y);

        // fuel
        String fuelString = "FUEL: " + GameManager.INSTANCE.getFuel();
        layout.setText(font, fuelString);
        float fuelX = (hudViewport.getWorldWidth() + layout.width) / 2 - layout.width;
        font.draw(batch, layout, fuelX, y);

        if (GameManager.INSTANCE.isGameOver()) {
            font.setColor(Color.RED);
            layout.setText(font, "THE END");
            float endX = (hudViewport.getWorldWidth() + layout.width) / 2 - layout.width;
            float endY = (hudViewport.getWorldHeight() + layout.height) / 2 - layout.height;
            font.draw(batch, layout, endX, endY);
        }

        batch.end();
    }
}
