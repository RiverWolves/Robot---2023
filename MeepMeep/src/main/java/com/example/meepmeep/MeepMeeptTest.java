package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeeptTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 11.99)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-24, -62, Math.toRadians(90)))
                                .forward(5)
                                .splineToConstantHeading(new Vector2d(-10, -57), Math.toRadians(90))
                                .splineTo(new Vector2d(-17.5, -12), Math.toRadians(120) )
                                .waitSeconds(0.5)

                             /*   .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                                .waitSeconds(0.5)
                                .splineToConstantHeading(new Vector2d(-40, 0), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(-34, 0), Math.toRadians(180))
                                .waitSeconds(0.5)

                                .forward(6)
                                .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                                .waitSeconds(0.5)
                                .splineToConstantHeading(new Vector2d(-40, 0), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(-34, 0), Math.toRadians(180))
                                .waitSeconds(0.5)

                                .forward(6)
                                .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                                .waitSeconds(0.5)
                                .splineToConstantHeading(new Vector2d(-40, 0), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(-34, 0), Math.toRadians(180))
                                .waitSeconds(0.5)

                                .forward(6)
                                .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                                .waitSeconds(0.5)
                                .splineToConstantHeading(new Vector2d(-40, 0), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(-34, 0), Math.toRadians(180))
                                .waitSeconds(0.5)*/

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}