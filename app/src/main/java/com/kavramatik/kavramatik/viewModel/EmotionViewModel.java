package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.EmotionModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class EmotionViewModel extends ViewModel {

    private final MutableLiveData<List<EmotionModel>> mutableLiveData;
    public MutableLiveData<Boolean> isError;
    public MutableLiveData<Boolean> isLoading;
    EducationAPI api;
    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    public EmotionViewModel() {
        isLoading = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        mutableLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        retrofit = EducationAPIService.getInstance();
    }

    public MutableLiveData<List<EmotionModel>> getDataAPI() {
        isLoading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getEmotions().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<EmotionModel>>() {
            @Override
            public void onSuccess(@NotNull List<EmotionModel> emotionModels) {
                isLoading.setValue(false);
                isError.setValue(false);
                mutableLiveData.setValue(emotionModels);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                isLoading.setValue(false);
                isError.setValue(true);
            }
        }));
        return mutableLiveData;
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
