package ma.ensa.projet.gestionmachinemarque.ui.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LogoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment de deconnexion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}