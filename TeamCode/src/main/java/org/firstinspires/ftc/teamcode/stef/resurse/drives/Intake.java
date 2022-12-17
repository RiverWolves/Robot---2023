package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Intake {

    private static Servo intake1, intake2 = null;
    private static boolean inchis = false;

    public static void loop(OpMode opMode){

        if (!SHardware.initializat) return;
        if (intake1 == null) {
            intake1 = SHardware.intake1;
        }
        if (intake2 == null) {
            intake2 = SHardware.intake2;
        }
        boolean einchis = inchis;

        if (einchis){
            intake1.setPosition(1);
            intake2.setPosition(0);
        }else{
            intake1.setPosition(0.7);
            intake2.setPosition(0.3);
        }

        opMode.telemetry.addData("intake inchis: ", einchis);
    }

    public static void setVal(boolean stare){
        inchis = stare;
    }
}
