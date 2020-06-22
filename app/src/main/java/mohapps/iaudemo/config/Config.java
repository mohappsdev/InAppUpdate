package mohapps.iaudemo.config;

import java.util.ArrayList;
import java.util.List;

import mohapps.inappupdate.helper.ForceUpdateStrategyConfig;

public class Config {


    public static List<Integer> FORCE_UPDATE_STRATEGY_LIST() {
        List<Integer> FORCE_UPDATE_STRATEGY_LIST = new ArrayList<>();
        //TODO: Add force update strategies here
        //FORCE_UPDATE_STRATEGY_LIST.add(ForceUpdateStrategy.LAST_DIGIT);
        //FORCE_UPDATE_STRATEGY_LIST.add(ForceUpdateStrategy.MAJOR_CHANGE);
        return FORCE_UPDATE_STRATEGY_LIST;
    }
    //To use ForceUpdateStrategy.LAST_DIGIT you have to specify a positive number (no limit on digits)
    //example: If you set it as 4, updates with version code of *4 such as 4, 14, 24, 34, ..., 104, 114, ... will be forced to users
    //example: If you set it as 23, updates with version code of *23 such as 23, 123, 223, 323, ..., 1023, 1123, ... will be forced to users
    public static Long FORCE_UPDATE_LAST_DIGIT = (long) -1;

    //To use ForceUpdateStrategy.MAJOR_CHANGE you have to specify a positive number (no limit on digits)
    //example: If you set it as 1, updates where 1 digit from left chenged such as 1 to 2, 12 to 22, 100658 to 200033 will be forced to users
    //example: If you set it as 2, updates where 2 digits from left chenged such as 11 to 22, 120 to 230, 100658 to 210033 will be forced to users
    //If your versions number of digits increased update will be forced
    //example: updates where number of digits increased such as 9 to 10, 99 to 100 will be forced to users

    public static Integer FORCE_UPDATE_MAJOR_LENGTH = -1;



    public static ForceUpdateStrategyConfig getCacheUtilConfig(){
        return ForceUpdateStrategyConfig.builder()
                .setForceUpdateStrategyList(FORCE_UPDATE_STRATEGY_LIST())
                .setEndsWith(FORCE_UPDATE_LAST_DIGIT)
                .setMajorLength(FORCE_UPDATE_MAJOR_LENGTH)
                .build();
    }
}
