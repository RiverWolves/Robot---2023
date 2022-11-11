package org.firstinspires.ftc.teamcode.stef.resurse;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.SRoti;


public class Autonomie_Albastru {

    private static int FAZA = 0;
    private static ElapsedTime et;
    private static float LIMITARE_SUS_LIFT = 3800;
    private static float LIMITARE_JOS_LIFT = 50;
    private static DcMotor lift1, lift2 = null;

    public static void init() {
        SRoti.setVelXYR(0, 0, 0);

    }

    public static void loop(OpMode opMode) {


        int pozitie_lift = SHardware.lift1.getCurrentPosition();

        lift1 = SHardware.lift1;
        lift2 = SHardware.lift2;


        opMode.telemetry.addData("FAZA", FAZA);
        opMode.telemetry.addData("lift", lift1.getCurrentPosition());


        if (et == null) {
            et = new ElapsedTime();
            et.reset();
        }
        if(FAZA == 0){
            SRoti.setVelXYR(0,0,0);
            SHardware.cleste1.setPosition(0);
            SHardware.cleste2.setPosition(1);
            if(pozitie_lift <= LIMITARE_SUS_LIFT) {
                lift1.setPower(0.8);
                lift2.setPower(0.8);
            }

            if(pozitie_lift >= LIMITARE_SUS_LIFT-2000){
                lift1.setPower(0);
                lift2.setPower(0);
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 1){
            SRoti.setVelY(-0.25f);
            if(et.seconds() > 0.6){
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 2){
            SRoti.setVelXYR(0,0,0);
            SHardware.cleste1.setPosition(0.15);
            SHardware.cleste2.setPosition(0.85);
            if(et.seconds() > 0.6) {
                FAZA++;
                et.reset();
            }

        }
        if(FAZA == 3){
            SHardware.cleste1.setPosition(0);
            SHardware.cleste2.setPosition(1);
            if(et.seconds() > 0.6){
                FAZA++;
                et.reset();
            }

        }
        if(FAZA == 4){
            SRoti.setVelY(0.2f);
            if(et.seconds() > 0.5){
                FAZA++;
                et.reset();
            }
        }
        if(FAZA == 5){
            SRoti.setVelXY(0.4f, 0.1f);
            if(et.seconds() > 1.6){
                SRoti.setVelXYR(0, 0, 0);
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 6){

            if(pozitie_lift >= 0) {
                lift1.setPower(-0.8);
                lift2.setPower(-0.8);
            }

            if(pozitie_lift <= LIMITARE_JOS_LIFT){
                lift1.setPower(0);
                lift2.setPower(0);
                FAZA++;
                et.reset();
            }
        }



        if (FAZA == 7) {
            SRoti.setVelXYR(0, 0, 0);
            lift1.setPower(0);
            lift2.setPower(0);



        }

    }

    public static void stop(){
        et = null;
        FAZA = 0;
        SRoti.setVelXYR(0,0,0);
    }

}

