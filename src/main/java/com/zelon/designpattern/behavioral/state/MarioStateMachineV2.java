package com.zelon.designpattern.behavioral.state;

/*
状态模式
一、使用场景
对于一些状态不是特别多，但是状态改变时，事件处理较为复杂的场景如订单状态改变，
需要改数据库、向消息队列发送消息，可以使用状态模式实现。

二、实现方式
1. 将状态和处理逻辑从状态机中分离，为每个状态设置状态类，其中只包含对应的处理逻辑，
没有成员变量，因此可以设置为单例模式，但需要将状态机对象传入处理方法，以便修改状态数据。
2. 状态机需要记录当前状态以及其他变量，在处理事件时，将请求委托给状态对象，而不是自己实现。
 */

public class MarioStateMachineV2 {
    private int score;
    private IMario curState;

    public MarioStateMachineV2(){
        this.score = 0;
        this.curState = new SmallMario();
    }

    public void obtainMushRoom(){
        curState.obtainMushRoom(this);
    }

    public void obtainCape(){
        curState.obtainCape(this);
    }

    public void obtainFireFlower(){
        curState.obtainFireFlower(this);
    }

    public void meetMonster(){
        curState.meetMonster(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setCurState(IMario curState){
        this.curState = curState;
    }
}

interface IMario{
    void obtainMushRoom(MarioStateMachineV2 marioStateMachine);
    void obtainCape(MarioStateMachineV2 marioStateMachine);
    void obtainFireFlower(MarioStateMachineV2 marioStateMachine);
    void meetMonster(MarioStateMachineV2 marioStateMachine);
}

class SmallMario implements IMario{
    private static final SmallMario instance = new SmallMario();

    @Override
    public void obtainMushRoom(MarioStateMachineV2 marioStateMachine) {
        int score = marioStateMachine.getScore();
        // 变更状态机中的数据和当前状态
        marioStateMachine.setScore(score + 100);
        marioStateMachine.setCurState(SuperMario.getInstance());
    }

    @Override
    public void obtainCape(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    @Override
    public void obtainFireFlower(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    @Override
    public void meetMonster(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    public static SmallMario getInstance(){
        return instance;
    }
}

class SuperMario implements IMario{
    private static final SuperMario instance = new SuperMario();

    @Override
    public void obtainMushRoom(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    @Override
    public void obtainCape(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    @Override
    public void obtainFireFlower(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    @Override
    public void meetMonster(MarioStateMachineV2 marioStateMachine) {
        // 状态转移行为...
    }

    public static SuperMario getInstance(){
        return instance;
    }
}