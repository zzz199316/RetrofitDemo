package com.plgf.myretrofit.network;

import android.content.Context;
import com.plgf.myretrofit.network.base.Constants;
import com.plgf.myretrofit.network.base.Settings;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private volatile  static ServiceClient serviceClient;

    private Retrofit retrofit;
    private ServiceClient(Context context,String baseUrl){
        retrofit=buildRetrofit(baseUrl,context);

    }

    /**
     * 单例获取客户端实例
     * @param context
     * @return
     */
    public static  ServiceClient getSingletonInstance(Context context){
        if (serviceClient==null){
            synchronized (ServiceClient.class){
                if (serviceClient==null){
                    serviceClient= new ServiceClient(context, Constants.BASE_URL);
                }
            }
        }
        return  serviceClient;
    }

    /**
     * 根据BaseUrl获取Retrofit
     * @param context
     * @param baseUrl
     * @return
     */
    public static  Retrofit geteRetrofitByBaseUrl(Context context,String baseUrl){
        return  buildRetrofit(baseUrl,context);
    }


    public Retrofit getRetrofit() {
        if (retrofit == null)
            throw new RuntimeException("请先初始化ServiceClient！");
        return retrofit;
    }



    private static Retrofit buildRetrofit(String baseUrl, Context context) {
        //Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //设置Log显示的内容
        if (Settings.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        //给okhtttpclient添加log

        /**************https配置相关项***************************/
       /* int[] certificates = {};// int[] certificates = {R.raw.tomcat};设置证书的资源
        String hosts[] = {Constants.BASE_URL};
        OkHttpClient okHttpClient = new OkHttpClient.Builder().socketFactory(getSSLSocketFactory(context, certificates)).addInterceptor(logging).build();*/
        /*************https配置相关项****************************/
        //生成retrofit
        Retrofit retrofit = new Retrofit.Builder().client(getUnsafeOkHttpClient(logging))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(baseUrl).build();
        return retrofit;
    }

    /**
     * 获取证书工厂
     * @param context
     * @param certificates
     * @return
     */
    private static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            KeyStore keyStore = KeyStore.getInstance("BKS");

            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));

                if (certificate != null) {
                    certificate.close();
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (CertificateException e) {

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取http链接的客户端
     * @param logging
     * @return
     */
    private static OkHttpClient getUnsafeOkHttpClient(HttpLoggingInterceptor logging) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .addInterceptor(logging)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            //---------请求之前------------
                            Response response = chain.proceed(request);
                            //---------请求之后------------

                            return response;
                        }
                    })
                    .build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 创建API
     */
    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }





}
