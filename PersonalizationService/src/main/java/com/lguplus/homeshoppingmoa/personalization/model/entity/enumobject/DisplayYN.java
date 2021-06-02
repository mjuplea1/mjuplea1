package com.lguplus.homeshoppingmoa.personalization.model.entity.enumobject;

public enum DisplayYN {

    Y("노출", true),
    N("비노출", false);

    private String displayStatus;
    private boolean displayFlag;

    DisplayYN(String displayStatus, boolean displayFlag) {
        this.displayStatus = displayStatus;
        this.displayFlag = displayFlag;
    }

    public boolean value() {
        return displayFlag;
    }
}
