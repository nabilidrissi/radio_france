// Generated by view binder compiler. Do not edit!
package com.nabilRadioFrance.radio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.nabilRadioFrance.radio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityListeRadioBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout radios;

  @NonNull
  public final RecyclerView radiosview;

  private ActivityListeRadioBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout radios, @NonNull RecyclerView radiosview) {
    this.rootView = rootView;
    this.radios = radios;
    this.radiosview = radiosview;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityListeRadioBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityListeRadioBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_liste_radio, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityListeRadioBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout radios = (ConstraintLayout) rootView;

      id = R.id.radiosview;
      RecyclerView radiosview = ViewBindings.findChildViewById(rootView, id);
      if (radiosview == null) {
        break missingId;
      }

      return new ActivityListeRadioBinding((ConstraintLayout) rootView, radios, radiosview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
