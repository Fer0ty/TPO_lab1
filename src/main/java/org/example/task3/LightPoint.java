package org.example.task3;

public class LightPoint {
    private String position;
    private String action;

    private String lastPosition;

    public LightPoint(String position){
        this.position = position;
    }

    private void setLastPosition(String lastPosition){
        this.lastPosition = lastPosition;
    }

    public void setPosition(String position){
        setLastPosition(this.position);
        this.position = position;
        System.out.println("Точка света поменяла свою позицию с "+getLastPosition()+" на "+getPosition());
    }

    public void setAction(String action){
        this.action = action;
        System.out.println("Точка света " + getAction());
    }

    public String getPosition(){
        return position;
    }
    public String getAction(){
        return action;
    }
    public String getLastPosition(){
        return lastPosition;
    }
}
