package com.zz.video.bean;

import com.arthenica.ffmpegkit.Chapter;
import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VzzMediaInfo extends MediaInformation {
    private List<VzzStreamInfo> mStreams;
    private List<VzzChapter> mChapters;

    public VzzMediaInfo(JSONObject jsonObject, List<VzzStreamInfo> streams, List<VzzChapter> chapters) {
        super(jsonObject,new ArrayList<>(),new ArrayList<>());
        setVzzStream(streams);
        setVzzChapters(chapters);
    }

    private void setVzzStream(List<VzzStreamInfo> streams) {
        mStreams = streams;
    }

    private void setVzzChapters(List<VzzChapter> chapters) {
        mChapters = chapters;
    }

    @Deprecated
    @Override
    public List<StreamInformation> getStreams() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public List<Chapter> getChapters() {
        throw new UnsupportedOperationException();
    }

    public List<VzzStreamInfo> getVzzStreams() {
        return mStreams;
    }

    public List<VzzChapter> getVzzChapters() {
        return mChapters;
    }
}
