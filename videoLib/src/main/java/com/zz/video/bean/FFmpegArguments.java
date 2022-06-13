package com.zz.video.bean;

import java.util.ArrayList;

public class FFmpegArguments {
    private final ArrayList argList;

    public FFmpegArguments() {
        argList = new ArrayList();
    }

    public FFmpegArguments append(String s) {
        argList.add(s);
        return this;
    }

    public String[] toArray() {
        return (String[]) argList.toArray(new String[argList.size()]);
    }
}
