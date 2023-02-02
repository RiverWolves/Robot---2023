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
                        drive.trajectorySequenceBuilder(new Pose2d(33, -62, Math.toRadians(90)))

                                //Iese din Start Point
                                .forward(2.5)
                                .waitSeconds(0.2)

                                .strafeLeft(21.5)
                                .waitSeconds(0.2)

                                // In fata si se aliniaza sa puna conul.
                                .lineTo(new Vector2d(11, -30))
                                .splineTo(new Vector2d(18, -8.8), Math.toRadians(60) )
                                //Pune con
                                .waitSeconds(0.5)
                                //Se aliniaza spre turnul de conuri.
                                .back(4)
                                .waitSeconds(0.2)
                                .turn(Math.toRadians(-60))
                                .splineTo(new Vector2d(62, -12), Math.toRadians(0))
                                //Se intoarce sa puna con
                                .waitSeconds(0.5)
                                .setReversed(true)
                                .lineTo(new Vector2d(51, -12))
                                .splineTo(new Vector2d(29, -8.8), Math.toRadians(120))
                                .waitSeconds(1.5)
                                .setReversed(false)
                                //Se aliniaza spre turnul de conuri.
                                .splineTo(new Vector2d(48, -12), Math.toRadians(0))
                                .splineTo(new Vector2d(62, -12), Math.toRadians(0))

                                //Se intoarce sa puna con
                                .waitSeconds(0.5)
                                .setReversed(true)
                                .lineTo(new Vector2d(51, -12))
                                .splineTo(new Vector2d(29, -8.8), Math.toRadians(120))
                                .setReversed(false)
                                .waitSeconds(1.5)
                                //Se aliniaza spre turnul de conuri.
                                .splineTo(new Vector2d(48, -12), Math.toRadians(0))
                                .splineTo(new Vector2d(62, -12), Math.toRadians(0))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}