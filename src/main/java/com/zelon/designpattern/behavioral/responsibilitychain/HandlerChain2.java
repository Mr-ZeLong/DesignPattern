package com.zelon.designpattern.behavioral.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain2 {
    private List<Handler2> handlers = new ArrayList<>();

    public void addHandler(Handler2 handler){
        handlers.add(handler);
    }

    public void handle(){
        for(Handler2 handler : handlers){
            if(handler.handle()){
               break;
            }
        }
    }
}

interface Handler2 {
    boolean handle();
}

class Handle2A implements Handler2{

    @Override
    public boolean handle() {
        boolean isHandled = false;
        // 中间处理逻辑...
        return isHandled;
    }
}
class Handle2B implements Handler2{

    @Override
    public boolean handle() {
        boolean isHandled = false;
        // 中间处理逻辑...
        return isHandled;
    }
}