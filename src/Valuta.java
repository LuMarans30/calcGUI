public class Valuta {
    private String nome;
    private double valore;
    private String simbolo;

    public Valuta(String nome, double valore, String simbolo) {
        this.nome = nome;
        this.valore = valore;
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public double getValore() {
        return valore;
    }

    public String toString() {
        return nome + " (" + simbolo + ")" + " - " + valore;
    }
}
