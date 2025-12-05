package com.zelon.designpattern.behavioral.state;

/*
在一些游戏引擎、工作流等系统开发中，经常需要实现状态机，
状态机主要包含状态、事件、行为等三要素，主要有三种实现方式：
1. if-else 逻辑判断（将所有逻辑写在一个方法中，一般不用）
2. 查表（使用状态转化表和行为表来辅助转化，适合游戏这种状态复杂、但是行为简单的状态机）
3. 状态模式
 */

// 超级马里奥状态机--查表
public class MarioStateMachineV1 {
    private int score;
    private State currentState;
    private static final State[][] transitionTable;
    private static final int[][] actionTable;

    static {
        // transitionTable[stateCode][eventCode] = nextState
        // 不存在的情况可以使用原状态或其他默认值
        transitionTable = new State[][]{
                {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
                {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
                {State.CAPE, State.CAPE, State.CAPE, State.SMALL},
                {State.FIRE, State.FIRE, State.FIRE, State.SMALL}
        };
        // actionTable[stateCode][eventCode] = 奖励/惩罚积分
        actionTable = new int[][]{
                {100, 200, 300, 0},
                {0, 200, 300, -100},
                {0, 0, 0, -200},
                {0, 0, 0, -300}
        };
    }

    public MarioStateMachineV1(){
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom(){
        executeEvent(Event.GOT_MUSHROOM);
    }

    public void obtainFireFlower(){
        executeEvent(Event.GOT_FIRE);
    }

    public void obtainCape(){
        executeEvent(Event.GOT_CAPE);
    }

    public void meetMonster(){
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event){
        int stateCode = currentState.getStateCode();
        int eventCode = event.getEventCode();
        this.currentState = transitionTable[stateCode][eventCode];
        this.score += actionTable[stateCode][eventCode];
    }

    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }
}
enum State{
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private final int stateCode;
    private State(int stateCode){
        this.stateCode = stateCode;
    }

    public int getStateCode(){
        return this.stateCode;
    }
}

enum Event{
    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3);

    private final int eventCode;

    private Event(int eventCode){
        this.eventCode = eventCode;
    }

    public int getEventCode(){
        return this.eventCode;
    }
}