package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.DimensionModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DimensionViewModel extends ViewModel {
    private final Retrofit retrofit;
    EducationAPI api;
    private final MutableLiveData<List<DimensionModel>> listMutableLiveData;
    public MutableLiveData<Boolean> isError;
    public MutableLiveData<Boolean> isLoading;
    private final CompositeDisposable disposable;

    public DimensionViewModel() {
        retrofit = EducationAPIService.getInstance();
        disposable = new CompositeDisposable();
        listMutableLiveData = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    public MutableLiveData<List<DimensionModel>> getDataAPI() {
        isLoading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        disposable.add(api.getDimensions().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<DimensionModel>>() {
                    @Override
                    public void onSuccess(@NotNull List<DimensionModel> dimensionModels) {
                        isError.setValue(false);
                        isLoading.setValue(false);
                        listMutableLiveData.setValue(dimensionModels);
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
        disposable.clear();
    }
}
