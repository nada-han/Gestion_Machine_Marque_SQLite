package ma.ensa.projet.gestionmachinemarque.ui.marque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import ma.ensa.projet.gestionmachinemarque.databinding.FragmentMarqueBinding;

public class MarqueFragment extends Fragment {

    private FragmentMarqueBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MarqueViewModel homeViewModel =
                new ViewModelProvider(this).get(MarqueViewModel.class);

        binding = FragmentMarqueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMarque;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}