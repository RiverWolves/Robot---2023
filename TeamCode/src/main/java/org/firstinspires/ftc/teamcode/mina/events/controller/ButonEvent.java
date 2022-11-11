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

    public boolean eA(){
        return butonType == ButonType.A;
    }
    public boolean eB(){
        return butonType == ButonType.B;
    }
    public boolean eX(){
        return butonType == ButonType.X;
    }
    public boolean eY(){
        return butonType == ButonType.Y;
    }

    public boolean eD_STANGA(){
        return butonType == ButonType.D_STANGA;
    }
    public boolean eD_DREAPTA(){
        return butonType == ButonType.D_DREAPTA;
    }
    public boolean eD_SUS(){
        return butonType == ButonType.D_SUS;
    }
    public boolean eD_JOS(){
        return butonType == ButonType.D_JOS;
    }

    public boolean eSTANGA_BUMPER(){
        return butonType == ButonType.STANGA_BUMPER;
    }
    public boolean eDREAPTA_BUMPER(){
        return butonType == ButonType.DREAPTA_BUMPER;
    }

    public String getInfo(){
        return butonType.toString()+" - "+apasat;
    }
}