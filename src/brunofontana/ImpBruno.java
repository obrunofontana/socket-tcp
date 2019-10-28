package brunofontana;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class ImpBruno implements IntBruno {

    private ArrayList<Livro> aLivros;

    public ImpBruno() {
        aLivros = new ArrayList<Livro>();
    }

    @Override
    public String cadastrar(int isbn, String titulo, String autor, String assunto) {
        boolean isExists = false;

        for (Livro item : aLivros) {
            if (item.getIsbn() == isbn) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            aLivros.add(new Livro(titulo, autor, assunto, isbn));
            return "Livro registrado com sucesso!";
        } else {
            return "ERROR: Livro já existe no array aLivros, por favor corrija o ISBN e tente novamente";
        }
    }

    @Override
    public String consultar(int tipo, String termo) {

        //Tipo 1 = titulo
        if (tipo == 1) {

            for (Livro item : aLivros) {
                if (item.getTitulo().equals(termo)) {
                    return item.toString(); //Retorna o item em formato String
                }
            }
            return "Ops! Livro com este Titulo não encontrado";
            //Tipo 2 = Autor    
        } else if (tipo == 2) {

            for (Livro item : aLivros) {
                if (item.getAutor().equals(termo)) {
                    return item.toString(); //Retorna o item em formato String
                }
            }
            return "Ops! Livro com este Autor não encontrado";

        }

        return "Opção indisponivel! Conteudos válidos no parâmetro 1 = 1 (titulo) e 2 (autor). Método: consultar(parâmetro 1, parâmetro 2) onde o segundo parâmetro é o termo a ser procurado";

    }

    @Override
    public String remover(int isbn) {

        for (Livro item : aLivros) {
            if (item.getIsbn() == isbn) {
                aLivros.remove(item);
                return "OK! Livro removido com sucesso!";
            }
        }
        return "ERRO! Livro não encontrado";
    }
}
