package org.tp_devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void creerArticleValideDoitFonctionner() {
        Article article = new Article("REF-001", "Cahier", 3.50);
        assertEquals("REF-001", article.getReference());
        assertEquals("Cahier", article.getNom());
        assertEquals(3.50, article.getPrix(), 0.001);
    }

    @Test
    void modifierPrixDoitMettreAJourLePrix() {
        Article article = new Article("REF-002", "Règle", 1.00);
        article.setPrix(1.50);
        assertEquals(1.50, article.getPrix(), 0.001);
    }

    @Test
    void referenceNulleDoitLeverException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Article(null, "Règle", 1.0);
        });
    }

    @Test
    void nomVideDoitLeverException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Article("REF-001", "", 1.0);
        });
    }

    @Test
    void prixNegatifAlaCreationDoitLeverException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Article("REF-001", "X", -1.0);
        });
    }

    @Test
    void setPrixNegatifDoitLeverException() {
        Article article = new Article("REF-001", "Stylo", 2.0);
        assertThrows(IllegalArgumentException.class, () -> {
            article.setPrix(-5.0);
        });
    }
}
