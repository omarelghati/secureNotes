package application;

public class Note {
	private String nom;
	private String description;


	public Note(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
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


}
