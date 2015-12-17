package com.example.flux.android.stores;

import com.example.flux.android.actions.Action;
import com.example.flux.android.actions.MessageAction;
import com.example.flux.android.dispatcher.Dispatcher;
import com.example.flux.android.model.Message;
import com.squareup.otto.Subscribe;

/**
 * Created by ntop on 18/12/15.
 */
public class MessageStore extends Store {
    private static MessageStore singleton;
    private Message mMessage = new Message();

    protected MessageStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static MessageStore get(Dispatcher dispatcher) {
        if (singleton == null) {
            singleton = new MessageStore(dispatcher);
        }
        return singleton;
    }

    public String getMessage() {
        return mMessage.getMessage();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case MessageAction.ACTION_NEW_MESSAGE:
                mMessage.setMessage((String) action.getData());
                break;
            default:
        }
        emitStoreChange();
    }


    @Override
    StoreChangeEvent changeEvent() {
        return new MessageStoreChangeEvent();
    }

    public class MessageStoreChangeEvent implements StoreChangeEvent {
    }
}
