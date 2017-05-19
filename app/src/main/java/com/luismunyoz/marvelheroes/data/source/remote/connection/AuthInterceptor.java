package com.luismunyoz.marvelheroes.data.source.remote.connection;

import com.luismunyoz.marvelheroes.BuildConfig;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Luism on 02/04/2017.
 */

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        String ts = String.valueOf(System.currentTimeMillis());

        String toMd5 = ts + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY;
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String result = "";
        if(messageDigest != null) {
            messageDigest.reset();
            messageDigest.update(toMd5.getBytes());
            byte[] resultByte = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, resultByte);
            result = bigInt.toString(16);
            while(result.length() < 32 ){
                result = "0" + result;
            }
        }

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", result)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

