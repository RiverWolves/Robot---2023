package org.firstinspires.ftc.teamcode.stef.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.stef.resurse.SConnection;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.SRoti;

import org.firstinspires.ftc.teamcode.stef.resurse.SVuforia;
import org.firstinspires.ftc.teamcode.stef.resurse.SDmneAjuta;

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
        //Initializam si vuforia pentru ca suntem la autonomie si ne ajuta
       // SVuforia.init(this);

        //asteptam pana se apasa butonul de start!
        waitForStart();

        //In loc de functia loop vom avea un while care se opreste cand apasam pe butonul de stop
        while(!isStopRequested()){
           // SVuforia.loop(this);
//            telemetry.addData("dist",SHardware.distanta.getDistance(DistanceUnit.MM));

            SDmneAjuta.loop(this);
//            SCreier.mergi(7650);
            SRoti.loop(this);


            telemetry.update();
        }


        SDmneAjuta.stop();
        SVuforia.stop();
        SConnection.stop();
        SHardware.initializat = false;
    }
}
