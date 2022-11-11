package org.firstinspires.ftc.teamcode.stef.resurse;


import static org.firstinspires.ftc.teamcode.stef.resurse.SHardware.intake1;
import static org.firstinspires.ftc.teamcode.stef.resurse.SHardware.intake2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;


public class SGamepad {
    private static float PUTERE_ROTI = 1;
    private static int INTAKE_MS = 1500;

    private static float LIMITARE_SUS_LIFT = 3800;
    private static float LIMITARE_JOS_LIFT = 50;

    private static float fine_control_roti = 1;
    private static float fine_control_lift = 1;

    private static boolean released = true;
    private static boolean intake_running = false;
    private static int intake_direction = 1;
    private static ElapsedTime timp;

    private static DcMotor lift1 = null;
    private static DcMotor lift2 = null;



    private static Gamepad.RumbleEffect
            ref = new Gamepad.RumbleEffect.Builder()
            .addStep(1, 0.2, 450)
            .addStep(0, 0, 100)
            .addStep(0.2, 1, 450)
            .addStep(0, 0, 600)
            .build();

    public static void init() {
        lift1 = null;
        lift2 = null;
        SHardware.lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void loop(OpMode opMode) {
        if (!SHardware.initializat) return;
        if (lift1 == null) {
            lift1 = SHardware.lift1;
        }

        if (lift2 == null) {
            lift2 = SHardware.lift2;
        }

        if (timp == null) {
            timp = new ElapsedTime();
            timp.reset();
        }

        Gamepad gamepad1 = opMode.gamepad1;
        Gamepad gamepad2 = opMode.gamepad2;

        //cod roti

        if(gamepad1.left_bumper) fine_control_roti = 0.5f;
        else fine_control_roti = 1f;

        SRoti.setVelXYR(fine_control_roti*gamepad1.left_stick_x, fine_control_roti*gamepad1.left_stick_y, fine_control_roti*gamepad1.right_stick_x);


        //codul pentru lift

        if(gamepad2.left_bumper) fine_control_lift = 0.5f;
        else fine_control_lift = 1f;

        int pozitie_lift = lift1.getCurrentPosition();
        float left_stick_y = -gamepad2.left_stick_y;

        double putere = 1;

        if (!gamepad2.isRumbling()) {
            if (left_stick_y > 0.5f && pozitie_lift >= LIMITARE_SUS_LIFT ) {
                gamepad2.runRumbleEffect(ref);
            }
            if (left_stick_y < -0.5f && pozitie_lift <= LIMITARE_JOS_LIFT ) {
                gamepad2.runRumbleEffect(ref);
            }
        }
        if (left_stick_y > 0.5f && pozitie_lift < LIMITARE_SUS_LIFT) {
            lift1.setPower(fine_control_lift*putere);
            lift2.setPower(fine_control_lift*putere);
        } else if (left_stick_y < -0.5f && pozitie_lift > LIMITARE_JOS_LIFT) {
            lift1.setPower(-fine_control_lift*putere);
            lift2.setPower(-fine_control_lift*putere);
        } else {
            lift1.setPower(0);
            lift2.setPower(0);
        }

        boolean buton = Ceva.buttonToSwich(gamepad2.right_bumper);

        //cod intake
        if (gamepad2.right_bumper && released) { //bumper apasat
            released = false;

            intake_running = true;
            if (intake_direction == 1) intake_direction = -1;
            else if (intake_direction == -1) intake_direction = 1;

            timp.reset();
        }

        if (!gamepad2.right_bumper && !released) {
            released = true;
        }

        if (timp.milliseconds() < INTAKE_MS) {
            if (intake_running) {
                intake1.setPower(intake_direction);
                intake2.setPower(-intake_direction);
            } else {
                intake1.setPower(0);
                intake2.setPower(0);
            }
        } else {
            intake1.setPower(0);
            intake2.setPower(0);
        }

        opMode.telemetry.addData("encoder: ", pozitie_lift);
        opMode.telemetry.addData("intake_direction: ", intake_direction);
        opMode.telemetry.addData("buton: ", buton);
        opMode.telemetry.addData("lift: ", lift1.getCurrentPosition());
    }
}