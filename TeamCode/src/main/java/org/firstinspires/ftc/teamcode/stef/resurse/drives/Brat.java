package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Brat {


    private static Servo brat = null,
            rotire = null;
    private static boolean pozitie = false;

    public static  void init() {
        if (!SHardware.initializat) return;
        if (brat == null) {
            brat = SHardware.brat;
        }
        if (rotire == null) {
            rotire = SHardware.rotire;
        }
    }
    public static void SetVal(boolean poz){
        pozitie = poz;
    }

    public static void loop(OpMode opdmode){

        if(pozitie){
            brat.setPosition(Ceva.unghiServo(180));
            rotire.setPosition(Ceva.unghiServo(180));
        }
        if(!pozitie){
            brat.setPosition(Ceva.unghiServo(0));
            rotire.setPosition(Ceva.unghiServo(0));
        }
        opdmode.telemetry.addData("Brat rotit: ", pozitie);
    }

}
