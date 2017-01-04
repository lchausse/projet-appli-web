package principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	
	@Id
	private String pseudo;
	private String mdp;
	private List<Playlist> mesPlayLists;
	
	public Utilisateur() {
		this.mesPlayLists = new ArrayList<Playlist>();
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
	
	public List<Playlist> getMesPlayLists() {
		return this.mesPlayLists;	
	}
	
	public void setMesPlayLists(List<Playlist> playlists) {
		this.mesPlayLists = playlists;	
	}
	
	public void addPlaylist(Playlist playlist) {
		this.mesPlayLists.add(playlist);	
	}
}
