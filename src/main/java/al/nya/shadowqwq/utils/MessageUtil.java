package al.nya.shadowqwq.utils;

import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.SingleMessage;

public class MessageUtil {
    public static String getMessage(MessageChain messageChain){
        String mergedMessage = "";
        int i = 0;
        for (SingleMessage singleMessage : messageChain) {
            if (i != 0){
                mergedMessage = mergedMessage + singleMessage.toString();
            }
            i = i + 1;
        }
        return mergedMessage;
    }
}
