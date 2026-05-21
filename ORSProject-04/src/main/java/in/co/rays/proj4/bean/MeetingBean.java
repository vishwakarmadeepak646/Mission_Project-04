package in.co.rays.proj4.bean;

public class MeetingBean extends BaseBean {

    private String hostName;
    private String platform;
    private int duration;
    private int participants;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public String getValue() {
        return hostName + " (" + platform + ")";
    }
}