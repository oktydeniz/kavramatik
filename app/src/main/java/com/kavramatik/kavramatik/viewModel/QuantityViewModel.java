package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.QuantityModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class QuantityViewModel extends ViewModel {

    public final MutableLiveData<List<QuantityModel>> listMutableLiveData;
    public MutableLiveData<Boolean> isError;
    public MutableLiveData<Boolean> isLoading;
    EducationAPI api;
    private final CompositeDisposable compositeDisposable;
    private final Retrofit retrofit;

    public QuantityViewModel() {
        isLoading = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        retrofit = EducationAPIService.getInstance();
    }

    public MutableLiveData<List<QuantityModel>> getDataAPI() {
        isLoading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getQuantities().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<QuantityModel>>() {

            @Override
            public void onSuccess(@NotNull List<QuantityModel> quantityModels) {
                isError.setValue(false);
                isLoading.setValue(false);
                listMutableLiveData.setValue(quantityModels);
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
