package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.services.VCGenerateService;

@Service
public class VCGenerateServiceImpl implements VCGenerateService {
    @Override
    public String genRandomNumIn6(long aliveExpiry) {
        //todo 实现
        int code = new java.util.Random().nextInt(900000)+100000;
        return String.valueOf(code);
    }
}