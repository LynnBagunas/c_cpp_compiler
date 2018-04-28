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

package com.jecelyin.editor.v2.ui.diagnostic;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duy.ccppcompiler.compiler.diagnostic.Diagnostic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duy on 28-Apr-18.
 */

public class DiagnosticView implements DiagnosticContract.View, DiagnosticClickListener {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private DiagnosticContract.Presenter mPresenter;
    private DiagnosticAdapter mAdapter;

    public DiagnosticView(RecyclerView recyclerView, Context context, DiagnosticContract.Presenter presenter) {
        this.mRecyclerView = recyclerView;
        this.mContext = context;
        mPresenter = presenter;
        init();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new DiagnosticAdapter(new ArrayList<Diagnostic>(), mContext);
        mAdapter.setDiagnosticClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void show(List<Diagnostic> diagnostics) {
        clear();
        addAll(diagnostics);
    }

    private void addAll(List<Diagnostic> diagnostics) {
        mAdapter.addAll(diagnostics);
    }

    @Override
    public void remove(Diagnostic diagnostic) {
        mAdapter.remove(diagnostic);
    }

    @Override
    public void add(Diagnostic diagnostic) {
        mAdapter.add(diagnostic);
    }

    @Override
    public void clear() {
        mAdapter.clear();
    }

    @Override
    public void onDiagnosisClick(Diagnostic diagnostic, View view) {
        mPresenter.onDiagnosticClick(view, diagnostic);
    }
}
