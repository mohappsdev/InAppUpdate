package mohapps.myproject.helper;

public class Config {
    public static ForceUpdateStrategyConfig getCacheUtilConfig(){
        return ForceUpdateStrategyConfig.builder()
                .setForceUpdateStrategyList(Constants.FORCE_UPDATE_STRATEGY_LIST())
                .setEndsWith(Constants.FORCE_UPDATE_LAST_DIGIT)
                .setMajorLength(Constants.FORCE_UPDATE_MAJOR_LENGTH)
                .build();
    }
}
