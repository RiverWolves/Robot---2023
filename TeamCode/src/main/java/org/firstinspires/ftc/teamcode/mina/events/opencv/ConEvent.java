package org.firstinspires.ftc.teamcode.mina.events.opencv;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.opencv.core.Rect;

import java.util.List;

public class ConEvent extends RecogEvent {

    private Rect[] detectii;
    private Rect best;

    public ConEvent(Rect[] detectii, Rect best) {
        super(RecogType.CON_RECOG);
        set(detectii, best);
    }

    public ConEvent set(Rect[] detectii, Rect best){
        this.detectii = detectii;
        this.best = best;

        seteOk(best != null);

        return this;
    }

    public Rect[] getDetectii() {
        return detectii;
    }

    public Rect getBest() {
        return best;
    }


    @Override
    public List<String> getInfo() {
        return null;
    }
}
