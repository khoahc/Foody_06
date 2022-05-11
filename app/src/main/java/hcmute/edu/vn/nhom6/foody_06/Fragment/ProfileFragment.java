package hcmute.edu.vn.nhom6.foody_06.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import hcmute.edu.vn.nhom6.foody_06.Activity.ChangePasswordActivity;
import hcmute.edu.vn.nhom6.foody_06.Activity.InformationActivity;
import hcmute.edu.vn.nhom6.foody_06.Activity.SignInActivity;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactUser;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.ActivityChangePasswordBinding;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    User user;
    TransactUser transactUser;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        transactUser = (TransactUser) getActivity();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = transactUser.getDataUser();
        binding.textViewProfile.setText(user.getFullName());

        binding.textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        binding.textViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), InformationActivity.class);
                intent.putExtra("infoUser", user);
                startActivity(intent);
            }
        });

        binding.textViewChangePassword.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
            intent.putExtra("oPwd", user.getPassword());
            intent.putExtra("idUser", user.getId());
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        user = transactUser.getDataUser();
        binding.textViewProfile.setText(user.getFullName());
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
