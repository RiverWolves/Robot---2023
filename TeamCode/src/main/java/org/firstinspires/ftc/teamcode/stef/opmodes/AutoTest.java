package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.tag.Tag;

@Autonomous(name = "Autonomie Test")
public class AutoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


//        SHardware.init(this);

        Tag.init(this);

        waitForStart();

        while(!isStopRequested()){
            Tag.update(this);
            telemetry.update();

        }

        Tag.stop();
    }

}
