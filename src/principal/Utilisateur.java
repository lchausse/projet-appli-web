package principal;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilisateur {
	
	@Id
	private String pseudo;
	private String mdp;
	private List<PlayList> mesPlayLists;
	
	public Utilisateur() {
		this.mesPlayLists = new ArrayList<PlayList>();
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public List<PlayList> getPlayLists() {
		return this.mesPlayLists;	
	}
	
	public void setPlayList(List<PlayList> playlist) {
		this.mesPlayLists = playlist;	
	}
}
