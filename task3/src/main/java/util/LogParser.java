package util;

import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log
public class LogParser {

    public long getVolume(List<String> logStrings){
        return Long.parseLong(logStrings.get(1));
    }

    public long getCurrentLiters(List<String> logStrings){
        return Long.parseLong(logStrings.get(2));
    }

    public LocalDateTime getTime(String str){
        String dateInStr = str.replaceFirst(".{4}Z-\\[.*", "").trim();
        return DateHandler.parseTimeFromString(dateInStr);
    }

    public String getUsername(String str){
        int firstBracket = str.indexOf("[");
        int lastBracket = str.lastIndexOf("]");
        return str.substring(firstBracket+1, lastBracket);
    }

    public String getAction(String str){
        String temp = str.substring(str.indexOf("wanna")+6);
        if (temp.contains("top up")){
            return "top up";
        }else if(str.contains("scoop")){
            return "scoop";
        }else{
            return "unresolved";
        }
    }

    public long getLiters(String str){
        String temp = str.substring(str.indexOf("wanna")+6);
        String[] t = temp.split(" ");
        int len = t.length;
        return Long.parseLong(t[len-2].replace("l", ""));
    }

    public boolean getSuccess(String str){
        String[] strs = str.split(" ");
        String lastW = strs[strs.length-1];
        return lastW.substring(1, lastW.length() - 1).equals("успех");
    }
}
