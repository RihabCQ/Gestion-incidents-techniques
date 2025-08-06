
package com.aeroport.repositories;

import com.aeroport.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
	
}
