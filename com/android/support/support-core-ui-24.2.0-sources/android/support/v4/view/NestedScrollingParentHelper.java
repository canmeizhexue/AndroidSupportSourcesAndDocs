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


package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

/**构造函数里面初始化这个帮助类，兼容android5.0以前
 * 这里都是代理方法，从NestedScrollingParent的相关方法代理到这个类的相应方法
 * Helper class for implementing nested scrolling parent views compatible with Android platform
 * versions earlier than Android 5.0 Lollipop (API 21).
 *
 * <p>{@link android.view.ViewGroup ViewGroup} subclasses should instantiate a final instance
 * of this class as a field at construction. For each <code>ViewGroup</code> method that has
 * a matching method signature in this class, delegate the operation to the helper instance
 * in an overridden method implementation. This implements the standard framework policy
 * for nested scrolling.</p>
 *
 * <p>Views invoking nested scrolling functionality should always do so from the relevant
 * {@link android.support.v4.view.ViewCompat}, {@link android.support.v4.view.ViewGroupCompat} or
 * {@link android.support.v4.view.ViewParentCompat} compatibility
 * shim static methods. This ensures interoperability with nested scrolling views on Android
 * 5.0 Lollipop and newer.</p>
 */
public class NestedScrollingParentHelper {
    private final ViewGroup mViewGroup;
    private int mNestedScrollAxes;

    /**
     * Construct a new helper for a given ViewGroup
     */
    public NestedScrollingParentHelper(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    /**NestedScrollingParent接受NestedScrollingChild发起的嵌套滑动，
     * Called when a nested scrolling operation initiated by a descendant view is accepted
     * by this ViewGroup.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     */
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mNestedScrollAxes = axes;
    }

    /**返回当前嵌套滑动的轴，水平轴、垂直轴，或者俩者都有
     * Return the current axes of nested scrolling for this ViewGroup.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     */
    public int getNestedScrollAxes() {
        return mNestedScrollAxes;
    }

    /**
     * React to a nested scroll operation ending.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     *
     * @param target View that initiated the nested scroll
     */
    public void onStopNestedScroll(View target) {
        mNestedScrollAxes = 0;
    }
}
