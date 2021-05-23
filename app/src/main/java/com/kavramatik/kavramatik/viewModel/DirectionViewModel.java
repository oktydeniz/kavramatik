package com.kavramatik.kavramatik.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.DirectionModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DirectionViewModel extends ViewModel {

    private final MutableLiveData<List<DirectionModel>> directionModel;
    public final MutableLiveData<Boolean> loading;
    private final CompositeDisposable compositeDisposable;
    EducationAPI api;
    private final Retrofit retrofit;
    public final MutableLiveData<Boolean> isFailed;

    public DirectionViewModel() {
        compositeDisposable = new CompositeDisposable();
        loading = new MutableLiveData<>();
        directionModel = new MutableLiveData<>();
        isFailed = new MutableLiveData<>();
        retrofit = EducationAPIService.getInstance();
    }

    public MutableLiveData<List<DirectionModel>> getDataAPI() {
        loading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getDirections().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DirectionModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<DirectionModel> directionModels) {
                        loading.setValue(false);
                        isFailed.setValue(false);
                        directionModel.setValue(directionModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.setValue(false);
                        isFailed.setValue(true);

                    }
                }));
        return directionModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
