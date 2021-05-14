package projeto_LP2_AED2;

public interface GestaoObjetos {

  public boolean regista(Objeto objeto);
  public boolean regista(TravelBug tb);
  public boolean removeO(Integer idObjeto);
  public boolean removeTb(Integer idTb);
  public boolean existeO(Integer idObjeto);
  public boolean existeTb(Integer idTb);
  public boolean editarO(int id, String nome);
  public boolean editarTb(int id, String nome);
}