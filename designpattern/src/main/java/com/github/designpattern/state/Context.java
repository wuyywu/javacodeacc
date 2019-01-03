package com.github.designpattern.state;


/**
 * Context类，维护一个ConcreteState子类的实例，这个实例定义当前的状态
 *
 * @author wuyun
 * @date 2019/1/1 17:49
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request(){
        this.state.handle(this);
    }
}
