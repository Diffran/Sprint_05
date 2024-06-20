package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto;


import java.util.Arrays;
import java.util.List;

public class BranchDTO {

    private Integer pk_BranchID;
    private String branchName;
    private String branchCountry;
    private String branchType;
    private static final List<String> UE_COUNTRIES = Arrays.asList(
            "Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece",
            "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden"
    );

    public BranchDTO() {
    }

    public BranchDTO(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        setBranchType(this.branchCountry);
    }

    public BranchDTO(Integer pk_SucursalID, String branchName, String branchCountry) {
        this.pk_BranchID = pk_SucursalID;
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        setBranchType(this.branchCountry);
    }

    public void setBranchType(String branchCountry){
        this.branchType = UE_COUNTRIES.stream()
                .anyMatch(country -> country.equalsIgnoreCase(branchCountry)) ? "UE" : "NON UE";
    }

    public String getBranchType() {
        return branchType;
    }

    public Integer getPk_BranchID() {
        return pk_BranchID;
    }

    public void setPk_BranchID(Integer pk_BranchID) {
        this.pk_BranchID = pk_BranchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "pk_BranchID=" + pk_BranchID +
                ", branchName='" + branchName + '\'' +
                ", branchCountry='" + branchCountry + '\'' +
                ", branchType='" + branchType + '\'' +
                '}';
    }
}
