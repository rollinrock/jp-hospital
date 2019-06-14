package studio.rollinrock.cnunicom.jphospital.services.filters;

import lombok.NonNull;
import studio.rollinrock.cnunicom.jphospital.services.SmsFilter;

public class SmsFilterAdapter implements SmsFilter {

    /**是否启用**/
    protected boolean enabled = true;

    @Override
    public boolean allow(@NonNull String mobile) {
        return true;
    }

    @Override
    public String explain(@NonNull String mobile) {
        return "允许";
    }
}
