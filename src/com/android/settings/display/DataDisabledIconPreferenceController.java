/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

public class DataDisabledIconPreferenceController extends AbstractPreferenceController
        implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {

    private static final String KEY_DATA_DISABLED_ICON = "data_disabled_icon";

    public DataDisabledIconPreferenceController(Context context) {
        super(context);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getPreferenceKey() {
        return KEY_DATA_DISABLED_ICON;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (Boolean) newValue;
        Settings.System.putInt(mContext.getContentResolver(),
                KEY_DATA_DISABLED_ICON, value ? 1 : 0);
        return true;
    }

    @Override
    public void updateState(Preference preference) {
        int value = Settings.System.getInt(mContext.getContentResolver(),
                KEY_DATA_DISABLED_ICON, 1);
        ((TwoStatePreference) preference).setChecked(value != 0);
    }
}
