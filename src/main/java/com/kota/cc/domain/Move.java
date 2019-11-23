package com.kota.cc.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Move {

	@JsonIgnore
	@Id @GeneratedValue
	public long id;
	@Enumerated(EnumType.STRING)
	public Player player;
	public int input;
	public Instant time;
	public int response;
	public boolean error;
	public String message;
}
