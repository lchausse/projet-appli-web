package principal;
import java.util.HashSet;
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
	@ManyToMany(mappedBy="utilisateurs", fetch=FetchType.EAGER)
	private Set<Playlist> mesPlaylists;
	
	public Utilisateur() {
		this.mesPlaylists = new HashSet<Playlist>();
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
	
	public Set<Playlist> getMesPlaylists() {
		return this.mesPlaylists;	
	}
	
	public void setMesPlaylists(Set<Playlist> playlists) {
		this.mesPlaylists = playlists;	
	}

	public void addPlaylist(Playlist playlist) {
		this.mesPlaylists.add(playlist);	
	}
}
