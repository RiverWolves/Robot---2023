package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.stef.resurse.drives.Intake;

@TeleOp ( name = "teleop test")
public class TestTeleop extends LinearOpMode {

    public static Servo intake, rotire;

    @Override
    public void runOpMode() throws InterruptedException {

        intake = (Servo) hardwareMap.get("intake");
        rotire = (Servo) hardwareMap.get("rotire");

        Intake.init();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){
            Intake.loop(this);

//            Intake.inchis = Ceva.buttonToSwich(gamepad1.right_bumper);
//            Intake.rotit = Ceva.buttonToSwich(gamepad1.left_bumper);

            Intake.setRotire(gamepad1.right_bumper);
            Intake.setInchis(gamepad1.left_bumper);

            telemetry.update();
        }



    }
}
