package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.domain;

import jakarta.persistence.*;


@Entity
@Table
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_BranchID;
    @Column(name = "name")
    private String branchName;
    @Column(name = "country")
    private String branchCountry;

    public Branch() {
    }

    public Branch(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

    public Branch(Integer pk_BranchID, String branchName, String branchCountry) {
        this.pk_BranchID = pk_BranchID;
        this.branchName = branchName;
        this.branchCountry = branchCountry;
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
        return "Branch{" +
                "pk_BranchID=" + pk_BranchID +
                ", branchName='" + branchName + '\'' +
                ", branchCountry='" + branchCountry + '\'' +
                '}';
    }
}
