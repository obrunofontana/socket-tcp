package brunofontana;

/**
 *
 * @author bruno
 */
public class Livro {

    private String titulo;
    private String autor;
    private String assunto;
    private int isbn;

    @Override
    public String toString() {
        return "Livro{" + "titulo=" + titulo + ", autor=" + autor + ", assunto=" + assunto + ", isbn=" + isbn + '}';
    }

    public Livro(String titulo, String autor, String assunto, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.assunto = assunto;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAssunto() {
        return assunto;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
