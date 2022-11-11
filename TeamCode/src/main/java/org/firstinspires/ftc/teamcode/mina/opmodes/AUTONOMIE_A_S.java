package org.firstinspires.ftc.teamcode.mina.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;

//autonomie albastru stanga
@Autonomous(name = "A_A_S", group = "MINA")
public class AUTONOMIE_A_S extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RWRobot.mina(this, StartEvent.StartType.AUTONOMIE_ALBASTRU_STANGA);
    }


}
