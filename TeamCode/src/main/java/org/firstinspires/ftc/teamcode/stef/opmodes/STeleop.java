package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.stef.resurse.SGamepad;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.SRoti;


@TeleOp(name = "TeleOp")
public class STeleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SHardware.init(this);
        SGamepad.init();


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){

            SGamepad.loop(this);

            SRoti.loop(this);

            telemetry.update();
        }

        SHardware.initializat = false;
    }
}
