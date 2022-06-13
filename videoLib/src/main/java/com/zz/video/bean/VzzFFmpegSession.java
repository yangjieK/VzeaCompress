package com.zz.video.bean;

import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.FFmpegSessionCompleteCallback;
import com.arthenica.ffmpegkit.LogCallback;
import com.arthenica.ffmpegkit.LogRedirectionStrategy;
import com.arthenica.ffmpegkit.StatisticsCallback;

public class VzzFFmpegSession extends FFmpegSession {
    public VzzFFmpegSession(String[] arguments) {
        super(arguments);
    }

    public VzzFFmpegSession(String[] arguments, FFmpegSessionCompleteCallback completeCallback) {
        super(arguments, completeCallback);
    }

    public VzzFFmpegSession(String[] arguments, FFmpegSessionCompleteCallback completeCallback, LogCallback logCallback, StatisticsCallback statisticsCallback) {
        super(arguments, completeCallback, logCallback, statisticsCallback);
    }

    public VzzFFmpegSession(String[] arguments, FFmpegSessionCompleteCallback completeCallback, LogCallback logCallback, StatisticsCallback statisticsCallback, LogRedirectionStrategy logRedirectionStrategy) {
        super(arguments, completeCallback, logCallback, statisticsCallback, logRedirectionStrategy);
    }
}
