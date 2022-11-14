package org.firstinspires.ftc.teamcode.stef.resurse.drives;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Lift {

    private static float LIMITARE_SUS_LIFT = 3800,
                         LIMITARE_JOS_LIFT = 50;

    private static float y = 0,
                         putere = 1;

    private static DcMotor lift1 = null,
                           lift2 = null;

    public static void loop(OpMode opMode){

        if (!SHardware.initializat) return;
        if (lift1 == null) {
            lift1 = SHardware.lift1;
        }

        if (lift2 == null) {
            lift2 = SHardware.lift2;
        }

        float input = y;
        float pozitie_lift = lift1.getCurrentPosition();

        if (input > 0.5f && pozitie_lift < LIMITARE_SUS_LIFT) {
            lift1.setPower(putere);
            lift2.setPower(putere);
        } else if (input < -0.5f && pozitie_lift > LIMITARE_JOS_LIFT) {
            lift1.setPower(-putere);
            lift2.setPower(-putere);
        } else {
            lift1.setPower(0);
            lift2.setPower(0);
        }

        if (input > 0.5f && pozitie_lift >= LIMITARE_SUS_LIFT){
            Ceva.rumble();
        }else if (input < -0.5f && pozitie_lift <= LIMITARE_JOS_LIFT){
            Ceva.rumble();
        }

        opMode.telemetry.addData("input lift: ", input);
        opMode.telemetry.addData("pozitie lift: ", pozitie_lift);

    }

    public static void setVal(float stick, float pow){
        putere = pow;
        y = stick;
    }

    public static void setNivel(float nivel){
        putere = 0.8f;
        int sens = 0;

        if (nivel == 0){
            LIMITARE_SUS_LIFT = 100;
        }else if (nivel == 1){
            LIMITARE_SUS_LIFT = 1000;
        }else if (nivel == 2){
            LIMITARE_SUS_LIFT = 2000;
        }else{
            LIMITARE_SUS_LIFT = 3800;
        }

        float pozitie_lift = lift1.getCurrentPosition();

        if (pozitie_lift > LIMITARE_SUS_LIFT){
            sens = -1;
        }else{
            sens = 1;
        }

        y = sens;
    }

}
