package org.firstinspires.ftc.teamcode.mina.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;

//autonomie albastru dreapta
@Autonomous(name = "A_A_D", group = "MINA")
public class AUTONOMIE_A_D extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RWRobot.mina(this, StartEvent.StartType.AUTONOMIE_ALBASTRU_DREAPTA);
    }


}
