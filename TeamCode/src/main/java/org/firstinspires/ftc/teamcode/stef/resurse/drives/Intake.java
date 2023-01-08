package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Intake {

    private static Servo intake, rotire = null;
    public static boolean inchis;
    public static boolean rotit;

    public static void init(){
        if (!SHardware.initializat) return;
//
        inchis = false;
        rotit = false;

        intake = SHardware.intake;
        rotire = SHardware.rotire;
        intake.setPosition(1);
        rotire.setPosition(1);

    }

    public static void loop(OpMode opMode){


        boolean einchis = inchis;
        boolean erotit = rotit;

        if (einchis) {
//            intake.setPosition(Ceva.servoToDegrees(300));
            intake.setPosition(0.5);
        }else {

//            intake.setPosition(Ceva.servoToDegrees(250));
            intake.setPosition(1);
        }

        if (erotit) {
//            rotire.setPosition(Ceva.servoToDegrees(180));
            rotire.setPosition(0.32);
        }else {
//            rotire.setPosition(Ceva.servoToDegrees(0));
            rotire.setPosition(1);
        }


        opMode.telemetry.addData("intake", einchis);
        opMode.telemetry.addData("rotire", erotit);

    }

    public static void setInchis(boolean stare1){
        inchis = stare1;
    }
    public static void setRotire(boolean stare2){
        rotit = stare2;
    }
}
