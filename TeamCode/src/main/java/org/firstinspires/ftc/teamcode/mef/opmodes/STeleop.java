package org.firstinspires.ftc.teamcode.mef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mef.resurse.SGamepad;
import org.firstinspires.ftc.teamcode.mef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.mef.resurse.SRoti;
import org.firstinspires.ftc.teamcode.mef.resurse.SVuforia;

@TeleOp(name = "Amin")
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
        SVuforia.stop();
        SHardware.initializat = false;
    }
}
