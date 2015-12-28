package com.example.flux.android.stores;

import com.example.flux.android.actions.Action;
import com.example.flux.android.dispatcher.Dispatcher;
import com.squareup.otto.Bus;

/**
 * Flux的Store模块
 * Created by ntop on 18/12/15.
 */
public abstract class Store {
    private  static final Bus bus = new Bus();

    protected Store() {
    }

    public void register(final Object view) {
        this.bus.register(view);
    }

    public void unregister(final Object view) {
        this.bus.unregister(view);
    }

    void emitStoreChange() {
        this.bus.post(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

    public class StoreChangeEvent {}
}
