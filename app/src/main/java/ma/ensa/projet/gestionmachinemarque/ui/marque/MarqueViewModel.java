package ma.ensa.projet.gestionmachinemarque.ui.marque;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MarqueViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MarqueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment des marques");
    }

    public LiveData<String> getText() {
        return mText;
    }
}