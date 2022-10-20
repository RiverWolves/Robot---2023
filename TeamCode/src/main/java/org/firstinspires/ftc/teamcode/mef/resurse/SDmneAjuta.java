package org.firstinspires.ftc.teamcode.mef.resurse;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class SDmneAjuta {

    private static int FAZA = 0;
    private static ElapsedTime et;
    private static float LIMITARE_SUS_LIFT = -8000;
    private static float LIMITARE_JOS_LIFT = 0;
    private static DcMotor lift = null;

    public static void init() {
        SRoti.setVelXYR(0, 0, 0);

    }

    public static void loop(OpMode opMode) {


        int pozitie_lift = SHardware.lift.getCurrentPosition();

        lift = SHardware.lift;


        opMode.telemetry.addData("FAZA", FAZA);
        opMode.telemetry.addData("lift", lift.getCurrentPosition());


        if (et == null) {
            et = new ElapsedTime();
            et.reset();
        }
        if(FAZA == 0){
            SRoti.setVelXYR(0,0,0);
            if(pozitie_lift >= -8000) {
                lift.setPower(0.8);
            }

            if(pozitie_lift <= -7900){
                lift.setPower(0);
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 1){
            SRoti.setVelY(-0.3f);
            if(et.seconds() > 1){
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 2){
            SRoti.setVelXYR(0,0,0);
            SHardware.cleste1.setPosition(0.7);
            SHardware.cleste2.setPosition(0.3);
            if(et.seconds() > 0.6) {
                FAZA++;
                et.reset();
            }

        }
        if(FAZA == 3){
            SHardware.cleste1.setPosition(1);
            SHardware.cleste2.setPosition(0);
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
            SRoti.setVelXY(0.5f, 0.1f);
            if(et.seconds() > 2){
                SRoti.setVelXYR(0, 0, 0);
                FAZA++;
                et.reset();
            }
        }

        if(FAZA == 6){

            if(pozitie_lift <= 0) {
                lift.setPower(-0.8);
            }

            if(pozitie_lift >= -50){
                lift.setPower(0);
                FAZA++;
                et.reset();
            }
        }



            if (FAZA == 7) {
                SRoti.setVelXYR(0, 0, 0);
                lift.setPower(0);


            }

        }

    public static void stop(){
        et = null;
        FAZA = 0;
        SRoti.setVelXYR(0,0,0);
    }

    }

