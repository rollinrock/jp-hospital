package studio.rollinrock.cnunicom.jphospital.services.filters;

import org.springframework.stereotype.Component;
import studio.rollinrock.cnunicom.jphospital.services.SmsFilter;

/**
 * 中国联通号码段过滤
 */
@Component
public class CnunicomMobileMatchFilter extends SmsFilterAdapter implements SmsFilter {
    @Override
    public boolean allow(String mobile) {
        //todo 示例判断：是否以156开头
        //需要考虑是否支持国际号码前缀标识
        return mobile.startsWith("156");
    }

    @Override
    public String explain(String mobile) {
        return String.format("当前号码[%s]非中国联通合法号段", mobile);
    }
}
