package studio.rollinrock.cnunicom.jphospital.services.api;

import retrofit2.Call;

public interface SmsVerificationCodeApi<T, E> {

    Call<T> send(E e);
}
