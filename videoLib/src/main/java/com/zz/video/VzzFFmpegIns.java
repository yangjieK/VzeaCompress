package com.zz.video;

import android.util.Log;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegKitConfig;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.FFprobeKit;
import com.arthenica.ffmpegkit.Level;
import com.arthenica.ffmpegkit.LogCallback;
import com.arthenica.ffmpegkit.MediaInformationSession;
import com.arthenica.ffmpegkit.SessionState;
import com.arthenica.ffmpegkit.StatisticsCallback;

import java.util.List;

public class VzzFFmpegIns {

    private static VzzFFmpegIns ins;

    private VzzFFmpegIns() {
    }

    public static final class K{
        public static VzzFFmpegIns ff = new VzzFFmpegIns();
    }

    public static VzzFFmpegIns getInstance() {
        return K.ff;
    }

    public static void disableLog(){
        FFmpegKitConfig.setLogLevel(Level.AV_LOG_DEBUG);
    }

    public static void setLogLevel(final Level level) {
        FFmpegKitConfig.setLogLevel(level);
    }

    public FFmpegSession compress(FFmpegArguments fFmpegArgs, StatisticsCallback statListener, LogCallback logCallback) {
        FFmpegSession session = new FFmpegSession(fFmpegArgs.toArray(),null, logCallback, statListener);
        FFmpegKitConfig.ffmpegExecute(session);
        if (session.getState() == SessionState.FAILED || !session.getReturnCode().isValueSuccess()) {
            Log.d("VzzFFmpegIns",String.format("FFprobe exited with state %s and rc %s.%s", new Object[]{FFmpegKitConfig.sessionStateToString(session.getState()), session.getReturnCode(), notNull(session.getFailStackTrace(), "\n")}));
        }
        return session;
    }

    public MediaInformationSession getMediaInfo(String path){
        return FFprobeKit.getMediaInformation(path);
    }

    private String notNull(String string, String valuePrefix) {
        if (string == null) {
            return "";
        }
        return String.format("%s%s", valuePrefix, string);
    }

    public void cancel(long sessionId){
        FFmpegKit.cancel(sessionId);
    }

    public void cancel(){
        FFmpegKit.cancel();
    }

    public void logLevel(Level level){
        FFmpegKitConfig.setLogLevel(level);
    }

    public List<FFmpegSession> getFFmpegSessions(){
        return FFmpegKitConfig.getFFmpegSessions();
    }
}
