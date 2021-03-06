// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.extensions;

import com.intellij.openapi.Disposable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ExtensionPoint<T> {
  @NotNull
  String getName();

  AreaInstance getArea();

  void registerExtension(@NotNull T extension);

  void registerExtension(@NotNull T extension, @NotNull LoadingOrder order);

  @NotNull
  T[] getExtensions();

  @NotNull
  List<T> getExtensionList();

  boolean hasAnyExtensions();

  @Nullable
  T getExtension();

  boolean hasExtension(@NotNull T extension);

  void unregisterExtension(@NotNull T extension);

  /**
   * Unregisters an extension of the specified type.
   */
  void unregisterExtension(@NotNull Class<? extends T> extensionClass);

  void addExtensionPointListener(@NotNull ExtensionPointListener<T> listener, @NotNull Disposable parentDisposable);

  void addExtensionPointListener(@NotNull ExtensionPointListener<T> listener);

  void removeExtensionPointListener(@NotNull ExtensionPointListener<T> extensionPointListener);

  void reset();

  @NotNull
  Class<T> getExtensionClass();

  @NotNull
  Kind getKind();

  @NotNull
  String getClassName();

  enum Kind {INTERFACE, BEAN_CLASS}
}
