/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.setupwizardlib.util;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.android.setupwizardlib.R;
import com.android.setupwizardlib.SetupWizardLayout;

/**
 * This layout helper works with SetupWizardLayout and manages the templates for suw_template and
 * its variants to perform some common operations.
 *
 * Layouts using custom templates can implement their own LayoutHelpers to perform layout specific
 * tasks for the template.
 */
public class SetupWizardLayoutHelper {

    /**
     * Create a SetupWizardLayoutHelper from a view in the activity. If the viewId is not found, or
     * if that view is not an instance of SetupWizardLayout, this method will return null.
     *
     * @return An instance of SetupWizardLayoutHelper, or null if viewId is not found or is not an
     *         instance of SetupWizardLayout.
     */
    public static SetupWizardLayoutHelper from(Activity activity, int viewId) {
        View view = activity.findViewById(viewId);
        if (view instanceof SetupWizardLayout) {
            return new SetupWizardLayoutHelper((SetupWizardLayout) view);
        }
        return null;
    }

    private SetupWizardLayout mLayout;

    public SetupWizardLayoutHelper(SetupWizardLayout layout) {
        mLayout = layout;
    }

    public SetupWizardLayout getLayout() {
        return mLayout;
    }

    public void setHeaderText(int title) {
        TextView titleView = (TextView) mLayout.findViewById(R.id.suw_layout_title);
        if (titleView != null) {
            titleView.setText(title);
        }
    }

    public void setHeaderText(CharSequence title) {
        TextView titleView = (TextView) mLayout.findViewById(R.id.suw_layout_title);
        if (titleView != null) {
            titleView.setText(title);
        }
    }
}