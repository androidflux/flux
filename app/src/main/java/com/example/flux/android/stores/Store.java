package com.example.flux.android.stores;

import com.example.flux.android.actions.Action;
import com.example.flux.android.dispatcher.Dispatcher;

/**
 * Created by ntop on 18/12/15.
 */
public abstract class Store {

    final Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

    public interface StoreChangeEvent {}
}
