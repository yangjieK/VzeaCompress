package com.zz.video;

import android.util.Log;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegKitConfig;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.Level;
import com.arthenica.ffmpegkit.SessionState;
import com.zz.video.bean.FFmpegArguments;
import com.zz.video.bean.VzzFFmpegSession;
import com.zz.video.bean.VzzMediaInfoSession;
import com.zz.video.inf.VzzLogListener;
import com.zz.video.inf.VzzStatisticsListener;

import java.util.List;

/**
 * FFmpeg
 */
public class VzzFFmpegIns {

    private static final int PROBE_MEDIAINFO_TIMEOUT = 3000;
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

    public VzzFFmpegSession compress(FFmpegArguments fFmpegArgs, VzzStatisticsListener statListener, VzzLogListener logCallback) {
        VzzFFmpegSession session = new VzzFFmpegSession(fFmpegArgs.toArray(),null, logCallback, statListener);
        FFmpegKitConfig.ffmpegExecute(session);
        if (session.getState() == SessionState.FAILED || !session.getReturnCode().isValueSuccess()) {
            Log.d("VzzFFmpegIns",String.format("FFprobe exited with state %s and rc %s.%s", new Object[]{FFmpegKitConfig.sessionStateToString(session.getState()), session.getReturnCode(), notNull(session.getFailStackTrace(), "\n")}));
        }
        return session;
    }

    public VzzMediaInfoSession getMediaInfo(String path, int timeout){
        final VzzMediaInfoSession session = new VzzMediaInfoSession(defaultMediaInfoCmdArguments(path));
        FFmpegKitConfig.getMediaInformationExecute(session, timeout);
        return session;
    }

    public VzzMediaInfoSession getMediaInfo(String path){
        final VzzMediaInfoSession session = new VzzMediaInfoSession(defaultMediaInfoCmdArguments(path));
        FFmpegKitConfig.getMediaInformationExecute(session, PROBE_MEDIAINFO_TIMEOUT);
        return session;
    }

    private  String[] defaultMediaInfoCmdArguments(final String path) {
        return new String[]{"-v", "error", "-hide_banner", "-print_format", "json", "-show_format", "-show_streams", "-show_chapters", "-i", path};
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
