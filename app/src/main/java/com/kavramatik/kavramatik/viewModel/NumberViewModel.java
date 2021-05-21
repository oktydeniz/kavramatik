package com.kavramatik.kavramatik.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kavramatik.kavramatik.model.NumberModel;
import com.kavramatik.kavramatik.service.EducationAPI;
import com.kavramatik.kavramatik.service.EducationAPIService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NumberViewModel extends ViewModel {
    private final MutableLiveData<List<NumberModel>> numberModel;
    public MutableLiveData<Boolean> isError;
    public MutableLiveData<Boolean> loading;
    private final CompositeDisposable compositeDisposable;
    EducationAPI api;
    private final Retrofit retrofit;

    public NumberViewModel() {
        numberModel = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        retrofit = EducationAPIService.getInstance();
        compositeDisposable = new CompositeDisposable();

    }

    public MutableLiveData<List<NumberModel>> getDataAPI() {
        loading.setValue(true);
        api = retrofit.create(EducationAPI.class);
        compositeDisposable.add(api.getNumbers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<NumberModel>>() {

                    @Override
                    public void onSuccess(@NotNull List<NumberModel> numberModels) {
                        loading.setValue(false);
                        isError.setValue(false);
                        numberModel.setValue(numberModels);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        loading.setValue(false);
                        isError.setValue(true);
                    }
                }));
        return numberModel;
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
