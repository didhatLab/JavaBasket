package org.comlexnum;


class RealNumber {
    double real_number;

    public <T extends Number> RealNumber(T number){
        this.real_number = number.doubleValue();
    }

}
