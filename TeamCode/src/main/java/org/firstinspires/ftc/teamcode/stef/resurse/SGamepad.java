package org.firstinspires.ftc.teamcode.stef.resurse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.stef.opmodes.STeleop;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Ceva;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Intake;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Lift;


public class SGamepad {
    private static float x, y, r, putere_lift;
    private static boolean fc_roti,
                           fc_lift;

    public static void init() {
        x = 0;
        y = 0;
        r = 0;
        putere_lift = 0;

        fc_roti = false;
        fc_lift = false;

    }

    public static void loop (OpMode opMode) {

        Gamepad gamepad1 = opMode.gamepad1;
        Gamepad gamepad2 = opMode.gamepad2;
        fc_lift = gamepad2.left_bumper;    //conditie fine control lift
        fc_roti = gamepad1.left_bumper;   //conditie fine control roti

        //Roti
        x = Ceva.fineControl(fc_roti, gamepad1.left_stick_x);            //setam puterea lui x
        y = Ceva.fineControl(fc_roti, gamepad1.left_stick_y);           //setam puterea lui y
        r = Ceva.fineControl(fc_roti, gamepad1.right_stick_x);         //setam puterea lui r
        STeleop.mecanum.setWeightedDrivePower(new Pose2d(x, y, r));           //atribuim puterile

        //Lift
        putere_lift = Ceva.fineControl(fc_lift, gamepad2.left_stick_y);   //setam puterea liftului
        Lift.setVal(gamepad2.left_stick_y, putere_lift);                 //atribuim stikul si puterea liftului

        //Intake
        boolean buton = Ceva.buttonToSwich(gamepad2.right_bumper);   //transformam bumperul in switch
        Intake.setVal(buton);                                       //setam starea intake-ului


        opMode.telemetry.addData("buton: ", buton);
        opMode.telemetry.update();
    }
}