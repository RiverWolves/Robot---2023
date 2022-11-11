package org.firstinspires.ftc.teamcode.mina;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mina.camera.RWOpenCV;
import org.firstinspires.ftc.teamcode.mina.drives.BratDrive;
import org.firstinspires.ftc.teamcode.mina.drives.ControlBratDrive;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.drives.ControlMecanumDrive;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;
import org.firstinspires.ftc.teamcode.mina.events.StopEvent;
import org.firstinspires.ftc.teamcode.mina.listeners.ControllerListener;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;

import java.util.ArrayList;
import java.util.List;

public class RWRobot {

    public static OpMode opMode;
    public static Telemetry telemetry;

    public static List<Drive> drives;
    public static SampleMecanumDrive mecanumDrive = null;
    public static BratDrive bratDrive = null;

    public static StartEvent.StartType startType;

    public static List<Telemetrie> telemetrii = new ArrayList<>();

    public static void init(OpMode mode, StartEvent.StartType startType1){
        opMode = mode;
        telemetry = mode.telemetry;
        startType = startType1;
        drives = new ArrayList<>();

        RWConfig.init();
        RWOpenCV.init();

        mecanumDrive = new SampleMecanumDrive(opMode.hardwareMap);
        bratDrive = new BratDrive();

        drives.add(new ControlMecanumDrive());
        drives.add(new ControlBratDrive());

        for(Drive d : drives){
            d.onInit();
        }
    }

    public static void start(){
        new StartEvent(startType).execute();
        RWConfig.INCEPUT = true;
    }

    public static void stop(){
        new StopEvent().execute();
        RWConfig.INCEPUT = false;
        RWOpenCV.stop();
    }

    public static void update(){
        RWOpenCV.update();

        ControllerListener.update();

        for(Telemetrie t: telemetrii){
            for(String linie : t.getMesaj()){
                telemetry.addLine(t.getTelType()+" "+t.getTitlu()+": "+linie);
            }
            telemetry.addLine();
        }
        telemetry.update();
    }






    //functia principala care dicteaza actiunile robotului
    public static void mina(LinearOpMode lop, StartEvent.StartType startType){
        //initializam robotul si ii spunem ce autonomie pornim
        init(lop, startType);
        //anuntam daca modeul DEBUG e activat
        if(RWConfig.DEBUG){
            telemetry.addLine("ATENTIE!");
            telemetry.addLine("MODUL DEBUG ESTE ACTIVAT");
            telemetry.addLine("ACESTA POATE PRODUCE LAG/DELAY LA ROBOT");
            telemetry.addLine("DACA CONDUCETI ROBOTUL IN COMPETITIE, DEZACTIVATI MODUL DEBUG!!!");
            telemetry.addLine("ACESTA SE AFLA IN RWConfig.java");
            telemetry.update();
        }
        //asteptam sa se apese butonul de start
        lop.waitForStart();
        //chemam eventul de start
        start();

        //incepem o bucla care se opreste cand apasam be butonul de STOP
        while (!lop.isStopRequested()){
            //bucla updateaza continuu listenerii
            update();
        }
        //dupa ce oprim, chemam eventul de stop
        stop();
    }


}
