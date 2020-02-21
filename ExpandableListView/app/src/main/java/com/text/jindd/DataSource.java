package com.text.jindd;

public class DataSource {

    public static final String[] FATHER = {"VEHICLE SETTING","DRIVING ASSISTANT"};
    public static final String[][] CHILD_NAME = {
            {"Lamp","Lock","Side Mirro and Seat","Skylight","Ambient Linght","tyre","ATS Special effect"},
            {"Forward Collision Warning","Lane Assistant System","Rear Drive Assist","Parking Distance Control","Speed Assist System","Fatigue Warning"}
    };
    public static final Integer[][] CHILD_IMAGE = {
            {R.drawable.lamp_on,R.drawable.lock,R.drawable.side_mirror_off, R.drawable.skylight_off,R.drawable.ambient_light_off,R.drawable.tyre,R.drawable.ats_off},
            {R.drawable.fcw_on,R.drawable.las_off,R.drawable.rda_off,R.drawable.pad_off,R.drawable.sas_off,R.drawable.fatigue_warning_off}
    };
}
