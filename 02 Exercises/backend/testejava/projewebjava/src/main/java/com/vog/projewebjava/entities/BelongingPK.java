package com.vog.projewebjava.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BelongingPK {
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game idGame;
	
	@ManyToOne
	@JoinColumn(name = "game_list_id")
	private GameList idGameList;

	public BelongingPK() {
	}

	public BelongingPK(Game idGame, GameList idGameList) {
		this.idGame = idGame;
		this.idGameList = idGameList;
	}

	public Game getIdGame() {
		return idGame;
	}

	public void setIdGame(Game idGame) {
		this.idGame = idGame;
	}

	public GameList getIdGameList() {
		return idGameList;
	}

	@Override
	public String toString() {
		return "BelongingPK [idGame=" + idGame + ", idGameList=" + idGameList + "]";
	}

	public void setIdGameList(GameList idGameList) {
		this.idGameList = idGameList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGame, idGameList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BelongingPK other = (BelongingPK) obj;
		return Objects.equals(idGame, other.idGame) && Objects.equals(idGameList, other.idGameList);
	}
}
