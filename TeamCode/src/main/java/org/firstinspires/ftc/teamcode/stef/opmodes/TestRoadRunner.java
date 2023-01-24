package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Intake;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Lift;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(name = "test")
public class TestRoadRunner extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SHardware.init(this, true);
        Lift.init();
        Intake.init();

        Intake.setInchis(true);

        ElapsedTime et = new ElapsedTime();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-24, -62, Math.toRadians(90)));

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-24, -62, Math.toRadians(90)))
                .forward(5)
                .waitSeconds(0.2)
                .addDisplacementMarker( () -> {
                    Lift.setNivel(3);
                    Lift.nivelLoop(this);
                })
                .splineToConstantHeading(new Vector2d(-10, -57), Math.toRadians(90))
                .waitSeconds(0.2)

                .splineTo(new Vector2d(-17.5, -12), Math.toRadians(120) )

                .waitSeconds(0.5)
                .addTemporalMarker(4,  ()->{
                    Intake.setInchis(false);
                    Lift.setNivel(0);
                    Lift.nivelLoop(this);
                })
                .turn(Math.toRadians(60))
                .waitSeconds(0.5)

                .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                .waitSeconds(5)
                .build();



        waitForStart();

        et.reset();

        if (opModeIsActive()){
            drive.followTrajectorySequence(traj2);

            telemetry.addData("sec: ", et.seconds());
            telemetry.update();
        }


        if(isStopRequested()) return;





    }
    private static void lift(int nivel){
        int target_position = 0, power_nivel = 0;
        int poz_lift = SHardware.lift1.getCurrentPosition();

        switch (nivel) {
            case 0:
                target_position = Lift.NIVEL_0;
                break;

            case 1:
                target_position = Lift.NIVEL_1;
                break;

            case 2:
                target_position = Lift.NIVEL_2;
                break;

            case 3:
                target_position = Lift.NIVEL_3;
                break;
        }

        SHardware.lift1.setTargetPosition(target_position);
        SHardware.lift2.setTargetPosition(target_position);

        power_nivel = 1;

        SHardware.lift1.setPower(power_nivel);
        SHardware.lift2.setPower(power_nivel);

        SHardware.lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SHardware.lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
