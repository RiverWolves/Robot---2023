package org.firstinspires.ftc.teamcode.stef.resurse.drives;

import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Brat {

    private static DcMotor brat = null;

    private static boolean in, previn;

    private static Ceva ceva = null;

    private static ElapsedTime et = null, init = null;

    public static int faza = 0;

    private static int target = 0;

    public static void init(){
        if(!SHardware.initializat) return;

        in = false;
        previn = false;
        ceva = new Ceva();

        brat = SHardware.brat;
        brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        brat.setPower(0);

        if (et == null) {
            et = new ElapsedTime();
            et.reset();
        }

        if (init == null) {
            init = new ElapsedTime();
            init.reset();
        }

    }

    public static void loop(OpMode opMode){
        if (in) {
            brat_fata();
        }
        else {
            brat_spate();
        }

        brat.setTargetPosition(target);
        brat.setPower(0.4);
        brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
/*


       if(in != previn){
           et.reset();
           previn = !previn;
       }

       if (in && et.seconds() < 0.8){
           brat.setPower(0.4);
       }else if(!in && et.seconds() < 0.8){
           brat.setPower(-0.4);
       }else{
           brat.setPower(0);
       }

       opMode.telemetry.addData("putere brat: ", brat.getPower());

       if(et.seconds() > 0.6) {
           Intake.setRotire(!in);
       }

    }

    public static void inceput(){
        if (faza == 0) {
            init.reset();
            if (init.seconds() < 0.4){
                brat.setPower(0.4);
            }else {
                brat.setPower(0);
            }
            faza++;
        }*/
    }

    public static void input(boolean buton) {
        in = buton;
    }

    public static void brat_fata() {
        target = 500;
    }

    public static void brat_spate() {
        target = -500;
    }
}
