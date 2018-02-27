package kaos.prosjekt;

public class Complex {

    private final double real, imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex gange(Complex b) {
        Complex a = this;
        double real = a.real * b.real - a.imag * b.imag;
        double imag = a.real * b.imag + a.imag * b.real;
        return new Complex(real, imag);
    }

    public Complex pluss(Complex b) {
        Complex a = this;
        double real = a.real + b.real;
        double imag = a.imag + b.imag;
        return new Complex(real, imag);
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }
    public double abs() {
        return (real*real)+(imag*imag);
    }
    @Override
    public String toString() {
        return "Complex{" + "real=" + real + ", imag=" + imag + '}';
    }
}