package wzw.pikaqiu.net;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import wzw.pikaqiu.model.GankBeautyResult;

public interface Get {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number") int number, @Path("page") int page);
}