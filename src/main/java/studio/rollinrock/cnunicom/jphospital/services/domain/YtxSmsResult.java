package studio.rollinrock.cnunicom.jphospital.services.domain;


import lombok.Data;

@Data
@UrlQueryParamsFormat
public class YtxSmsResult {

    private String result;

    private String description;

}
