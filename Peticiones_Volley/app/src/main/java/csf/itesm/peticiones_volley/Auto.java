package csf.itesm.peticiones_volley;

import java.io.Serializable;

public class Auto implements Serializable {
    private String marca;
    private String auto;
    private String image;

    public String getMarca(){
        return marca;
    }
    public String getImage(){
        return image;
    }
    public String getAuto(){
        return auto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
