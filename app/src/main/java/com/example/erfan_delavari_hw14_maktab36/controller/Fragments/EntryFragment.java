package com.example.erfan_delavari_hw14_maktab36.controller.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erfan_delavari_hw14_maktab36.R;
import com.example.erfan_delavari_hw14_maktab36.controller.Activity.AdminActivity;
import com.example.erfan_delavari_hw14_maktab36.controller.Activity.SearchActivity;
import com.example.erfan_delavari_hw14_maktab36.controller.Activity.TaskPagerActivity;
import com.example.erfan_delavari_hw14_maktab36.model.User;
import com.example.erfan_delavari_hw14_maktab36.repository.UserDBRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class EntryFragment extends Fragment {

    public static final String TAG_DIALOG_SIGN_UP = "DialogSignUp";
    public static final int REQUEST_CODE_SIGN_UP = 13;

    private UserDBRepository mRepository;

    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private FloatingActionButton mButtonDone;
    private FloatingActionButton mButtonSignUp;

    public static EntryFragment newInstance() {
        return new EntryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRepository = UserDBRepository.getInstance(Objects.requireNonNull(getContext()));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_entry_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_admin_item:
                startActivity(AdminActivity.newIntent(getActivity()));
                return true;
            case R.id.menu_search_item:
                startActivity(SearchActivity.newIntent(getContext()));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        findViews(view);
        listeners();
        return view;
    }

    private void listeners() {

        mButtonDone.setOnClickListener(v -> {
            boolean userFound = false;
            for (User user : mRepository.getUserList()) {
                if (user.loginCheck(mEditTextUserName.getText().toString(), mEditTextPassword.getText().toString())) {
                    Intent intent = TaskPagerActivity.newIntent(getActivity(), user.getUUID());
                    mEditTextPassword.setText("");
                    mEditTextUserName.setText("");
                    startActivity(intent);
                    userFound = true;
                }
            }
            if (!userFound)
                Toast.makeText(getActivity(), R.string.user_not_found, Toast.LENGTH_SHORT).show();
        });
        mButtonSignUp.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                DialogSignUpFragment dialogSignUpFragment =
                        DialogSignUpFragment.newInstance(mEditTextUserName.getText().toString()
                                , mEditTextPassword.getText().toString());
                dialogSignUpFragment.setTargetFragment(EntryFragment.this, REQUEST_CODE_SIGN_UP);
                dialogSignUpFragment.show(getFragmentManager(), TAG_DIALOG_SIGN_UP);
            }
        });
    }

    private void findViews(View view) {
        mEditTextUserName = view.findViewById(R.id.entry_edit_text_user_name);
        mEditTextPassword = view.findViewById(R.id.entry_edit_text_password);
        mButtonDone = view.findViewById(R.id.entry_button_done);
        mButtonSignUp = view.findViewById(R.id.button_sign_up);
    }
}