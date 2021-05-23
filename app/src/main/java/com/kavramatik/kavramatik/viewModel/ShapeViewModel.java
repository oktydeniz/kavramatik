package com.kavramatik.kavramatik.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.ShapeModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ShapeViewModel extends ViewModel {
    private final MutableLiveData<List<ShapeModel>> shapeModel;
    public MutableLiveData<Boolean> loading;
    private final CompositeDisposable compositeDisposable;
    public MutableLiveData<Boolean> error;

    private final Retrofit retrofit;
    EducationAPI api;

    public ShapeViewModel() {
        compositeDisposable = new CompositeDisposable();
        loading = new MutableLiveData<>();
        error = new MutableLiveData<>();
        shapeModel = new MutableLiveData<>();
        retrofit = EducationAPIService.getInstance();
    }

    public MutableLiveData<List<ShapeModel>> getDataAPI() {
        loading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getShapes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ShapeModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<ShapeModel> shapeModels) {
                        loading.setValue(false);
                        error.setValue(false);
                        shapeModel.setValue(shapeModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.setValue(false);
                        error.setValue(true);

                    }
                })
        );
        return shapeModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
