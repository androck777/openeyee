package bytes.seven.openeye;

public class lugares {
    private double latitud;
    private double longitud;
    private String delincuentes;
    private String descripcion;
    private String resumen;

    public lugares() {
        super();
    }

    public lugares(double latitud, double longitud, String delincuentes, String descripcion, String resumen) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.delincuentes = delincuentes;
        this.descripcion = descripcion;
        this.resumen = resumen;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDelincuentes() {
        return delincuentes;
    }

    public void setDelincuentes(String delincuentes) {
        this.delincuentes = delincuentes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
