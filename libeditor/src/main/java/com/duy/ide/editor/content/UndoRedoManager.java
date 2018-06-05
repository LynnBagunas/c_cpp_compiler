/*
 * Copyright 2018 Mr Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duy.ide.editor.content;

import android.content.Context;
import android.content.SharedPreferences;
import com.duy.ide.editor.core.widget.BaseEditorView;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by Duy on 26-Apr-18.
 */

public class UndoRedoManager {
    private Context context;

    public UndoRedoManager(Context context) {
        this.context = context;
    }

    @Nullable
    public UndoRedoHelper getUndoRedoStateFor(BaseEditorView textView,File file) {
        if (!file.isFile()) {
            return null;
        }
        UndoRedoHelper undoRedoHelper = new UndoRedoHelper(textView);
        SharedPreferences history = getHistory(file);
        undoRedoHelper.restorePersistentState(history, "");
        return undoRedoHelper;
    }

    private SharedPreferences getHistory(File file) {
        String path = file.getName();
        return context.getSharedPreferences(path, Context.MODE_PRIVATE);
    }
}
