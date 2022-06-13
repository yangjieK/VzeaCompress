package com.zz.video.bean;

import com.arthenica.ffmpegkit.LogCallback;
import com.arthenica.ffmpegkit.MediaInformationSession;
import com.arthenica.ffmpegkit.MediaInformationSessionCompleteCallback;

public class VzzMediaInfoSession extends MediaInformationSession {
    public VzzMediaInfoSession(String[] arguments) {
        super(arguments);
    }

    public VzzMediaInfoSession(String[] arguments, MediaInformationSessionCompleteCallback completeCallback) {
        super(arguments, completeCallback);
    }

    public VzzMediaInfoSession(String[] arguments, MediaInformationSessionCompleteCallback completeCallback, LogCallback logCallback) {
        super(arguments, completeCallback, logCallback);
    }
}
