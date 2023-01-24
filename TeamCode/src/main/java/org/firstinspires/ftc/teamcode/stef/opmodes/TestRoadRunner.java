package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(name = "test")
public class TestRoadRunner extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-24, -62, Math.toRadians(90)));

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-24, -62, Math.toRadians(90)))
                .forward(5)
                .splineToConstantHeading(new Vector2d(-10, -57), Math.toRadians(90))
                .splineTo(new Vector2d(-17.5, -12), Math.toRadians(120) )
                .waitSeconds(0.5)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(traj2);

    }
}
