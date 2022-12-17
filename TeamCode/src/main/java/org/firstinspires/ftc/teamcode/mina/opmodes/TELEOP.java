package org.firstinspires.ftc.teamcode.mina.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;

@TeleOp(name = "MINA TELEOP")
public class TELEOP extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RWRobot.mina(this, StartEvent.StartType.CONTROL);
    }


}
