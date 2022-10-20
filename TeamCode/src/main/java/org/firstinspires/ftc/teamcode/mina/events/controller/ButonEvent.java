package org.firstinspires.ftc.teamcode.mina.events.controller;

public class ButonEvent extends ControllerEvent {

    public enum ButonType{
        A, B, X, Y,
        D_STANGA, D_DREAPTA, D_JOS, D_SUS,
        STANGA_BUMPER, DREAPTA_BUMPER
    }

    public boolean apasat;
    public ButonType butonType;
    public ButonEvent(Controller controller, ButonType tip, boolean apasat){
        super(ControllerEventType.BUTON,controller);
        this.butonType = tip;
        this.apasat = apasat;
    }

    public ButonEvent set(Controller controller, ButonType butonType, boolean apasat) {
        this.controller = controller;
        this.butonType = butonType;
        this.apasat = apasat;

        return this;
    }

    public String getInfo(){
        return butonType.toString()+" - "+apasat;
    }
}