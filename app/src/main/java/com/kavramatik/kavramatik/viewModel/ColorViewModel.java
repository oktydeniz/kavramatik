package com.kavramatik.kavramatik.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.ColorModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ColorViewModel extends ViewModel {
    private final MutableLiveData<List<ColorModel>> colorModel;
    public MutableLiveData<Boolean> loading;
    private final CompositeDisposable compositeDisposable;
    private final Retrofit retrofit;
    EducationAPI api;

    public ColorViewModel() {
        this.colorModel = new MutableLiveData<>();
        this.loading = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        retrofit = EducationAPIService.getInstance();

    }

    public MutableLiveData<List<ColorModel>> getDataAPI() {
        loading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getColors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ColorModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<ColorModel> colorModels) {
                        colorModel.setValue(colorModels);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                })
        );
        return colorModel;
    }

    public void destroy() {
        compositeDisposable.clear();
    }
}
