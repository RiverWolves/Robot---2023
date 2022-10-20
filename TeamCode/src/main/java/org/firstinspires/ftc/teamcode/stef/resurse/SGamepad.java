package org.firstinspires.ftc.teamcode.stef.resurse;


import static org.firstinspires.ftc.teamcode.stef.resurse.SHardware.cleste1;
import static org.firstinspires.ftc.teamcode.stef.resurse.SHardware.cleste2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


public class SGamepad {

    private static float PUTERE_ROTI = 1;

    private static float LIMITARE_SUS_LIFT = 3800;
    private static float LIMITARE_JOS_LIFT = 50;


    private static Gamepad.RumbleEffect
            ref = new Gamepad.RumbleEffect.Builder()
                    .addStep(1, 0.2, 450)
                    .addStep(0, 0, 100)
                    .addStep(0.2, 1, 450)
                    .addStep(0, 0, 600)
                    .build();

    public static void init() {
        lift = null;
        lift2 = null;
        SHardware.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private static DcMotor lift = null;
    private static DcMotor lift2 = null;

    public static void loop(OpMode opMode) {
        if (!SHardware.initializat) return;
        if (lift == null) {
            lift = SHardware.lift;
        }

        if (lift2 == null) {
            lift2 = SHardware.lift2;
        }


            Gamepad gamepad1 = opMode.gamepad1;
            Gamepad gamepad2 = opMode.gamepad2;

            //cod roti
            SRoti.setVelXYR(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);


            //codul pentru lift

            int pozitie_lift = lift.getCurrentPosition();
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
                lift.setPower(putere);
                lift2.setPower(putere);
            } else if (left_stick_y < -0.5f && pozitie_lift > LIMITARE_JOS_LIFT) {
                lift.setPower(-putere);
                lift2.setPower(-putere);
            } else {
                lift.setPower(0);
                lift2.setPower(0);
            }

            boolean buton = Ceva.debouce(gamepad2.right_bumper);

            //cod cleste
            if(buton){
                cleste1.setPosition(0);
                cleste2.setPosition(1);

            }if(!buton){
                cleste1.setPosition(0.3);
                cleste2.setPosition(0.7);

            }
            opMode.telemetry.addData("encoder: ", pozitie_lift);
            opMode.telemetry.addData("buton: ", buton);
            opMode.telemetry.addData("lift: ", lift.getCurrentPosition());

        }


    }


