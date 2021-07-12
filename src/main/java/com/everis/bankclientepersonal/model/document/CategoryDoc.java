package com.everis.bankclientepersonal.model.document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "CategoryDoc")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryDoc {
	@Id
	@NotEmpty
	private String id;
	@NotNull
	private String description;
	
	
	public CategoryDoc(@NotNull String description) {
		super();
		this.description = description;
	}
	
		
}
