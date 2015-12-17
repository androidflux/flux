package com.example.flux.android.dispatcher;

import com.example.flux.android.actions.Action;
import com.example.flux.android.stores.Store;
import com.squareup.otto.Bus;

/**
 * Created by ntop on 18/12/15.
 */
public class Dispatcher {
    private final Bus bus;
    private static Dispatcher instance;

    public static Dispatcher get(Bus bus) {
        if (instance == null) {
            instance = new Dispatcher(bus);
        }
        return instance;
    }

    Dispatcher(Bus bus) {
        this.bus = bus;
    }

    public void register(final Object cls) {
        bus.register(cls);
    }

    public void unregister(final Object cls) {
        bus.unregister(cls);
    }

    public void emitChange(Store.StoreChangeEvent o) {
        post(o);
    }

    public void dispatch(Action action) {
        post(action);
    }

    private void post(final Object event) {
        bus.post(event);
    }
}
