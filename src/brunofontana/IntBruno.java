package brunofontana;

/**
 *
 * @author bruno
 */
public interface IntBruno {

    public String cadastrar(int isbn, String titulo, String autor, String assunto);

    public String consultar(int tipo, String termo);

    public String remover(int isbn);

}
