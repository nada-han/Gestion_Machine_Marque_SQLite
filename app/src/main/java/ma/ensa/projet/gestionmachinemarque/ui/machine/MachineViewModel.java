package ma.ensa.projet.gestionmachinemarque.ui.machine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MachineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MachineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment des machines");
    }

    public LiveData<String> getText() {
        return mText;
    }
}