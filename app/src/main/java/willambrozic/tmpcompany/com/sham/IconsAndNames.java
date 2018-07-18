package willambrozic.tmpcompany.com.sham;

import java.util.List;

/**
 * Created by William on 2018-05-05.
 */

public class IconsAndNames  {

    private String name;
    private int icon;
    private int outline;
    private String message;
    private int repeatType;
    private String preClockText;
    private String postClockText;
    private int occurNum;
    private long[] times;

    public long[] getTimes() {
        return times;
    }

    public void setTimes(long[] times) {
        this.times = times;
    }

    public int getOccurNum() {
        return occurNum;
    }

    public void setOccurNum(int occurNum) {
        this.occurNum = occurNum;
    }

    public IconsAndNames() {
    }

    public IconsAndNames(String name, String message, int icon, int outline, int repeatType, String preClockText, String postClockText, int occurNum, long[] times) {
        this.name = name;
        this.icon = icon;
        this.outline = outline;
        this.message = message;
        this.repeatType = repeatType;
        this.preClockText = preClockText;
        this.postClockText = postClockText;
        this.occurNum = occurNum;
        this.times = times;
    }

    //Accessors


    public String getName() {
        return name;
    }


    public int getIcon() {
        return icon;
    }

    public int getOutline() {
        return outline;
    }

    public String getMessage() {
        return message;
    }

    public int getRepeatType() {
        return repeatType;
    }

    public String getPreClockText () {
        return preClockText;
    }
    public String getPostClockText () {
        return postClockText;
    }

    //Mutators


    public void setRepeatType(int repeatType) {
        this.repeatType = repeatType;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOutline(int outline) {
        this.outline = outline;
    }
    public void setPreClockText (String s) {
        preClockText = s;
    }
    public void setPostClockText (String s) {
        postClockText = s;
    }
}

