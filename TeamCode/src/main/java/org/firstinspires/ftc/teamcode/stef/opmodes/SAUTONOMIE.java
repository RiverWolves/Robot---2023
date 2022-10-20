package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.SRoti;

import org.firstinspires.ftc.teamcode.stef.resurse.SDmneAjuta;
import org.firstinspires.ftc.teamcode.tag.AutonAprilTagDetection;

@Autonomous(name = "SAUTONOMIE")
public class SAUTONOMIE extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //OPTIONAL Initializam inainte serverul pentru conexiunea externa cu laptopul
        //daca aplicatia se inchide in timpul testului neasteptat, stergeti linia SConnection.init();

//        SConnection.init(); //STERGE LINIA ASTA DACA APLICATIA SE INCHIDE RANDOM

        //In primul rand trebuie sa initializam parte de hardware
        //deci chemam fucntia init din clasa SHardware si pasam argumentul OpMode
        //care este "this", adica clasa asta, intrucat extinde OpMode!!
        SHardware.init(this);

        SDmneAjuta.init();

        AutonAprilTagDetection.init(this);

        //asteptam pana se apasa butonul de start!
        waitForStart();

        //In loc de functia loop vom avea un while care se opreste cand apasam pe butonul de stop
        while(!isStopRequested()){

            SDmneAjuta.loop(this);

            SRoti.loop(this);


            telemetry.update();
        }


        SDmneAjuta.stop();
        SHardware.initializat = false;
    }
}
