package mohapps.inappupdate.helper;

import java.util.ArrayList;
import java.util.List;

public class ForceUpdateStrategyConfig {
    private List<Integer> forceUpdateStrategyList;
    private Long endsWith;
    private Integer majorLength;

    public List<Integer> getForceUpdateStrategyList() {
        return forceUpdateStrategyList;
    }

    public void setForceUpdateStrategyList(List<Integer> forceUpdateStrategyList) {
        this.forceUpdateStrategyList = forceUpdateStrategyList;
    }

    public Long getEndsWith() {
        return endsWith;
    }

    public void setEndsWith(Long endsWith) {
        this.endsWith = endsWith;
    }

    public Integer getMajorLength() {
        return majorLength;
    }

    public void setMajorLength(Integer majorLength) {
        this.majorLength = majorLength;
    }

    private ForceUpdateStrategyConfig(Builder builder) {
        forceUpdateStrategyList = builder.forceUpdateStrategyList;
        endsWith = builder.endsWith;
        majorLength = builder.majorLength;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private List<Integer> forceUpdateStrategyList = new ArrayList<>();
        private Long endsWith;
        private Integer majorLength;


        public ForceUpdateStrategyConfig.Builder setForceUpdateStrategyList(List<Integer> forceUpdateStrategyList) {
            this.forceUpdateStrategyList = forceUpdateStrategyList;
            return this;
        }
        public ForceUpdateStrategyConfig.Builder setEndsWith(Long endsWith) {
            this.endsWith = endsWith;
            return this;
        }
        public ForceUpdateStrategyConfig.Builder setMajorLength(Integer majorLength) {
            this.majorLength = majorLength;
            return this;
        }
        public ForceUpdateStrategyConfig build() {
            return new ForceUpdateStrategyConfig(this);
        }
    }

}
