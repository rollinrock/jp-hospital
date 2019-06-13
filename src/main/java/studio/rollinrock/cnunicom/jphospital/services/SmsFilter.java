package studio.rollinrock.cnunicom.jphospital.services;

import lombok.NonNull;

/**
 * 短信功能过滤接口
 */
public interface SmsFilter {
    /**
     * 是否允许该手机号当前的短信发送功能
     * @param mobile
     * @return
     */
    boolean allow(@NonNull String mobile);

    /**
     * 不允许发送的情况下给出原因
     * @param mobile
     * @return
     */
    String explain(@NonNull String mobile);
}
