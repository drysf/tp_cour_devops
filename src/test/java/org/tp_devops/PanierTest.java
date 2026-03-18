package org.tp_devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PanierTest {
    @Test
    void ajouterArticleDeitAugmenterLeNombreDeArticles() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        // Agir
        panier.ajouterArticle(article, 2);
        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void calculerTotalDoitRetournerLaSommeDessousTotaux() {
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
        Article article2 = new Article("REF-002", "Stylo rouge", 1.50);
        panier.ajouterArticle(article1, 3);
        panier.ajouterArticle(article2, 3);
        assertEquals(9.00, panier.calculerTotal());
    }

    @Test
    void panierVideDoitRetournerEstVideEgalTrue() {
        Panier panier = new Panier();
        assertTrue(panier.estVide());
    }

    @Test
    void panierAvecArticlesNeDoitPasEtreVide() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        panier.ajouterArticle(article, 1);
        assertFalse(panier.estVide());
    }

    @Test
    void articleNulDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(null, 1));
    }

    @Test
    void quantiteNulleDoitLeverException() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, 0));
    }

    @Test
    void quantiteNegativeDoitLeverException() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, -3));
    }

    @Test
    void codeReductionVideDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(""));
    }

    @Test
    void codeReductionNulDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(null));
    }

    @Test
    void quantiteUneDoitEtreAcceptee() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 9.99);
        panier.ajouterArticle(article, 1);
        assertEquals(9.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void articleGratuitDoitEtreAccepte() {
        Panier panier = new Panier();
        Article articleGratuit = new Article("OFFERT-01", "Stylo offert", 0.0);
        panier.ajouterArticle(articleGratuit, 1);
        assertEquals(0.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void prixEleveDoitFonctionner() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Objet cher", 999.99);
        panier.ajouterArticle(article, 1);
        assertEquals(999.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierAvecUnSeulArticleDoitFonctionner() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        panier.ajouterArticle(article, 1);
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void plusieursArticlesDifferentsDansPanier() {
        Panier panier = new Panier();
        Article a1 = new Article("REF-001", "Stylo", 1.50);
        Article a2 = new Article("REF-002", "Cahier", 3.00);
        Article a3 = new Article("REF-003", "Règle", 2.50);
        panier.ajouterArticle(a1, 2);  // 3.00
        panier.ajouterArticle(a2, 1);  // 3.00
        panier.ajouterArticle(a3, 3);  // 7.50
        assertEquals(13.50, panier.calculerTotal(), 0.001);
    }
}
