package org.firstinspires.ftc.teamcode.stef.resurse.drives;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Lift {

    private static final int LIMITARE_SUS_LIFT = 3800,
                         NIVEL_0 = 100,
                         NIVEL_1 = 1000,
                         NIVEL_2 = 2000,
                         NIVEL_3 = 3800;

    private static final float LIMITARE_JOS_LIFT = 50;

    private static float y = 0;
    private static float putere = 1;
    private static float pow_nivel = 0.4f;

    private static DcMotor lift1 = null,
                           lift2 = null;

    public static boolean enivel = false,
            nivel0 = false,
            nivel1 = false,
            nivel2 = false,
            nivel3 = false;

    public static int nivel = 0,
                      target = 0;

public static  void init(){
    if (!SHardware.initializat) return;
    if (lift1 == null) {
        lift1 = SHardware.lift1;
    }
    if (lift2 == null) {
        lift2 = SHardware.lift2;
    }
    lift1.setTargetPosition(0);
    lift2.setTargetPosition(0);
    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
}

    public static void loop(OpMode opMode){

        float input = y;
        float pozitie_lift = lift1.getCurrentPosition();

        if (input > 0.3f && pozitie_lift < LIMITARE_SUS_LIFT) {
            putere = 1;
        } else if (input < -0.3f && pozitie_lift > LIMITARE_JOS_LIFT) {
           putere = -1;
        } else {
            putere = 0;
        }

        putere = Ceva.fineControl(opMode.gamepad2.left_bumper, putere);

        if (input != 0) {
            lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            lift1.setPower(putere);
            lift2.setPower(putere);
        }

        if (input > 0.3f && pozitie_lift >= LIMITARE_SUS_LIFT){
            Ceva.rumble(opMode.gamepad2);
        }else if (input < -0.3f && pozitie_lift <= LIMITARE_JOS_LIFT){
            Ceva.rumble(opMode.gamepad2);
        }


        opMode.telemetry.addData("input lift: ", input);
        opMode.telemetry.addData("pozitie lift: ", pozitie_lift);

    }

    public static void nivelLoop(OpMode opMode){

        int poz_lift = lift1.getCurrentPosition();

        if (nivel0){
            nivel = 0;
        }else if (nivel1){
            nivel = 1;
        }else if (nivel2){
            nivel = 2;
        }else if (nivel3){
            nivel = 3;
        }


        if (y == 0) {
            if (nivel0) {
                target = NIVEL_0;
                pow_nivel = 0.4f;

            } else if (nivel1) {
                target = NIVEL_1;
                pow_nivel = 0.4f;

            } else if (nivel2) {
                target = NIVEL_2;
                pow_nivel = 0.4f;

            } else if (nivel3) {
                target = NIVEL_3;
                pow_nivel = 0.4f;

            }
        }else{
            target = 0;
            pow_nivel = 0;
        }

        lift1.setTargetPosition(target);

        if (y == 0) {
            lift1.setPower(pow_nivel);
            lift2.setPower(pow_nivel);

            lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        if (poz_lift <= target+10 && poz_lift >= target-10){
            pow_nivel = 0;
            enivel = true;
        } else {
            enivel = false;
        }

        if (poz_lift <= target+10 && poz_lift >= target-10 && lift1.getPower() != 0){
            Ceva.rumble(opMode.gamepad2);
        }


        opMode.telemetry.addData("putere: ", lift1.getPower());
        opMode.telemetry.addData("nivel: ", nivel);
        opMode.telemetry.addData("Este nivel: ", enivel);



    }

    public static void setVal(float stick, boolean bool0, boolean bool1, boolean bool2, boolean bool3){

        nivel0 = bool0;
        nivel1 = bool1;
        nivel2 = bool2;
        nivel3 = bool3;
        y = stick;

    }

}
