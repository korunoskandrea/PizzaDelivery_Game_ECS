package si.um.feri.pizzadeliveryecs;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import si.um.feri.pizzadeliveryecs.PizzaDeliveryGameECS;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("pizza-delivery-game-ecs");
        new Lwjgl3Application(new PizzaDeliveryGameECS(), config);
    }
}