package org.messagebus;

import org.comlexnum.ComplexNumber;
import org.comlexnum.Matrix;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<String, ComplexNumber> numVariables;
    Map<String, Matrix> matrixVariables;


    public Storage(){
        this.numVariables = new HashMap<String, ComplexNumber>();
        this.matrixVariables = new HashMap<String, Matrix>();
    }

    public void setNumVariable(String name, ComplexNumber number){
        if (numVariables.containsKey(name)){
            return;
        }
        this.numVariables.put(name, number);
    }

    public void setMatrixVariable(String name, Matrix matrix){
        if (matrixVariables.containsKey(name)){
            return;
        }

        this.matrixVariables.put(name, matrix);

    }

    public ComplexNumber getNumber(String name){
        if (!numVariables.containsKey(name)){
            return null;
        }
        return this.numVariables.get(name);
    }

    public Matrix getMatrix(String name){
        if (!matrixVariables.containsKey(name)){
            return null;
        }

        return this.matrixVariables.get(name);
    }


}
