package main.java.Bunnies.Bunny6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alina on 9/7/2017.
 */
public class Bunny6 {
    public static void main(String [] args){
        int[][] myExample1 = {{0, 7, 0, 17, 0, 1, 0, 5, 0, 2},{0, 0, 29, 0, 28, 0, 3, 0, 16, 0},{0, 3, 0, 0, 0, 1, 0, 0, 0, 0},{48, 0, 3, 0, 0, 0, 17, 0, 0, 0},{0, 6, 0, 0, 0, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] myExample2 = {{1,2,3,4,5,6}};
        int[][] m1 = {{0,2,1,0,0},{0,0,0,3,4},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        int[][] m2 = {{0,1,0,0,0,1},{4,0,0,3,2,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        System.out.println(Arrays.toString(answer(myExample1)));
        System.out.println(Arrays.toString(answer(myExample2)));
        System.out.println(Arrays.toString(answer(m1)));
        System.out.println(Arrays.toString(answer(m2)));
    }

    public static int[] answer(int[][] m) {

        if(m.length == 1) return new int[]{1,1};
        if(findTerminals(m).contains(0)) return new int[]{1,1};

        Fraction[][] B = transformToFractions(m);

        ArrayList<ArrayList<Fraction>> Qlist = new ArrayList<>();
        ArrayList<ArrayList<Fraction>> Rlist = new ArrayList<>();

        for(int i =0; i<B.length; i++){
            ArrayList<Fraction> qRows = new ArrayList<>();
            ArrayList<Fraction> rRows = new ArrayList<>();
            if(!findTerminals(m).contains(i)){
                for(int j = 0; j<B.length; j++) {
                    if(!findTerminals(m).contains(j)){
                        qRows.add(B[i][j]);
                    }
                    else{
                        rRows.add(B[i][j]);
                    }
                }
                Qlist.add(qRows);
                Rlist.add(rRows);
            }
        }

        Fraction[][] Q = new Fraction[Qlist.size()][Qlist.get(0).size()];
        int i=0;
        for(ArrayList<Fraction> arr: Qlist){
            arr.toArray(Q[i]);
            i++;
        }
        Fraction[][] R = new Fraction[Rlist.size()][Rlist.get(0).size()];
        int j=0;
        for(ArrayList<Fraction> arr1: Rlist){
            arr1.toArray(R[j]);
            j++;
        }

        Matrix Qmatrix = new Matrix(Q);
        Matrix IminusQ = Matrix.subtractFromIdentityMatrix(Qmatrix);
        Matrix Fmatrix = Matrix.inverse(IminusQ);
        Matrix Rmatrix = new Matrix(R);

        Fraction[][] FR = Fmatrix.multiplyByMatrix(Rmatrix);
        Fraction[] result = FR[0];

        return commonDenominator(result);
    }

    public static int[] commonDenominator(Fraction[] result){
        int maxDenominator = result[0].getDenominator();
        Fraction maxDenominatorFraction = result[0];
        for(int i = 1; i<result.length; i++){
            maxDenominator = maxDenominatorFraction.lcm(result[i]);
            maxDenominatorFraction = new Fraction(1,maxDenominator);
        }
        int[] fin = new int[result.length+1];
        fin[fin.length-1] = maxDenominator;

        for(int i = 0; i<result.length; i++){
            result[i] = result[i].multiply(maxDenominator);
            result[i].simplify();
            fin[i] = result[i].getNumerator();
        }
        return fin;
    }

    public static List<Integer> findTerminals(int[][] m){
        List terminals = new ArrayList();
        for (int i = 0; i < m.length; i++) {
            if(findRowSum(m[i]) == 0){
                terminals.add(i);
            }
        }
        return terminals;
    }
    public static int findRowSum(int[] m) {
        int sum = 0;
        for (int i : m){
            sum += i;
        }
        return sum;
    }

    public static Fraction[][] transformToFractions(int[][] m) {
        Fraction [][] b = new Fraction[m.length][m.length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if(m[i][j]==0)
                    b[i][j] = new Fraction(m[i][j]).simplify();
                else
                    b[i][j]= new Fraction(m[i][j], findRowSum(m[i])).simplify();
            }
        }
        return b;
    }
}

class Fraction extends Number{

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    @Override
    public int intValue() {
        return (int) this.doubleValue();
    }

    @Override
    public long longValue() {
        return (long) this.doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) this.doubleValue();
    }

    @Override
    public double doubleValue() {
        return ((double) numerator)/((double) denominator);
    }

    public Fraction subtract(Fraction frac){
        return new Fraction(this.numerator*frac.denominator - frac.numerator*this.denominator, this.denominator*frac.denominator).simplify();
    }

    @Override
    public String toString() {
        return " " + numerator + "/" + denominator + " ";
    }

    public int gcd(int a, int b)
    {
        if (b==0) return a;
        else
            return gcd(b,a%b);
    }

    public Fraction simplify()
    {
        int gcd = gcd(this.numerator, this.denominator);
        if(gcd == 0) {
            this.numerator = 0;
            this.denominator = 1;
        }
        else {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
        return new Fraction(this.numerator,this.denominator);
    }

    Fraction add(Fraction frac)
    {
        return new Fraction(this.numerator * frac.denominator + frac.numerator * this.denominator,
                this.denominator * frac.denominator).simplify();
    }
    public Fraction multiply(Fraction frac){
        return new Fraction(this.numerator*frac.numerator, this.denominator*frac.denominator).simplify();
    }

    public Fraction multiply(int x){
        return new Fraction(x*this.numerator, this.denominator).simplify();
    }

    public Fraction inverse(){
        if(this.denominator != 0)
            return new Fraction(this.denominator,this.numerator).simplify();
        else return new Fraction(0).simplify();
    }

    public int lcm(Fraction f)
    {   int a,b;
        a = this.denominator;
        b = f.denominator;
        return a * (b / gcd(a, b));
    }
}

class Matrix {

    private int nrows;
    private int ncols;
    private Fraction[][] data;

    public Matrix(Fraction[][] dat) {
        this.data = dat;
        this.nrows = dat.length;
        this.ncols = dat[0].length;
    }

    public Matrix(int nrows,int ncols) {
        this.nrows = nrows;
        this.ncols = ncols;
        data = new Fraction[nrows][ncols];
    }

    public int getNrows() {
        return nrows;
    }

    public int getNcols() { return ncols; }

    public void setValueAt(int x, int y, Fraction value) {
        for (int i = 0; i < this.getNrows(); i++) {
            for (int j = 0; j < this.getNcols(); j++) {
                if (i == x && j == y)
                    this.data[i][j] = value.simplify();
            }
        }
    }

    public Fraction getValueAt(int i, int j) {
        return this.data[i][j].simplify();
    }

    public static Matrix transpose(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.getNcols(),matrix.getNrows());
        for (int i = 0; i < matrix.getNrows(); i++) {
            for (int j = 0; j < matrix.getNcols(); j++) {
                transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
            }
        }
        return transposedMatrix;
    }

    public static Fraction determinant(Matrix matrix) {
        if (matrix.getNrows() == 1) {
            return matrix.getValueAt(0, 0).simplify();
        }
        if (matrix.getNrows() == 2) {
            return matrix.getValueAt(0, 0).simplify().multiply(matrix.getValueAt(1, 1).simplify()).subtract(matrix.getValueAt(0, 1).simplify().multiply(matrix.getValueAt(1, 0).simplify()));
        }
        Fraction sum = new Fraction(0).simplify();
        for (int i = 0; i < matrix.getNcols(); i++) {
            sum = sum.add(matrix.getValueAt(0, i).simplify().multiply(changeSign(i)).multiply(determinant(createSubMatrix(matrix, 0, i))));
        }
        return sum.simplify();
    }

    public static int changeSign(int x) {
        if (x % 2 == 0) return 1;
        else return -1;
    }

    public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(matrix.getNrows() - 1,matrix.getNcols()-1);
        int r = -1;
        for (int i = 0; i < matrix.getNrows(); i++) {
            if (i == excluding_row)
                continue;
            r++;
            int c = -1;
            for (int j = 0; j < matrix.getNcols(); j++) {
                if (j == excluding_col)
                    continue;
                mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }

    public static Matrix cofactor(Matrix matrix) {
        Matrix mat = new Matrix(matrix.getNrows(),matrix.getNcols());
        for (int i = 0; i < matrix.getNrows(); i++) {
            for (int j = 0; j < matrix.getNcols(); j++) {
                mat.setValueAt(i, j, determinant(createSubMatrix(matrix, i, j)).multiply(changeSign(i) * changeSign(j)).simplify());
            }
        }
        return mat;
    }

    public static Matrix inverse(Matrix matrix) {
        return (transpose(cofactor(matrix)).multiplyByConstant(determinant(matrix).inverse()));
    }

    public Matrix multiplyByConstant(Fraction x) {
        x = x.simplify();
        for (int i = 0; i < this.getNrows(); i++) {
            for (int j = 0; j < this.getNcols(); j++) {
                this.data[i][j] = this.data[i][j].simplify().multiply(x).simplify();
            }
        }
        return this;
    }

    public Fraction[][] multiplyByMatrix(Matrix matrix){
        if (this.ncols != matrix.nrows) {
            return  matrix.multiplyByMatrix(this);
        }

        Fraction[][] product = new Fraction[this.nrows][matrix.ncols];

        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < matrix.ncols; j++) {
                Fraction sum = new Fraction(0).simplify();
                for (int k = 0; k < matrix.nrows; k++) {
                    sum = sum.simplify().add(data[i][k].simplify().multiply( matrix.data[k][j].simplify()));
                }
                product[i][j] = sum.simplify();
            }
        }
        return product;
    }

    public static Matrix subtractFromIdentityMatrix(Matrix b){
        Matrix identityMatrix = new Matrix(b.getNrows(),b.getNcols());
        Matrix result = new Matrix(b.getNrows(),b.getNcols());

        for(int i  = 0; i<b.nrows; i++){
            for(int j = 0; j<b.ncols; j++){
                if(i == j) identityMatrix.data[i][j] =  new Fraction(1).simplify();
                else identityMatrix.data[i][j] = new Fraction(0).simplify();
                result.data[i][j] =  identityMatrix.data[i][j].subtract(b.data[i][j].simplify());
            }
        }
        return result;

    }
}