package org.firstinspires.ftc.teamcode.mef.resurse;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Ceva {

    private static ElapsedTime et, lastDebounceTime;
    private static boolean buttonState = false;
    private static boolean debounced = false;

    public static boolean debouce(boolean buton) {

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
}
