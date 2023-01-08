package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Brat {

    private static DcMotor brat = null;
    private static NormalizedColorSensor color = null;

    private static boolean in;

    private static final float gain = 2f;
    private static float[] hsvValues = {0, 0, 0};
    private static final int purple = Color.parseColor("#9933ff");
    private static final int grey = Color.parseColor("#808080");

    private static View relativeLayout = null;

    private static Ceva ceva = null;



    public static void init(){
        if(!SHardware.initializat) return;

        color = SHardware.colorSensor;

        brat = SHardware.brat;
        brat.setPower(0);

        if (color instanceof SwitchableLight) {
            ((SwitchableLight)color).enableLight(true);
        }
        color.setGain(gain);
//        int relativeLayoutId = opMode.hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", opMode.hardwareMap.appContext.getPackageName());
//        relativeLayout = ((Activity) opMode.hardwareMap.appContext).findViewById(relativeLayoutId);

        ceva = new Ceva();
    }

    public static void loop(){

        float rosu = color.getNormalizedColors().red;
        float albastru = color.getNormalizedColors().blue;
        float gri = color.getNormalizedColors().green;

        Intake.setRotire(!in);

        if (in && albastru < gri) {
            brat.setPower(0.4);
        }
        else if (!in && rosu < gri) {
            brat.setPower(-0.4);
        }
        else {
            brat.setPower(0);
        }

    }

    public static void input(boolean buton) {
        in = ceva.buttonToSwich(buton);
    }

    private static NormalizedRGBA color(){

        NormalizedRGBA colors = color.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);

//        relativeLayout.post(new Runnable() {
//            public void run() {
//                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
//            }
//        });

        return colors;
    }

}
