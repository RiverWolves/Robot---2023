package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Ceva {

    private static ElapsedTime et, lastDebounceTime;
    private static boolean buttonState = false;
    private static boolean debounced = false;

    private static final float constanta = 0.3f;

    public static boolean buttonToSwich(boolean buton) {

        if (et == null) {
            et = new ElapsedTime();
            et.reset();
        }
        if (lastDebounceTime == null) {
            lastDebounceTime = new ElapsedTime();
            lastDebounceTime.reset();
        }

        if (buton) {
            // reset the debouncing timer
            lastDebounceTime.reset();
        }
        if ((et.milliseconds() - lastDebounceTime.milliseconds()) > 50) {
            // whatever the reading is at, it's been there for longer than the debounce
            // delay, so take it as the actual current state:

            // if the button state has changed:
            if (buton != buttonState) {
                buttonState = buton;

                // only toggle the LED if the new button state is HIGH
                if (buttonState) {
                    debounced = !debounced;
                }
                et.reset();
            }
        }

        return debounced;
    }



    public static void rumble(Gamepad gamepad ){
        Gamepad.RumbleEffect rum = new Gamepad.RumbleEffect.Builder()
                .addStep(1, 0.2, 200)
                .addStep(0, 0, 100)
                .addStep(0.2, 1, 200)
                .addStep(0, 0, 600)
                .build();

        gamepad.runRumbleEffect(rum);
    }

    public static float fineControl(boolean conditie, float in) {
        if(conditie){
            return in * constanta;
        }
        return in;
    }
}
