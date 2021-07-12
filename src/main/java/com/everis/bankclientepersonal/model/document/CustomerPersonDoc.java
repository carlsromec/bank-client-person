package com.everis.bankclientepersonal.model.document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "CustomerPersonDoc")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CustomerPersonDoc {
	
	@Id
	@NotEmpty
	private String id;
	@NotNull
	private String dni;
	@NotNull
	private String lastname;
	@NotNull
	private String firstname;
	@NotNull
	private String dicetion;
	@NotNull
	private String telephone;
	
	@Valid
	private CategoryDoc categoryDoc;

	public CustomerPersonDoc(@NotNull String dni, @NotNull String lastname, @NotNull String firstname,
			@NotNull String dicetion, @NotNull String telephone, @Valid CategoryDoc categoryDoc) {
		super();
		this.dni = dni;
		this.lastname = lastname;
		this.firstname = firstname;
		this.dicetion = dicetion;
		this.telephone = telephone;
		this.categoryDoc = categoryDoc;
	}
	
	
	
	

}
