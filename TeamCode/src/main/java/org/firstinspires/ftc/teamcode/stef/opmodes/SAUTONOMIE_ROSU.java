package org.firstinspires.ftc.teamcode.stef.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.firstinspires.ftc.teamcode.stef.resurse.drives.Roti;

import org.firstinspires.ftc.teamcode.stef.resurse.Autonumoie_Rosu;
import org.firstinspires.ftc.teamcode.stef.resurse.tag.Tag;

@Autonomous( name = "hai ba te rog eu")
public class SAUTONOMIE_ROSU extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //OPTIONAL Initializam inainte serverul pentru conexiunea externa cu laptopul
        //daca aplicatia se inchide in timpul testului neasteptat, stergeti linia SConnection.init();

//        SConnection.init(); //STERGE LINIA ASTA DACA APLICATIA SE INCHIDE RANDOM

        //In primul rand trebuie sa initializam parte de hardware
        //deci chemam fucntia init din clasa SHardware si pasam argumentul OpMode
        //care este "this", adica clasa asta, intrucat extinde OpMode!!
        SHardware.init(this);

//        Tag.init();

//        Autonumoie_Rosu.init();

        //asteptam pana se apasa butonul de start!
        waitForStart();

        //In loc de functia loop vom avea un while care se opreste cand apasam pe butonul de stop
        while(!isStopRequested()){

//            Tag.getTag(this);

        }


        Autonumoie_Rosu.stop();
        SHardware.initializat = false;
    }
}
