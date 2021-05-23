package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.OppositesModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OppositesViewModel extends ViewModel {
    public MutableLiveData<List<OppositesModel>> oppositeLiveData;
    public MutableLiveData<Boolean> isError;
    public MutableLiveData<Boolean> isLoading;
    EducationAPI api;
    private final Retrofit retrofit;
    public CompositeDisposable compositeDisposable;

    public OppositesViewModel() {
        retrofit = EducationAPIService.getInstance();
        compositeDisposable = new CompositeDisposable();
        isLoading = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        oppositeLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<OppositesModel>> getDataAPI() {
        api = retrofit.create(EducationAPI.class);
        isLoading.setValue(true);
        compositeDisposable.add(api.getOpposites().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<OppositesModel>>() {
            @Override
            public void onSuccess(@NotNull List<OppositesModel> oppositesModels) {
                isError.setValue(false);
                isLoading.setValue(false);
                oppositeLiveData.setValue(oppositesModels);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                isLoading.setValue(false);
                isError.setValue(true);
            }
        }));
        // compositeDisposable.add(api.getOpposites().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handlerResponsive));
        return oppositeLiveData;
    }

    /*private void handlerResponsive(List<OppositesModel> oppositesModels) {
        if(!oppositesModels.isEmpty()){
            oppositeLiveData.setValue(oppositesModels);
            isLoading.setValue(false);
            isError.setValue(false);
        }else{
            isLoading.setValue(false);
            isError.setValue(true);
        }
    }*/

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

