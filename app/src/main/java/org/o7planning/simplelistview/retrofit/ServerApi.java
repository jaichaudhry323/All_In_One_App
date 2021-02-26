package org.o7planning.simplelistview.retrofit;

import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserRequest;
import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserResponse;

import retrofit2.Call;

public class ServerApi {

    public static Call<UserResponse> createUserByPostMethod(UserRequest userRequest)
    {
        return ServerAPIBuilder.getApiService().createUser(userRequest);
    }

}
