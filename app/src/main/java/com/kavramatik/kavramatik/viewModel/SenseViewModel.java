package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kavramatik.kavramatik.model.SenseModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SenseViewModel extends ViewModel {

    public MutableLiveData<List<SenseModel>> listMutableLiveData;
    public MutableLiveData<Boolean> isLoading;
    public MutableLiveData<Boolean> isError;
    EducationAPI api;
    private final CompositeDisposable compositeDisposable;
    private final Retrofit retrofit;

    public SenseViewModel() {
        listMutableLiveData = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        retrofit = EducationAPIService.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<SenseModel>> getDataAPI() {
        api = retrofit.create(EducationAPI.class);
        isLoading.setValue(true);
        compositeDisposable.add(api.getSenses().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<SenseModel>>() {

            @Override
            public void onSuccess(@NotNull List<SenseModel> senseModels) {
                isError.setValue(false);
                isLoading.setValue(false);
                listMutableLiveData.setValue(senseModels);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                isError.setValue(true);
                isLoading.setValue(false);
            }
        }));
        return listMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
