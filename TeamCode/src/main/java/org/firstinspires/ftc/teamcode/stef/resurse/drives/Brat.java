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

    private static View relativeLayout = null;

    private static Ceva ceva = null;



    public static void init(){
        if(!SHardware.initializat) return;

//

        brat = SHardware.brat;
        brat.setPower(0);

        brat.setTargetPosition(0);
        brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        ceva = new Ceva();
    }

    public static void loop(){

       if(in){
           brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

           brat.setTargetPosition(50);

       }
       else if(!in){
           brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

           brat.setTargetPosition(-50);
       }

        Intake.setRotire(!in);


    }

    public static void input(boolean buton) {
        in = ceva.buttonToSwich(buton);
    }



}
