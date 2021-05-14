package projeto_LP2_AED2;

public class Localizacao {
    //FIELDS/CAMPOS
    private Integer coordenadaX;
    private Integer coordenadaY;
    private String localizacao;

    /**
     * Construtor da localizacao
     * @param coordenadaX - localizacao X
     * @param coordenadaY - localizacao Y
     */
    public Localizacao(Integer coordenadaX, Integer coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    /**
     * Construtor da localizacao
     * @param coordenadaX - Localizacao X
     * @param coordenadaY - Localizacao Y
     * @param localizacao - Localizacao - Local
     */
    public Localizacao(Integer coordenadaX, Integer coordenadaY, String localizacao) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.localizacao = localizacao;
    }

    //GETTERS AND SETTERS
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Integer coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Integer getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Integer coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    /**
     * Metodo que calcula a distancia entre localizcoes
     * @param local - local que queremos calcular a disctancia
     * @return da return da distancia
     */
    public double distancia(Localizacao local) {
        return Math.sqrt(Math.pow(distanceX(local), 2.0) + Math.pow(distanceY(local), 2.0));
    }

    /**
     * Metodo que calcula a distancia X entre duas posicoes
     * @param local - local que queremos calcular a disctancia
     * @return a distancia x
     */
    public double distanceX(Localizacao local) {
        return Math.abs(this.getCoordenadaX() - local.getCoordenadaX());
    }

    /**
     * Metodo que calcula a distancia Y entre duas posicoes
     * @param local - local que queremos calcular a disctancia
     * @return a distancia y
     */
    public double distanceY(Localizacao local) {
        return Math.abs(this.getCoordenadaY() - local.getCoordenadaY());
    }


    @Override
    public String toString() {
        return "X = " + coordenadaX +
                ", Y = " + coordenadaY;
    }
}