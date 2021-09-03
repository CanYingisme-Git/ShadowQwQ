package al.nya.shadowqwq.runnable;

import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.HistoryTodayInfo;
import al.nya.shadowqwq.utils.NoticeInfo;
import al.nya.shadowqwq.utils.hypixel.HypixelAPIInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.Bot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class DayNoticeTimerTask extends TimerTask {
    @Override
    public void run() {
        new DayNotice().notice();
    }
}
