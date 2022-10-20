package org.firstinspires.ftc.teamcode.stef.resurse;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.stef.opmodes.SAUTONOMIE;
import org.firstinspires.ftc.teamcode.tag.AutonAprilTagDetection;


public class SDmneAjuta {

    private static int FAZA = 0;
    private static ElapsedTime et;
    private static float LIMITARE_SUS_LIFT = 3800;
    private static float LIMITARE_JOS_LIFT = 0;
    private static DcMotor lift, lift2 = null;
    private static int parcare;

    public static void init() {
        SRoti.setVelXYR(0, 0, 0);

    }

    public static void loop(OpMode opMode) {

        parcare = AutonAprilTagDetection.poz();


        int pozitie_lift = SHardware.lift.getCurrentPosition();
        Telemetry telemetry = opMode.telemetry;

        lift = SHardware.lift;
        lift2 = SHardware.lift2;
        telemetry.addData("lift: ", pozitie_lift);
        telemetry.addData("parcare: ", parcare);



        }

    public static void stop(){
        et = null;
        FAZA = 0;
        SRoti.setVelXYR(0,0,0);
    }

    }

