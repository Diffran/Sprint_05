package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto;


public class SucursalDTO {

    private Integer pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;

    public SucursalDTO() {
    }

    public SucursalDTO(String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
    }

    public Integer getPk_SucursalID() {
            return pk_SucursalID;
        }

        public void setPk_SucursalID(Integer pk_SucursalID) {
            this.pk_SucursalID = pk_SucursalID;
        }

        public String getNomSucursal() {
            return nomSucursal;
        }

        public void setNomSucursal(String nomSucursal) {
            this.nomSucursal = nomSucursal;
        }

        public String getPaisSucursal() {
            return paisSucursal;
        }

        public void setPaisSucursal(String paisSucursal) {
            this.paisSucursal = paisSucursal;
        }

        @Override
        public String toString() {
            return "SucursalDTO{" +
                    "pk_SucursalID=" + pk_SucursalID +
                    ", nomSucursal='" + nomSucursal + '\'' +
                    ", paisSucursal='" + paisSucursal + '\'' +
                    '}';
        }


}
