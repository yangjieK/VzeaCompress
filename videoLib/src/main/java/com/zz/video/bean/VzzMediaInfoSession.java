package com.zz.video.bean;

import com.arthenica.ffmpegkit.Chapter;
import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.MediaInformationSession;
import com.arthenica.ffmpegkit.StreamInformation;

import java.util.ArrayList;
import java.util.List;

public class VzzMediaInfoSession extends MediaInformationSession {
    private VzzMediaInfo mediaInformation;

    public VzzMediaInfoSession(String[] arguments) {
        super(arguments);
    }

    @Override
    public VzzMediaInfo getMediaInformation() {
        return mediaInformation;
    }

    @Override
    public void setMediaInformation(MediaInformation mediaInfo) {
        ArrayList<VzzStreamInfo> zStreams = new ArrayList<>();
        // add VzzStreamInfo
        for (StreamInformation stream :mediaInfo.getStreams()){
            zStreams.add(new VzzStreamInfo(stream.getAllProperties()));
        }
        // add VzzChapter
        List<VzzChapter> zChapters = new ArrayList<>();
        for (Chapter chapter :mediaInfo.getChapters()){
            zChapters.add(new VzzChapter(chapter.getAllProperties()));
        }
        this.mediaInformation= new VzzMediaInfo(mediaInfo.getAllProperties(),zStreams,zChapters);
    }

}
