package principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Utilisateur {
	
	@Id
	private String pseudo;
	private String mdp;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Playlist> mesPlayLists;
	
	public Utilisateur() {
		this.mesPlayLists = new HashSet<Playlist>();
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
	
	public Set<Playlist> getMesPlayLists() {
		return this.mesPlayLists;	
	}
	
	public void setMesPlayList(Set<Playlist> playlists) {
		this.mesPlayLists = playlists;	
	}

	public void addPlaylist(Playlist playlist) {
		this.mesPlayLists.add(playlist);	
	}
}
