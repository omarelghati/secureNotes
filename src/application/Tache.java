package application;

public class Tache {
	private String nom;
	private String description;
	private String degre;

	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tache(String nom,String description,String degre){
		super();
		this.nom=nom;
		this.description=description;
		this.degre=degre;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDegre() {
		return degre;
	}
	public void setDegre(String degre) {
		this.degre = degre;
	}

}
