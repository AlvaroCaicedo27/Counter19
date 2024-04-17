package clases;

/**
 * Representa el modelo de información de un 'Nodo'. Los nodos son usados en las
 * estructuras de árboles para contener información y facilitar los
 * procedimientos que se realicen con estos en los árboles.
 */
public class Nodo {

    //Atributos de la clase.
    private String data;
    private Nodo up;
    private Nodo izq;
    private Nodo der;
    private int nivel;
    private int numNodo;

    //Constructor
    public Nodo(String pData) {
        data = pData;
        izq = null;
        der = null;
        up = null;
    }

    //Método usado para facilitar la asignación de nivel y número de un nodo.
    public void posicion(int pNivel, int pNumNodo) {
        nivel = pNivel;
        numNodo = pNumNodo;
    }

    //Métodos get y set.
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Nodo getUp() {
        return up;
    }

    public void setUp(Nodo up) {
        this.up = up;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNumNodo() {
        return numNodo;
    }

    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }

}
