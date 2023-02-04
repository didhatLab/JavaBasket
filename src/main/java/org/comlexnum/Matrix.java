package org.comlexnum;

import javax.swing.table.TableRowSorter;
import java.io.UncheckedIOException;
import java.util.Arrays;

public class Matrix {
    private int n_size;
    private int m_size;
    private ComplexNumber[][] matrix;



    public Matrix(int n, int m){
        this.n_size = n;
        this.m_size = m;
        this.matrix = new ComplexNumber[n][m];
        this.fill_zeros(this.matrix);
    }

    public void set_value(int x, int y, ComplexNumber num){
        this.matrix[x][y] = num;
    }

    public <T extends Number> void set_value(int x, int y, T num){
        this.matrix[x][y] = new ComplexNumber(num);
    }


    public Matrix plus(Matrix other){
        if (!is_plus_available(this, other)){
            throw new RuntimeException();
        }
        Matrix new_matrix = new Matrix(this.n_size, this.m_size);
        for (int i = 0; i < this.n_size; i++){
            for (int j = 0; j < this.m_size; j++){
                new_matrix.set_value(i, j, this.matrix[i][j].append(other.matrix[i][j]));
            }
        }
        return new_matrix;
    }

    public Matrix mull(Matrix other) {
        if (!this.is_mull_available(this, other)){
            throw new RuntimeException();
        }
        Matrix new_matrix = new Matrix(this.n_size, other.m_size);

        for (int i = 0; i < this.n_size; ++i){
            for (int j = 0; j < other.m_size; ++j){

                ComplexNumber new_value = new ComplexNumber(0);
                for (int k = 0; k < this.m_size; ++k){
                    new_value = new_value.append(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
                new_matrix.set_value(i, j, new_value);
            }
        }


        return new_matrix;

    }

    public void transport(){
        ComplexNumber[][] new_matrix = new ComplexNumber[this.m_size][this.n_size];

        for (int i = 0; i < this.n_size; i++) {
            for (int j = 0; j < this.m_size; j++) {
                new_matrix[j][i] = this.matrix[i][j];
            }
        }
        int temp = this.n_size;
        this.n_size = this.m_size;
        this.m_size = temp;
        this.matrix = new_matrix;

    }

    public ComplexNumber determinant(){

        return determinant(this.matrix, this.n_size);
    }

    @Override
    public String toString() {
        StringBuilder view = new StringBuilder();

        for (int i = 0; i < this.matrix.length; ++i){
            for (int j = 0; j < this.matrix[i].length; ++j){
                view.append(' ');
                view.append(this.matrix[i][j].toString());
            }
            view.append('\n');
        }
        return view.toString();
    }

    private ComplexNumber determinant(ComplexNumber[][] matrix, int n){

        if (!this.is_determinant_available()){
            throw new RuntimeException();
        }

        ComplexNumber D = new ComplexNumber(0);

        if (n == 1)
            return matrix[0][0];

        ComplexNumber temp[][] = new ComplexNumber[n][n];

        ComplexNumber sign = new ComplexNumber(1);

        for (int f = 0; f < n; f++) {
            getSubMatrix(matrix, temp, 0, f, n);
            D = D.append(matrix[0][f].multiply(sign).multiply(determinant(temp, n - 1)));

            ComplexNumber kek = new ComplexNumber(-1);
            sign = sign.multiply(kek);
        }

        return D;
    }

    private void getSubMatrix(ComplexNumber[][] mat, ComplexNumber[][] temp, int p, int q, int n){
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }


    private boolean is_plus_available(Matrix first, Matrix second){
        return first.m_size == second.m_size && first.n_size == second.n_size;
    }

    private boolean is_mull_available(Matrix first, Matrix second){
        return first.m_size == second.n_size;
    }

    private boolean is_determinant_available(){
        return this.m_size == this.n_size;
    }

    private void fill_zeros(ComplexNumber[][] matrix){
        for (int i = 0; i < matrix.length; ++i){
            for (int j = 0; j < matrix[i].length; ++j){
                matrix[i][j] = new ComplexNumber(0);
            }
        }
    }

}
