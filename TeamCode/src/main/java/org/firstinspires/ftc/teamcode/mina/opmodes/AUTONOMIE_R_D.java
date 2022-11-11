package org.firstinspires.ftc.teamcode.mina.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;

//autonomie rosu dreapta
@Autonomous(name = "A_R_D", group = "MINA")
public class AUTONOMIE_R_D extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RWRobot.mina(this, StartEvent.StartType.AUTONOMIE_ROSU_DREAPTA);
    }


}
