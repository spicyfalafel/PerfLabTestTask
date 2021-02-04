
import lombok.extern.java.Log;
import model.BarrelEvent;
import util.CSVSaver;
import util.DateHandler;
import util.LogParser;
import util.LogReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class Main {

    private static List<BarrelEvent> all = new ArrayList<>();

    private static LocalDateTime from, to;
    public static void main(String[] args) {
        if(args.length<1){
            log.warning("no path to log file provided.");
            return;
        }
        String pathToLog = args[0];
        log.info("path to log is " + pathToLog);


        String timeFrom=null;
        String timeTo=null;

        if (args.length==3){
            timeFrom = args[1];
            timeTo = args[2];
        }

        LogReader lr = new LogReader();
        LogParser logParser = new LogParser();
        List<String> logStrings = lr.readLog(pathToLog);

        for(int i = 3; i<logStrings.size(); i++){
            String str = logStrings.get(i);
            BarrelEvent be = new BarrelEvent();
            be.setTime(logParser.getTime(str));
            be.setUsername(logParser.getUsername(str));
            be.setAction(logParser.getAction(str));
            be.setLiters(logParser.getLiters(str));
            be.setSuccess(logParser.getSuccess(str));
            all.add(be);
        }


        if (timeFrom != null && timeTo != null) {
            try{
                from = DateHandler.parseTimeFromString(timeFrom);
                to = DateHandler.parseTimeFromString(timeTo);

                if(from.isAfter(to)){
                    log.warning("First date should be earlier than second one. Using no args.");
                }else{
                    all = all.stream().filter(e -> e.getTime().compareTo(from) >= 0 && e.getTime().compareTo(to) <= 0)
                            .collect(Collectors.toList());
                    log.info("Size of barrel events after filtering:" + all.size());
                }

            }catch (Exception e){
                log.warning("Couldn't parse time arguments. Format: yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            }
        }

        //1
        long topUpCount = all.stream().filter(e -> e.getAction().trim().equals("top up")).count();
        //2
        long mistakes = all.stream().filter(e -> !e.getSuccess()).count();
        double mistakesPercent =  ( (double) mistakes/all.size())*100;
        //3
        long sum = all.stream().filter(e -> e.getAction().trim().equals("top up") && e.getSuccess()).map(e -> e.getLiters())
                .reduce(0L, Long::sum);
        //4
        long topUpMistakesVolume = all.stream().filter(e -> e.getAction().equals("top up") && !e.getSuccess())
                .map(BarrelEvent::getLiters).reduce(0L, Long::sum);

        // 5
        long scoop = all.stream().filter(e -> e.getAction().trim().equals("scoop")).count();
        // 6
        long sum2 = all.stream().filter(e -> e.getAction().trim().equals("scoop") && e.getSuccess()).map(e -> e.getLiters())
                .reduce(0L, Long::sum);
        // 7
        long scoopMistakesVolume = all.stream().filter(e -> e.getAction().equals("scoop") && !e.getSuccess())
                .map(BarrelEvent::getLiters).reduce(0L, Long::sum);


        CSVSaver.saveToCSV(new String[]{
                String.valueOf(topUpCount), String.valueOf(mistakesPercent),
                String.valueOf(sum), String.valueOf(topUpMistakesVolume),
                String.valueOf(scoop), String.valueOf(sum2), String.valueOf(scoopMistakesVolume),
        });

        log.info("Program finished");

    }


}
