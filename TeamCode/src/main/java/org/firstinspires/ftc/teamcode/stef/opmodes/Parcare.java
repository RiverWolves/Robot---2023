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

        drive.setPoseEstimate(new Pose2d(-33, -62, Math.toRadians(90)));

        TrajectorySequence principala = drive.trajectorySequenceBuilder(new Pose2d(-33, -62, Math.toRadians(90)))
                .forward(2.5)
                .waitSeconds(0.2)
                .addDisplacementMarker( () -> {
                    Intake.setInchis(false);
                    Intake.loop(this);
//                    Lift.setLiftLevel(3);
                })
                .strafeRight(24)
                .waitSeconds(0.2)
//                .addDisplacementMarker(()->{
//                    Brat.brat_fata();
//                    Brat.loop(this);
//                })

                .splineTo(new Vector2d(-20, -8.8), Math.toRadians(120) )

                .waitSeconds(0.5)
                .addTemporalMarker(4,  ()->{
                    Intake.setInchis(true);
                    Intake.loop(this);
//                    Lift.setLiftLevel(0);
                })
                .back(4)
                .turn(Math.toRadians(60))
                .build();

        TrajectorySequence first = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .splineTo(new Vector2d(-60, -12), Math.toRadians(180))
                .build();

        TrajectorySequence second = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .splineTo(new Vector2d(-35, -12), Math.toRadians(180))
                .build();

        TrajectorySequence third = drive.trajectorySequenceBuilder(principala.end())
                .waitSeconds(0.5)
                .back(7)
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
