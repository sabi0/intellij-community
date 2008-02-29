/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.execution.runners;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.JDOMExternalizable;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProgramRunner<Settings extends JDOMExternalizable> {
  ExtensionPointName<ProgramRunner> PROGRAM_RUNNER_EP = ExtensionPointName.create("com.intellij.programRunner");

  interface Callback {
    void processStarted(RunContentDescriptor descriptor);
  }

  @NotNull @NonNls
  String getRunnerId();

  boolean canRun(@NotNull final String executorId, @NotNull final RunProfile profile);

  Settings createConfigurationData(ConfigurationInfoProvider settingsProvider);

  void checkConfiguration(RunnerSettings settings, ConfigurationPerRunnerSettings configurationPerRunnerSettings)
    throws RuntimeConfigurationException;

  void onProcessStarted(RunnerSettings settings, ExecutionResult executionResult);

  AnAction[] createActions(ExecutionResult executionResult);

  SettingsEditor<Settings> getSettingsEditor(final Executor executor, RunConfiguration configuration);

  void execute(@NotNull final Executor executor, @NotNull RunProfile profile,
               @NotNull DataContext dataContext,
               @Nullable RunnerSettings settings,
               ConfigurationPerRunnerSettings configurationSettings) throws ExecutionException;

  void execute(@NotNull final Executor executor, @NotNull RunProfile profile,
               @NotNull DataContext dataContext,
               @Nullable RunnerSettings settings,
               ConfigurationPerRunnerSettings configurationSettings,
               @Nullable final Callback callback) throws ExecutionException;
}