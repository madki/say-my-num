package xyz.madki.saymynum;


/**
 * Created by madki on 03/02/16.
 * Every non-empty string appended adds a space before it
 */
public class SpacedStringBuilder {
    private final StringBuilder sb;
    private boolean notEmpty = false;

    public SpacedStringBuilder(String str) {
        sb = new StringBuilder(str);
        if(!TextUtils.isEmpty(str)) notEmpty = true;
    }

    public SpacedStringBuilder(int capacity) {
        sb = new StringBuilder(capacity);
    }

    public SpacedStringBuilder append(String str) {
        if(!TextUtils.isEmpty(str)) {
            if(notEmpty) {
                sb.append(" ");
            } else {
                notEmpty = true;
            }
            sb.append(str);
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
