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
                .setConstraints(30, 74, Math.toRadians(180), Math.toRadians(180), 11.99)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -62, Math.toRadians(90)))
                                .forward(2.5)
                                .waitSeconds(0.2)

                                .strafeRight(25)
                                .waitSeconds(0.2)

                                .lineTo(new Vector2d(-11, -30))

                                .splineTo(new Vector2d(-20, -8.8), Math.toRadians(120) )
                                .waitSeconds(0.5)
                                .back(4)
                                .turn(Math.toRadians(60))



                                .forward(6)
                                .splineTo(new Vector2d(-58, -12), Math.toRadians(180))
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineTo(new Vector2d(-48, -12))
                                .splineTo(new Vector2d(-31, -12), Math.toRadians(60))
                                .setReversed(false)
                                .waitSeconds(0.5)


                                .splineTo(new Vector2d(-48, -12), Math.toRadians(180))
                                .lineTo(new Vector2d(-58, -12))
                                .waitSeconds(1)
                                .setReversed(true)
                                .lineTo(new Vector2d(-48, -12))
                                .splineTo(new Vector2d(-31, -12), Math.toRadians(60))
                                .setReversed(false)
                                .waitSeconds(0.5)
//
//                                .splineTo(new Vector2d(-48, -12), Math.toRadians(180))
//                                .lineTo(new Vector2d(-58, -12))
//                                .waitSeconds(1)
//                                .setReversed(true)
//                                .lineTo(new Vector2d(-48, -12))
//                                .splineTo(new Vector2d(-31, -12), Math.toRadians(60))
//                                .setReversed(false)
//                                .waitSeconds(0.5)
//
//                                .splineTo(new Vector2d(-48, -12), Math.toRadians(180))
//                                .lineTo(new Vector2d(-58, -12))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}