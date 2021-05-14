package projeto_LP2_AED2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.List;

public class Objeto{

  //FIELDS/CAMPOS
  private boolean viajar;
  private Integer idObjeto;
  private String nome;
  private Cache cache;
  private Aventureiro aventureiro;
  private Localizacao local;

  //GETTERS AND SETTERS
  public Objeto(String nome) {
    this.nome = nome;
  }

  public boolean isViajar() {
    return viajar;
  }

  public void setViajar(boolean viajar) {
    this.viajar = viajar;
  }

  public Integer getIdObjeto() {
    return idObjeto;
  }

  public void setIdObjeto(Integer idObjeto) {
    this.idObjeto = idObjeto;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Cache getCache() {
    return cache;
  }

  public void setCache(Cache cache) {
    this.cache = cache;
  }

  public Aventureiro getAventureiro() {
    return aventureiro;
  }

  public void setAventureiro(Aventureiro aventureiro) {
    this.aventureiro = aventureiro;
  }

  /**
   * Méetodo para guardar Objeto no ficheiro de Objetos
   */
  public void guardarObjeto(){
    int k = 0;
    In infile = new In("data/Objeto.txt");
    String [] lines = infile.readAllLines();
    Out outfile = new Out("data/Objeto.txt");
    String toFile = "Objeto " + idObjeto + " " + nome; //guardamos o ID e o nome
    while (lines.length > k) {
      outfile.println(lines[k]);
      k++;
    }
    outfile.println(toFile);
  }

  /**
   * Método toString do Objeto
   * @return
   */
  @Override
  public String toString() {
    return "idObjeto:" + idObjeto +
            ", nome: " + nome;
  }
}