package org.firstinspires.ftc.teamcode.stef.resurse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Brat;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Intake;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Lift;
import org.firstinspires.ftc.teamcode.stef.resurse.tag.Tag;

public class Autonomie {

    public static int tag;

    public static void init(OpMode op){
        SHardware.init(op);
        Tag.init(op);
        Brat.init();
        Intake.init();
        Lift.init();
        Tag.update(op);

        tag = Tag.tag();
    }

    public static void start(OpMode op){

        Brat.loop();
        Intake.loop(op);
        Lift.nivelLoop(op);

        SampleMecanumDrive drive = new SampleMecanumDrive(op.hardwareMap);

        /*drive.trajectoryBuilder(new Pose2d())
                .forward(30)
                .build(); */

//        drive.trajectoryBuilder(new Pose2d(-24,))

    }
}
