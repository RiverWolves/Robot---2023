package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Intake {

    private static Servo intake = null;
    private static boolean inchis = false;

    public static void loop(OpMode opMode){

        if (!SHardware.initializat) return;
        if (intake == null) {
            intake = SHardware.intake;
        }
        boolean einchis = inchis;

        if (einchis){
            intake.setPosition(1);
        }else{
            intake.setPosition(0);
        }

        opMode.telemetry.addData("intake inchis: ", einchis);
    }

    public static void setVal(boolean stare){
        inchis = stare;
    }
}
