package si.um.feri.pizzadeliveryecs.assets;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> UI_FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> GAME_PLAY =
            new AssetDescriptor<TextureAtlas>(AssetPaths.GAMEPLAY, TextureAtlas.class);
    public static final AssetDescriptor<ParticleEffect> LIGHT =
            new AssetDescriptor<ParticleEffect>(AssetPaths.LIGHT, ParticleEffect.class);
    public static final AssetDescriptor<ParticleEffect> SMOKE =
            new AssetDescriptor<ParticleEffect>(AssetPaths.SMOKE, ParticleEffect.class);
    public static final AssetDescriptor<Sound> SOUND_GAS_FILLING_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_GAS_FILLING_ASSET_DESCRIPTOR, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_NEGATIVE_BEEPS_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_NEGATIVE_BEEPS_ASSET_DESCRIPTOR, Sound.class);
    public static final AssetDescriptor<Sound> SOUND_PACKAGE_DROP_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_PACKAGE_DROP_ASSET_DESCRIPTOR, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_SCREECH_TIRE_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_SCREECH_TIRE_ASSET_DESCRIPTOR, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_STAR_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_STAR_ASSET_DESCRIPTOR, Sound.class);

    public static final AssetDescriptor<Sound> SOUND_YUM_ASSET_DESCRIPTOR =
            new AssetDescriptor<Sound>(AssetPaths.SOUND_YUM_ASSET_DESCRIPTOR, Sound.class);


    private AssetDescriptors() {
    }
}