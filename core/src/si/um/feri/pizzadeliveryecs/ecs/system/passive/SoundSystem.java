package si.um.feri.pizzadeliveryecs.ecs.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import si.um.feri.pizzadeliveryecs.assets.AssetDescriptors;
import si.um.feri.pizzadeliveryecs.assets.AssetPaths;

public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;

    private Sound gasFilling;
    private Sound negativeBeeps;
    private Sound packageDrop;
    private Sound screechTire;
    private Sound star;
    private Sound yum;

    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        setProcessing(false); // passive system
        init();
    }

    private void init() {
        gasFilling = assetManager.get(AssetDescriptors.SOUND_GAS_FILLING_ASSET_DESCRIPTOR);
        negativeBeeps = assetManager.get(AssetDescriptors.SOUND_NEGATIVE_BEEPS_ASSET_DESCRIPTOR);
        packageDrop = assetManager.get(AssetDescriptors.SOUND_PACKAGE_DROP_ASSET_DESCRIPTOR);
        screechTire = assetManager.get(AssetDescriptors.SOUND_SCREECH_TIRE_ASSET_DESCRIPTOR);
        star = assetManager.get(AssetDescriptors.SOUND_STAR_ASSET_DESCRIPTOR);
        yum = assetManager.get(AssetDescriptors.SOUND_YUM_ASSET_DESCRIPTOR);

    }

    public void pickGas() {
        gasFilling.play();
    }
    public void pickBeeps() {
        negativeBeeps.play();
    }
    public void pickPackageDrop() {
        packageDrop.play();
    }
    public void pickScreechTire() {
        screechTire.play();
    }
    public void pickStar() {
        star.play();
    }
    public void pickYum() {
        yum.play();
    }
}
