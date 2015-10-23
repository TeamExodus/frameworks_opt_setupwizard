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

package com.android.setupwizardlib.test;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.setupwizardlib.R;
import com.android.setupwizardlib.items.Item;

public class ItemTest extends AndroidTestCase {

    private TextView mTitleView;
    private TextView mSummaryView;
    private ImageView mIconView;
    private FrameLayout mIconContainer;

    @SmallTest
    public void testOnBindView() {
        Item item = new Item();
        item.setTitle("TestTitle");
        item.setSummary("TestSummary");
        Drawable icon = new ShapeDrawable();
        item.setIcon(icon);
        View view = createLayout();

        item.onBindView(view);

        assertEquals("Title should be \"TestTitle\"", "TestTitle", mTitleView.getText().toString());
        assertEquals("Summary should be \"TestSummary\"", "TestSummary",
                mSummaryView.getText().toString());
        assertSame("Icon should be the icon shape drawable", icon, mIconView.getDrawable());
    }

    @SmallTest
    public void testSingleLineItem() {
        Item item = new Item();
        item.setTitle("TestTitle");
        View view = createLayout();

        item.onBindView(view);

        assertEquals("Title should be \"TestTitle\"", "TestTitle", mTitleView.getText().toString());
        assertEquals("Summary should be gone", View.GONE, mSummaryView.getVisibility());
        assertEquals("IconContainer should be gone", View.GONE, mIconContainer.getVisibility());
    }

    @SmallTest
    public void testProperties() {
        Item item = new Item();
        item.setTitle("TestTitle");
        item.setSummary("TestSummary");
        item.setEnabled(false);
        ShapeDrawable icon = new ShapeDrawable();
        item.setIcon(icon);
        item.setId(12345);
        item.setLayoutResource(56789);

        assertEquals("Title should be \"TestTitle\"", "TestTitle", item.getTitle());
        assertEquals("Summary should be \"TestSummary\"", "TestSummary", item.getSummary());
        assertFalse("Enabled should be false", item.isEnabled());
        assertSame("Icon should be same as set", icon, item.getIcon());
        assertEquals("ID should be 12345", 12345, item.getId());
        assertEquals("Layout resource should be 56789", 56789, item.getLayoutResource());
    }

    @SmallTest
    public void testDefaultValues() {
        Item item = new Item();

        assertNull("Default title should be null", item.getTitle());
        assertNull("Default summary should be null", item.getSummary());
        assertNull("Default icon should be null", item.getIcon());
        assertTrue("Default enabled should be true", item.isEnabled());
        assertEquals("Default ID should be 0", 0, item.getId());
        assertEquals("Default layout resource should be R.layout.suw_items_text",
                R.layout.suw_items_default, item.getLayoutResource());
    }

    @SmallTest
    public void testHierarchyImplementation() {
        Item item = new Item();
        item.setId(12345);

        assertEquals("getCount should be 1", 1, item.getCount());
        assertSame("getItemAt should return itself", item, item.getItemAt(0));
        assertSame("findItemById with same ID should return itself", item,
                item.findItemById(12345));
        assertNull("findItemById with different ID should return null", item.findItemById(34567));
    }

    private ViewGroup createLayout() {
        ViewGroup root = new FrameLayout(mContext);

        mTitleView = new TextView(mContext);
        mTitleView.setId(R.id.suw_items_title);
        root.addView(mTitleView);

        mSummaryView = new TextView(mContext);
        mSummaryView.setId(R.id.suw_items_summary);
        root.addView(mSummaryView);

        mIconContainer = new FrameLayout(mContext);
        mIconContainer.setId(R.id.suw_items_icon_container);
        root.addView(mIconContainer);

        mIconView = new ImageView(mContext);
        mIconView.setId(R.id.suw_items_icon);
        mIconContainer.addView(mIconView);

        return root;
    }
}