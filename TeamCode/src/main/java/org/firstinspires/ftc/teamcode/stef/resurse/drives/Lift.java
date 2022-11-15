package org.firstinspires.ftc.teamcode.stef.resurse.drives;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;

public class Lift {

    private static final float LIMITARE_SUS_LIFT = 3800,
                         NIVEL_0 = 100,
                         NIVEL_1 = 1000,
                         NIVEL_2 = 2000,
                         NIVEL_3 = 3800;

    private static final float LIMITARE_JOS_LIFT = 50;

    private static float y = 0;
    private static final float putere = 1;

    private static DcMotor lift1 = null,
                           lift2 = null;
    public static boolean enivel = false,
                          nivel0 = false,
                          nivel1 = false,
                          nivel2 = false,
                          nivel3 = false;

    public static int nivel;

    public static void loop(OpMode opMode){

        if (!SHardware.initializat) return;
        if (lift1 == null) {
            lift1 = SHardware.lift1;
        }else if (lift2 == null) {
            lift2 = SHardware.lift2;
        }

        float input = y;
        float pozitie_lift = lift1.getCurrentPosition();
        int sens;

        if (nivel0){
            nivel = 0;
        }else if (nivel1){
            nivel = 1;
        }else if (nivel2){
            nivel = 2;
        }else if (nivel3){
            nivel = 3;
        }else{
            nivel = 4;
        }


        if (nivel == 0){
            if (pozitie_lift <= NIVEL_0){
                sens = 1;
            }else {
                sens = -1;
            }

            if (pozitie_lift <= NIVEL_0+200 && pozitie_lift >= NIVEL_0-200){
                enivel = true;
                lift1.setPower(0);
                lift2.setPower(0);
            }else{
                lift1.setPower(sens);
                lift2.setPower(sens);
                enivel = false;
            }
        }else if (nivel == 1){
            if (pozitie_lift <= NIVEL_1){
                sens = 1;
            }else {
                sens = -1;
            }

            if (pozitie_lift <= NIVEL_1+200 && pozitie_lift >= NIVEL_1-200){
                enivel = true;
                lift1.setPower(0);
                lift2.setPower(0);
            }else{
                lift1.setPower(sens);
                lift2.setPower(sens);
                enivel = false;
            }
        }else if (nivel == 2){
            if (pozitie_lift <= NIVEL_2){
                sens = 1;
            }else {
                sens = -1;
            }

            if (pozitie_lift <= NIVEL_2+200 && pozitie_lift >= NIVEL_2-200){
                enivel = true;
                lift1.setPower(0);
                lift2.setPower(0);
            }else{
                lift1.setPower(sens);
                lift2.setPower(sens);
                enivel = false;
            }
        }else if (nivel == 3){
            if (pozitie_lift <= NIVEL_3){
                sens = 1;
            }else {
                sens = -1;
            }

            if (pozitie_lift <= NIVEL_3+200 && pozitie_lift >= NIVEL_3-200){
                enivel = true;
                lift1.setPower(0);
                lift2.setPower(0);
            }else{
                lift1.setPower(sens);
                lift2.setPower(sens);
                enivel = false;
            }
        }

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
            Ceva.rumble(opMode.gamepad2);
        }else if (input < -0.5f && pozitie_lift <= LIMITARE_JOS_LIFT){
            Ceva.rumble(opMode.gamepad2);
        }

        opMode.telemetry.addData("input lift: ", input);
        opMode.telemetry.addData("pozitie lift: ", pozitie_lift);
        opMode.telemetry.addData("Nivel lift: ", nivel);
        opMode.telemetry.addData("E nivel: ", enivel);

    }

    public static void setVal(float stick, boolean bool0, boolean bool1, boolean bool2, boolean bool3){

        nivel0 = Ceva.longPress(bool0);
        nivel1 = Ceva.longPress(bool1);
        nivel2 = Ceva.longPress(bool2);
        nivel3 = Ceva.longPress(bool3);
        y = stick;

    }

   /* public static void setNivel(float nivel){
        if (!SHardware.initializat) return;
        if (lift1 == null) {
            lift1 = SHardware.lift1;
        }

        putere = 0.4f;

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
            y = -1;
        }else{
            y = 1;
        }
        if (pozitie_lift <= LIMITARE_SUS_LIFT+200 && pozitie_lift >= LIMITARE_SUS_LIFT-200){
            enivel = true;
            y = 0;
        }else{
            enivel = false;
        }
    }

    public static void setNivelTeleop(float nivel, boolean conditie){
        if (!SHardware.initializat) return;
        if (lift1 == null) {
            lift1 = SHardware.lift1;
        }
        putere = 0.4f;

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

        if (conditie) {
            if (pozitie_lift > LIMITARE_SUS_LIFT) {
                y = -1;
            } else {
                y = 1;
            }
        }

        if (pozitie_lift <= LIMITARE_SUS_LIFT+200 && pozitie_lift >= LIMITARE_SUS_LIFT-200){
            enivel = true;
            y = 0;
        }else{
            enivel = false;
        }

    }


    */
}
