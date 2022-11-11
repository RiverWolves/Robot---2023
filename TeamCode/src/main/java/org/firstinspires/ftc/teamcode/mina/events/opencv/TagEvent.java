package org.firstinspires.ftc.teamcode.mina.events.opencv;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;
import org.opencv.core.Rect;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public class TagEvent extends RecogEvent {

    private List<AprilTagDetection> taguri;

    public TagEvent(List<AprilTagDetection> taguri) {
        super(RecogType.TAG_RECOG);
        set(taguri);
    }

    public TagEvent set(List<AprilTagDetection> taguri){
        this.taguri = taguri;

        seteOk(taguri != null && taguri.size() == 1);

        return this;
    }


    public List<AprilTagDetection> getTaguri() {
        return taguri;
    }

    @SuppressLint("DefaultLocale")
    public List<String> getInfo() {
        List<String> lista = new ArrayList<>();
        for(AprilTagDetection detection : taguri) {
            lista.add(String.format("Tag: %d%n, X: %.2f m, Y: %.2f m, Z %.2f m, Y: %.2f g, P: %.2f g, R %.2f g", detection.id, detection.pose.x, detection.pose.y, detection.pose.z, Math.toDegrees(detection.pose.yaw), Math.toDegrees(detection.pose.pitch), Math.toDegrees(detection.pose.roll)));
        }
        return lista;
    }
}
