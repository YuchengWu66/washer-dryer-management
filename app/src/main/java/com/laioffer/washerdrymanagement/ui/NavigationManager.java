package com.laioffer.washerdrymanagement.ui;

import androidx.fragment.app.Fragment;

public interface NavigationManager {

    void navigateTo(Fragment fragment);
    void navigateToWithAnimation(Fragment fragment);
    void navigateWithFragmentDestroy(Fragment target, Fragment current);
    void goBack();
}