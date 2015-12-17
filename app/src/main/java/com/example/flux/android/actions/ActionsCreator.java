package com.example.flux.android.actions;

import com.example.flux.android.dispatcher.Dispatcher;

/**
 * Created by ntop on 18/12/15.
 */
public class ActionsCreator {

    private static ActionsCreator instance;
    final Dispatcher dispatcher;

    ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    public void sendMessage(String message) {
        dispatcher.dispatch(new MessageAction(MessageAction.ACTION_NEW_MESSAGE, message));
    }
}
