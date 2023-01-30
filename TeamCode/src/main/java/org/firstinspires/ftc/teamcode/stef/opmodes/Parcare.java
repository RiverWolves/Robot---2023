package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Brat;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Intake;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Lift;
import org.firstinspires.ftc.teamcode.stef.resurse.tag.TagBase;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous( name = "amin" )
public class Parcare extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SHardware.init(this, true);
        Lift.init();
        Intake.init();
        Brat.init();
        TagBase.init(this);

        Intake.setInchis(true);

        ElapsedTime et = new ElapsedTime();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-31.22, -62, Math.toRadians(90)));

        TrajectorySequence principala = drive.trajectorySequenceBuilder(new Pose2d(-31.22, -62, Math.toRadians(90)))
                .forward(2.5)
                .waitSeconds(0.2)
                .addDisplacementMarker( () -> {
                    Intake.setInchis(false);
                    Intake.loop(this);
                    Lift.setLiftLevel(3);
                })
                .strafeRight(23)
                .waitSeconds(0.2)
//                .addDisplacementMarker(()->{
//                    Brat.brat_fata();
//                    Brat.loop(this);
//                })
//                .forward(10)
                .lineTo(new Vector2d(-11, -30))
                .splineTo(new Vector2d(-22, -8.8), Math.toRadians(120) )

                .waitSeconds(0.5)
                .addDisplacementMarker(()->{
                    Lift.setLiftLevel(0);
                })
                .back(4)
                .waitSeconds(0.2)
                .turn(Math.toRadians(60))
                .splineTo(new Vector2d(-62, -12), Math.toRadians(180))
                .addDisplacementMarker(() ->{
                    Lift.setLiftLevel(3);
                })
                .waitSeconds(0.5)
                .setReversed(true)
                .lineTo(new Vector2d(-51, -12))
                .splineTo(new Vector2d(-31, -11), Math.toRadians(60))
                .waitSeconds(1.5)
                .setReversed(false)
                .addDisplacementMarker(() ->{
                    Lift.setLiftLevel(0);
                })
                .splineTo(new Vector2d(-48, -11), Math.toRadians(180))
                .splineTo(new Vector2d(-62, -12), Math.toRadians(180))
                .addDisplacementMarker(()->{
                    Lift.setLiftLevel(3);
                })

                .waitSeconds(0.5)
                .setReversed(true)
                .lineTo(new Vector2d(-51, -12))
                .splineTo(new Vector2d(-31, -11), Math.toRadians(60))
                .setReversed(false)
                .waitSeconds(1.5)
                .addDisplacementMarker(() ->{
                    Lift.setLiftLevel(0);
                })
                .splineTo(new Vector2d(-48, -12), Math.toRadians(180))
                .splineTo(new Vector2d(-62, -12), Math.toRadians(180))
                .build();

        TrajectorySequence first = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .lineTo(new Vector2d(-60, -12))
                .build();

        TrajectorySequence second = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .lineTo(new Vector2d(-35, -12))
                .build();

        TrajectorySequence third = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .lineTo(new Vector2d(-12,-12))
                .build();


        while (opModeInInit()){
            TagBase.update(this);
        }



        waitForStart();

        et.reset();

        if (opModeIsActive()){
            drive.followTrajectorySequence(principala);


            switch (TagBase.tag()){
                case 1:
                    drive.followTrajectorySequence(first);
                    break;
                case 2:
                    drive.followTrajectorySequence(second);
                    break;
                case 3:
                    drive.followTrajectorySequence(third);
                    break;

            }

            telemetry.addData("sec: ", et.seconds());
            telemetry.update();
        }


        if(isStopRequested()) {
            TagBase.stop();
            return;
        }
    }
}
