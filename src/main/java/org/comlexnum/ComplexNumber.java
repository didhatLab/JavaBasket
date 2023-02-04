package org.comlexnum;

import java.awt.image.CropImageFilter;

public class ComplexNumber {
    private final double real;
    private final double image;

    public <T extends Number> ComplexNumber(T real, T image){
        this.real = real.doubleValue();
        this.image = image.doubleValue();
    }

    public <T extends Number> ComplexNumber(T real){
        this.real = real.doubleValue();
        this.image = 0;
    }


    public ComplexNumber append(ComplexNumber num){
        return new ComplexNumber(this.real + num.real, this.image + num.image);
    }

    public <T extends Number> ComplexNumber append(T num){
        return new ComplexNumber(this.real + num.doubleValue(), this.image);
    }

    public ComplexNumber subtract(ComplexNumber num){
        return new ComplexNumber(this.real - num.real, num.image);
    }

    public <T extends Number> ComplexNumber subtract(T num){
        return new ComplexNumber(this.real - num.doubleValue(), this.image);
    }

    public ComplexNumber multiply(ComplexNumber num){
        double new_real = (this.real*num.real - this.image*num.image);
        double new_image = (this.real*num.image + num.real*this.image);

        return new ComplexNumber(new_real, new_image);
    }

    public String toString(){
        if (this.image == 0){
            return Double.toString(this.real);
        } else {
            return String.format("%f+%fi", this.real, this.image);
        }
    }


}
