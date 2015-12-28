package com.example.flux.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flux.android.stores.Store;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import com.example.flux.android.actions.ActionsCreator;
import com.example.flux.android.dispatcher.Dispatcher;
import com.example.flux.android.stores.MessageStore;

/**
 * Flux的Controller-View模块
 * Created by ntop on 18/12/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText vMessageEditor;
    private Button vMessageButton;
    private TextView vMessageView;

    private Dispatcher dispatcher;
    private ActionsCreator actionsCreator;
    private MessageStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(store);
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get();
        actionsCreator = ActionsCreator.get(dispatcher);
        store = new MessageStore();
        dispatcher.register(store);
    }

    private void setupView() {
        vMessageEditor = (EditText) findViewById(R.id.message_editor);
        vMessageView = (TextView) findViewById(R.id.message_view);
        vMessageButton = (Button) findViewById(R.id.message_button);
        vMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.message_button) {
            if (vMessageEditor.getText() != null) {
                actionsCreator.sendMessage(vMessageEditor.getText().toString());
                vMessageEditor.setText(null);
            }
        }
    }

    private void render(MessageStore store) {
        vMessageView.setText(store.getMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();
        store.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        store.unregister(this);
    }

    @Subscribe
    public void onStoreChange(Store.StoreChangeEvent event) {
        render(store);
    }
}
