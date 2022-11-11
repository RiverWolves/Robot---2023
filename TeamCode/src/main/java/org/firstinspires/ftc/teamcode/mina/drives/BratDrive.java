package org.firstinspires.ftc.teamcode.mina.drives;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mina.RWConfig;

public class BratDrive {

    public static int LIMITA_LIFT_SUS = 3800;
    public static int LIMITA_LIFT_JOS = 50;

    private static DcMotor lift1 = RWConfig.lift;
    private static DcMotor lift2 = RWConfig.lift2;
    private static int pozlift = RWConfig.lift.getTargetPosition();

    public static void init(){
        lift1.setPower(0);
        lift2.setPower(0);
    }

    public static void brat(float y){

        if(y > 0.5 && pozlift > LIMITA_LIFT_JOS){
            lift1.setPower(y);
            lift2.setPower(y);
        }if(y < -0.5 && pozlift < LIMITA_LIFT_SUS){
            lift1.setPower(y);
            lift2.setPower(y);
        }else{
            lift1.setPower(0);
            lift2.setPower(0);
        }
    }
}
